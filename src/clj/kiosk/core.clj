(ns kiosk.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]))

(defn which-ad [app]
  (if (= app "google")
    "yahoo"
    "subway"))

(defroutes app-routes
  (GET "/" [] (resp/file-response "welcome.html" {:root "resources/public"}))
  (GET "/device/:dev/app/:app" [dev app]
       (let [resp-str (str "App " app " on device " dev " was clicked. Showing add for " (which-ad app))]
         (-> (resp/response resp-str)
             (resp/content-type "text/plain"))))
  (route/files "/" {:root "resources/public"})
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))