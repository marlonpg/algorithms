(ns bin-packing-problem.bin-packing-problem)

(defn first-fit [items cap]
  (letfn [(place-item [bins item]
            (loop [i 0]
              (if (< i (count bins))
                (if (<= (+ (nth bins i) item) cap)
                  (assoc bins i (+ (nth bins i) item))
                  (recur (inc i)))
                (conj bins item))))]
    (reduce place-item [] items)))

(defn first-fit-dec [items, cap]
  (first-fit (sort > items) cap))