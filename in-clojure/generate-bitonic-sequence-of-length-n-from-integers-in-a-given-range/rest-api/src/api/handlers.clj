(ns api.handlers
  (:require [cheshire.core :as json]
            [api.db :as db]
            [taoensso.carmine :as car]))

(defn bitonic-sequence [n start end]
  (let [range-vals (range start (inc end))
        mid (quot n 2)
        ascending (take mid (cycle range-vals))
        descending (take (- n mid)
                         (cycle (reverse range-vals)))]
    (concat ascending
            (drop-while #(> (first ascending) %) descending))))
            
(defn bitonic [{:keys [query-params body]}]
  (let [{:keys [n start end]} (if body
                                (json/parse-string (slurp body) true)
                                (zipmap [:n :start :end]
                                       (map #(Integer/parseInt %)
                                           [(get query-params "n")
                                            (get query-params "start")
                                            (get query-params "end")])))
        cache-key (db/generate-cache-key n start end)
        cached-result (db/get-result cache-key)]
    (if cached-result
      {:status 200
       :headers {"Content-Type" "application/json"}
       :body (json/generate-string (assoc cached-result :cached true))}
      (let [sequence (vec (bitonic-sequence n start end))
            result {:sequence sequence :length n :range [start end]}]
        (db/store-result cache-key result)
        {:status 200
         :headers {"Content-Type" "application/json"}
         :body (json/generate-string result)}))))

(defn health [_]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string {:status "ok" :service "bitonic-api"})})

(defn clear-cache [_]
  (try
    (db/wcar* (car/flushdb))
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string {:message "Cache cleared"})}
    (catch Exception e
      {:status 500
       :headers {"Content-Type" "application/json"}
       :body (json/generate-string {:error "Failed to clear cache"})})))