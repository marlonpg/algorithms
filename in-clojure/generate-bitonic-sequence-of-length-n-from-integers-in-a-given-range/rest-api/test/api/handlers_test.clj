(ns api.handlers-test
  (:require [clojure.test :refer :all]
            [api.handlers :as handlers]
            [cheshire.core :as json]))

(deftest test-bitonic-sequence
  (testing "generates correct bitonic sequence"
    (let [result (handlers/bitonic-sequence 6 1 3)]
      (is (= 6 (count result)))
      (is (every? #(<= 1 % 3) result))))
  
  (testing "handles edge cases"
    (let [result (handlers/bitonic-sequence 1 5 5)]
      (is (= 1 (count result)))
      (is (= 5 (first result))))))

(deftest test-health-endpoint
  (testing "returns healthy status"
    (let [response (handlers/health {})]
      (is (= 200 (:status response)))
      (is (= "application/json" (get-in response [:headers "Content-Type"])))
      (let [body (json/parse-string (:body response) true)]
        (is (= "ok" (:status body)))
        (is (= "bitonic-api" (:service body)))))))

(deftest test-bitonic-endpoint-with-query-params
  (testing "processes query parameters correctly"
    (let [request {:query-params {"n" "4" "start" "1" "end" "2"}}
          response (handlers/bitonic request)]
      (is (= 200 (:status response)))
      (is (= "application/json" (get-in response [:headers "Content-Type"])))
      (let [body (json/parse-string (:body response) true)]
        (is (= 4 (:length body)))
        (is (= [1 2] (:range body)))
        (is (= 4 (count (:sequence body))))))))