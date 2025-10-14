(ns api.db-test
  (:require [clojure.test :refer :all]
            [api.db :as db]))

(deftest test-generate-cache-key
  (testing "generates consistent cache keys"
    (is (= "bitonic:5:1:10" (db/generate-cache-key 5 1 10)))
    (is (= "bitonic:3:0:5" (db/generate-cache-key 3 0 5))))
  
  (testing "handles different parameter types"
    (is (= "bitonic:10:5:15" (db/generate-cache-key 10 5 15)))))

(deftest test-cache-operations
  (testing "store and retrieve operations work together"
    (let [key "test:key"
          data {:test "value" :number 42}]
      (db/store-result key data)
      (let [retrieved (db/get-result key)]
        (is (= data retrieved)))))
  
  (testing "returns nil for non-existent keys"
    (is (nil? (db/get-result "non-existent-key")))))