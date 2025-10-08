;; (defn bitonic-sequence [n start end]
;;   (let [mid (quot n 2)
;;         range-vals (range start (inc end))
;;         ascending (take mid (cycle range-vals))
;;         descending (take (- n mid) (cycle (reverse range-vals)))]
;;     (concat ascending descending)))

(defn bitonic-sequence [n start end]
  (let [range-vals (range start (inc end))
        mid (quot n 2)
        
        ;; ascending: take `mid` numbers from range repeatedly, non-strictly increasing
        ascending (take mid (cycle range-vals))
        
        ;; descending: take remaining numbers from reversed range repeatedly
        ;; make sure the first number starts from the last of ascending or smaller
        descending (take (- n mid)
                         (cycle (reverse range-vals)))]
    
    ;; trim the descending part if the first element is bigger than the last of ascending
    ;; so the sequence never goes up again
    (concat ascending
            (drop-while #(> (first ascending) %) descending))))

(defn -main []
  (println "Bitonic sequence examples:")
  (println "Length 8, range 1-4:" (bitonic-sequence 8 1 4))
  (println "Length 7, range 1-3:" (bitonic-sequence 7 1 3))
  (println "Length 6, range 2-5:" (bitonic-sequence 6 2 5)))

(-main)