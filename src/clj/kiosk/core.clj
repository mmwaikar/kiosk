(ns kiosk.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (route/files "/" {:root "resources/public"})
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))