(ns api.main
  (:require
    [ring.adapter.jetty :as jetty]
    [api.controller :refer [app]]))

(defn -main []
  (println "Starting server on port 3000...")
  (jetty/run-jetty app {:port 3000 :join? false}))