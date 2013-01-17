(ns kiosk.core
  (:use [compojure.core]
        [kiosk.index])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [ring.middleware.resource :as rmr]))

(defn which-ad [app]
  (cond
   (= app "pizzahut") {:name "Mankar Dosa Centre" :url "http://www.mankardosacentre.com/"} 
   (= app "mcdonalds") {:name "Subway" :url "http://www.subway.co.in/"}
   (= app "walmart") {:name "Big Bazaar" :url "http://www.bigbazaar.com/"}
   (= app "sears") {:name "Ikea" :url "http://www.ikea.com/"}
   (= app "joshsoftware") {:name "Oracle" :url "http://www.oracle.com/"}
   (= app "37signals") {:name "Microsoft" :url "http://www.microsoft.com"}))

(defroutes app-routes  
  (GET "/" [] (resp/redirect "welcome.html"))

  ;; for individual devices
  (GET "/device/:dev" [dev]
       (resp/file-response (str "device" dev ".html") {:root "resources/public"}))
  
  ;; (GET "/device/:dev" [dev]
  ;;      ;; (resp/file-response "index.html" {:root "resources/public"})
  ;;      ;; (prn (str "for device: " dev))
  ;;      (let [req-map (show-row-cols-for (Integer/parseInt dev))]
  ;;        (prn req-map)
  ;;        ;; (rmr/wrap-resource (resp/response (index req-map)) "../resources/public/main.css")
  ;;        (resp/response (index req-map))
  ;;        ))
  
  (GET "/device/:dev/app/:app" [dev app] (prn "dev: " dev ", app: " app)
       (let [resp-str (str "App " app " on device " dev " was clicked. Showing add for " (:name (which-ad app)))]
         (-> (resp/response resp-str)
             (resp/content-type "text/plain"))))
  
  (route/files "/" {:root "resources/public"})
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))