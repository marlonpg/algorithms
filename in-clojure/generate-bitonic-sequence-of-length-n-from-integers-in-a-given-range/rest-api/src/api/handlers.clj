(ns api.handlers
  (:require [cheshire.core :as json]))

(defn hello [_]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string {:message "Hello, world!"})})
