(defn bitonic-sequence [n start end]
  (let [mid (quot n 2)
        range-vals (range start (inc end))
        ascending (take mid (cycle range-vals))
        descending (take (- n mid) (cycle (reverse range-vals)))]
    (concat ascending descending)))

(defn -main []
  (println "Bitonic sequence examples:")
  (println "Length 8, range 1-4:" (bitonic-sequence 8 1 4))
  (println "Length 6, range 2-5:" (bitonic-sequence 6 2 5)))

(-main)