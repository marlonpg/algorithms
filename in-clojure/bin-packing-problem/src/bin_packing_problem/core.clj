(ns bin-packing-problem.core
  (:require [bin-packing-problem.bin-packing-problem :as bin]))

(def items [2 5 4 7 1 3 8])
(def cap 10)

(defn -main
  [& args]
  (def result (bin/first-fit-dec items cap))
  (println "Result vector of bins used:"  result)
  (println "Number of bins used:" (count result)))