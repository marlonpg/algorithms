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