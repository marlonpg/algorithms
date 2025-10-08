(ns api.routes
  (:require
    [reitit.ring :as ring]
    [api.handlers :as handlers]))

(def app
  (ring/ring-handler
    (ring/router
      [["/hello" {:get handlers/hello}]])))
