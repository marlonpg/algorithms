(def items [1 2 3 4])

(doseq [item items]
  (println item))

(loop [index 0]
  (when (< index (count items))
    (println (nth items index))
    (recur (inc index))))

(defn first-fit-test [items cap]
  (loop [cap (- (count items) 1)]
    (when (>= cap 0)
      (println (nth items cap))
      (recur (dec cap)))
  )
)

(first-fit-test items 3)

;;;;;;;;;;;;;;
;; using mutable data structures
;;;;;;;;;;;;;;

(defn first-fit-decreasing [items bin-capacity]
  (let [sorted-items (sort > items)]
    (reduce
      (fn [bins item]
        (if-let [index (some (fn [[idx bin]] (when (<= item (- bin-capacity bin)) idx))
                             (map-indexed vector bins))]
          (update bins index + item)
          (conj bins item)))
      []
      sorted-items)))

(def items [4 8 1 4 2 1])
(def bin-capacity 10)

(println "Bins:" (first-fit-decreasing items bin-capacity))


;;;;;;;;;;;;;;
;; using immutable data structures
;;;;;;;;;;;;;;

(defn first-fit-test2 [items bin-capacity]
  (letfn [(place-item [bins item]
            (loop [i 0]
              (if (< i (count bins))
                (if (<= (+ (nth bins i) item) bin-capacity)
                  (assoc bins i (+ (nth bins i) item))
                  (recur (inc i)))
                (conj bins item))))]
    (reduce place-item [] (sort > items))))

(def items [2 5 4 7 1 3 8])
(def bin-capacity 10)

(def result (first-fit-test2 items bin-capacity))
(println "Result list of bins used:"  result)
(println "Number of bins used:" (count result))

;; second example
(defn first-fit-2 [items bin-capacity]
  (letfn [(place-item [bins item]
            (if-let [index (some (fn [[idx bin]]
                                   (when (<= (+ bin item) bin-capacity)
                                     idx))
                                 (map-indexed vector bins))]
              (assoc bins index (+ (bins index) item))
              (conj bins item)))]
    (reduce place-item [] items)))

(def items [2 5 4 7 1 3 8])
(def bin-capacity 10)

(println "Number of bins used:" (count (first-fit-2 items bin-capacity)))


;;; V2 
(defn first-fit [items cap]
  (letfn [(place-item [bins item]
            (loop [i 0]
              (if (< i (count bins))
                (if (<= (+ (nth bins i) item) cap)
                  (assoc bins i (+ (nth bins i) item))
                  (recur (inc i)))
                (conj bins item))))]
    (reduce place-item [] items)))

(def items [2 5 4 7 1 3 8])
(def cap 10)

(defn first-fit-desc [items, cap]
  ;;sorted vector [8 7 5 4 3 2 1]
  (first-fit (sort > items) cap))
  
(def result (first-fit-desc items cap))
(println "Result vector of bins used:"  result)
(println "Number of bins used:" (count result))