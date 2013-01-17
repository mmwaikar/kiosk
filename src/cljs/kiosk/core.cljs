(ns kiosk.core
  (:require [enfocus.core :as ef]
            [goog.net.XhrIo :as xhr])
  (:require-macros [enfocus.macros :as em]))

(defn replace-content [element cntnt]
  (em/at js/document
         element (em/content cntnt)))

;; ajax related methods
(defn get-ajax-response [event]
  (.getResponseText (.-target event)))

(defn get-ajax-response-and-update-node [node]
  #(replace-content node (get-ajax-response %)))

(defn send-ajax-request [url callback http-method content]
  (xhr/send url callback http-method content))

;; css-selector constants
(def *app1-button* ["#app1"])
(def *app2-button* ["#app2"])
(def *app-frame* ["#appframe"])

(def *left-button* ["#left"])
(def *bottom-button* ["#bottom"])
(def *right-button* ["#right"])
(def *center-button* ["#center"])

(defn message [which]
  (str which " button has been clicked"))

(em/defaction show-ad [sel msg]
  sel (em/content msg))

;; (defn start []
;;   ;; (replace-content ["#column2"] "center")
;;   #(em/at js/document
;;           ["#appframe"] (em/set-attr :src "https://rubygems.org")))

(em/defaction navigate-iframe-to [urlto]
  *app-frame* (em/set-attr :src urlto))

(defn show-ad-and-navigate [app-node callback http-method content]
  (let [values (em/from js/document
                        :ajaxto app-node (em/get-attr :ajax)
                        :urlto app-node (em/get-attr :url))]
    (send-ajax-request (:ajaxto values) callback http-method content)
    (navigate-iframe-to (:urlto values))))

(em/defaction setup []
  *app1-button* (em/do->
                 (em/listen :mouseenter #(em/at (.-currentTarget %) (em/add-class "highlight")))
                 (em/listen :mouseleave #(em/at (.-currentTarget %) (em/remove-class "highlight")))
                 (em/listen :click #(show-ad-and-navigate *app1-button* (get-ajax-response-and-update-node ["#toprow"]) "GET" nil)))

  *app2-button* (em/do->
                 (em/listen :mouseenter #(em/at (.-currentTarget %) (em/add-class "btn-small")))
                 (em/listen :mouseleave #(em/at (.-currentTarget %) (em/remove-class "btn-small")))
                 (em/listen :click #(show-ad-and-navigate *app2-button* (get-ajax-response-and-update-node ["#bottomrow"]) "GET" nil)))
  
  ;; *left-button* (em/do->
  ;;                (em/listen :mouseenter #(em/at (.-currentTarget %) (em/add-class "highlight")))
  ;;                (em/listen :mouseleave #(em/at (.-currentTarget %) (em/remove-class "highlight")))
  ;;                (em/listen :click #(send-ajax-request "http://localhost:3000/device/1/app/google"
  ;;                                                      (get-ajax-response-and-update-node ["#column1"]) "GET" nil)))
  
  ;; *bottom-button* (em/listen :click #(show-ad ["#row3"] (message "Bottom")))
  
  ;; *right-button* (em/listen :click #(send-ajax-request "http://localhost:3000/device/1/app/pizzahut"
  ;;                                                      (get-ajax-response-and-update-node ["#column3"]) "GET" nil))  
  ;; *center-button* (em/do->
  ;;                  (em/listen :mouseenter #(em/at (.-currentTarget %) (em/add-class "btn-small")))
  ;;                  (em/listen :mouseleave #(em/at (.-currentTarget %) (em/remove-class "btn-small")))
  ;;                  ;; (em/listen :click #(start))
  ;;                  )
  )

;; initialization
(em/defaction init []
  (setup)
  ;; (start)
  )

(set! (.-onload js/window)
      (init))