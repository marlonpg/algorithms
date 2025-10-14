(ns api.db
  (:require [taoensso.carmine :as car]
            [cheshire.core :as json]))

(def redis-conn {:pool {} :spec {:host (or (System/getenv "REDIS_HOST") "localhost")
                                  :port (Integer/parseInt (or (System/getenv "REDIS_PORT") "6379"))}})

(defmacro wcar* [& body] `(car/wcar redis-conn ~@body))

(defn store-result [key data]
  (wcar* (car/set key (json/generate-string data))))

(defn get-result [key]
  (when-let [result (wcar* (car/get key))]
    (json/parse-string result true)))

(defn generate-cache-key [n start end]
  (str "bitonic:" n ":" start ":" end))