(ns api.handlers
  (:require [cheshire.core :as json]))

(defn bitonic-sequence [n start end]
  (let [range-vals (range start (inc end))
        mid (quot n 2)
        ascending (take mid (cycle range-vals))
        descending (take (- n mid)
                         (cycle (reverse range-vals)))]
    (concat ascending
            (drop-while #(> (first ascending) %) descending))))

(defn hello [_]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string {:message "Hello, world!"})})

(defn bitonic [{:keys [query-params body]}]
  (let [{:keys [n start end]} (if body
                                (json/parse-string (slurp body) true)
                                (zipmap [:n :start :end]
                                       (map #(Integer/parseInt %)
                                           [(get query-params "n")
                                            (get query-params "start")
                                            (get query-params "end")])))
        sequence (vec (bitonic-sequence n start end))]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string {:sequence sequence
                                  :length n
                                  :range [start end]})}))

(defn health [_]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string {:status "ok" :service "bitonic-api"})})