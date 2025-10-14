(ns api.bitonic-service-test
  (:require [clojure.test :refer :all]
            [api.bitonic-service :as bitonic-service]
            [api.db :as db]
            [cheshire.core :as json]))

(def mock-cache (atom {}))

(defn mock-db-fixture [f]
  (reset! mock-cache {})
  (with-redefs [db/store-result (fn [key data] (swap! mock-cache assoc key data))
                db/get-result (fn [key] (get @mock-cache key))]
    (f)))

(use-fixtures :each mock-db-fixture)

(deftest test-bitonic-sequence
  (testing "generates correct bitonic sequence"
    (let [result (bitonic-service/bitonic-sequence 6 1 3)]
      (is (= 6 (count result)))
      (is (every? #(<= 1 % 3) result))))
  
  (testing "handles edge cases"
    (let [result (bitonic-service/bitonic-sequence 1 5 5)]
      (is (= 1 (count result)))
      (is (= 5 (first result))))))

(deftest test-health-endpoint
  (testing "returns healthy status"
    (let [response (bitonic-service/health {})]
      (is (= 200 (:status response)))
      (is (= "application/json" (get-in response [:headers "Content-Type"])))
      (let [body (json/parse-string (:body response) true)]
        (is (= "ok" (:status body)))
        (is (= "bitonic-api" (:service body)))))))

(deftest test-bitonic-endpoint-with-query-params
  (testing "processes query parameters correctly"
    (let [request {:query-params {"n" "4" "start" "1" "end" "2"}}
          response (bitonic-service/bitonic request)]
      (is (= 200 (:status response)))
      (is (= "application/json" (get-in response [:headers "Content-Type"])))
      (let [body (json/parse-string (:body response) true)]
        (is (= 4 (:length body)))
        (is (= [1 2] (:range body)))
        (is (= 4 (count (:sequence body))))))))

(deftest test-bitonic-endpoint-caching
  (testing "caches results correctly"
    (let [request {:query-params {"n" "4" "start" "1" "end" "2"}}
          response1 (bitonic-service/bitonic request)
          response2 (bitonic-service/bitonic request)]
      (is (= 200 (:status response1)))
      (is (= 200 (:status response2)))
      (let [body1 (json/parse-string (:body response1) true)
            body2 (json/parse-string (:body response2) true)]
        (is (false? (:cached body1)))
        (is (true? (:cached body2)))
        (is (= (:sequence body1) (:sequence body2)))))))