(ns bin-packing-problem.bin-packing-problem-test
  (:require [clojure.test :refer :all]
            [bin-packing-problem.bin-packing-problem :as bin]))

(deftest first-fit-dec-should-return-vector-with-10-10-10
  (def items [2 5 4 7 1 3 8])
  (def cap 10)
  (def result (bin/first-fit-dec items cap))
  (testing
    (is (= 3 (count result)))
    (is (= [10 10 10] result))))

(deftest first-fit-should-return-vector-with-8-7-7-8
  (def items [2 5 4 7 1 3 8])
  (def cap 10)
  (def result (bin/first-fit items cap))
  (testing
    (is (= 4 (count result)))
    (is (= [8 7 7 8] result))))