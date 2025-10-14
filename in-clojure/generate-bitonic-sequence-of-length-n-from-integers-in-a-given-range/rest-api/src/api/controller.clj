(ns api.controller
  (:require
    [reitit.ring :as ring]
    [api.bitonic-service :as bitonic-service]))

(def app
  (ring/ring-handler
    (ring/router
      [["/bitonic" {:get bitonic-service/bitonic
                    :post bitonic-service/bitonic}]
       ["/health" {:get bitonic-service/health}]
       ["/cache/clear" {:delete bitonic-service/clear-cache}]])))