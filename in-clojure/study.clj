(def items [1 2 3 4])


;;;;;;;;;;;;;;;;;;;;;;;;;;
;; loops and iterations

;; doseq
(doseq [item items]
  (println item))

;; loop
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

(defn first-fit-dec [items, cap]
  ;;sorted vector [8 7 5 4 3 2 1]
  (first-fit (sort > items) cap))
  
(def result (first-fit-dec items cap))
(println "Result vector of bins used:"  result)
(println "Number of bins used:" (count result))


;; reduce
(def testItems [1 2 3 4 5 6 7 8 9 0])
(println (reduce + testItems))

;; access index of vector
(def vector [1 2 3 4 5])
(println (nth vector 2))
;; expected 3

;; if
(def a 1)
(def b 2)

(if (< a b)
  (println "b bigger")
  (println "b smaller"))


;; assoc to replace a value from a vector in a specific index
(def values [1 2 3 4 5])
;; create new vector with 10 instead of 2
(println (assoc values 1 10))
;; expected [1 10 3 4 5]


;; conj to add a value to a vector
(println (conj values 13))
;; expected [1 2 3 4 5 13]
