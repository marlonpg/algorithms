(ns api.routes
  (:require
    [reitit.ring :as ring]
    [api.handlers :as handlers]))

(def app
  (ring/ring-handler
    (ring/router
      [["/bitonic" {:get handlers/bitonic
                    :post handlers/bitonic}]
       ["/health" {:get handlers/health}]
       ["/cache/clear" {:delete handlers/clear-cache}]])))