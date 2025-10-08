(require '[ring.adapter.jetty :as jetty]
         '[compojure.core :refer [defroutes GET POST]]
         '[compojure.route :as route]
         '[ring.middleware.json :refer [wrap-json-response wrap-json-body]]
         '[ring.util.response :refer [response]])

(defn bitonic-sequence [n start end]
  (let [mid (quot n 2)
        range-vals (range start (inc end))
        ascending (take mid (cycle range-vals))
        descending (take (- n mid) (cycle (reverse range-vals)))]
    (concat ascending descending)))

(defroutes app-routes
  (GET "/bitonic" [n start end]
    (let [n (Integer/parseInt n)
          start (Integer/parseInt start)
          end (Integer/parseInt end)]
      (response {:sequence (vec (bitonic-sequence n start end))
                 :length n
                 :range [start end]})))
  
  (POST "/bitonic" {body :body}
    (let [{:keys [n start end]} body]
      (response {:sequence (vec (bitonic-sequence n start end))
                 :length n
                 :range [start end]})))
  
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-json-body {:keywords? true})
      wrap-json-response))

(defn -main []
  (jetty/run-jetty app {:port 3000 :join? false})
  (println "Server running on http://localhost:3000")
  (println "Try: GET /bitonic?n=8&start=1&end=4"))