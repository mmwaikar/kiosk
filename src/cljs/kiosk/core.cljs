(ns kiosk.core
  (:require [enfocus.core :as ef]
            [goog.net.XhrIo :as xhr])
  (:require-macros [enfocus.macros :as em]))

(defn replace-content [element cntnt]
  (em/at js/document
         element (em/content cntnt)))

;; ajax related methods
(defn receiver [event]
  (let [response (.-target event)]
    (replace-content ["#column1"] (.getResponseText response))))

(defn receiver-right [event]
  (let [response (.-target event)]
    (replace-content ["#column3"] (.getResponseText response))))

(defn show-ad-ajax [url content]
  (if-not (= (.indexOf url "google") -1)
    (xhr/send url receiver "GET" content)
    (xhr/send url receiver-right "GET" content)))

;; css-selector constants
(def *left-button* ["#left"])
(def *bottom-button* ["#bottom"])
(def *right-button* ["#right"])
(def *center-button* ["#center"])

(defn message [which]
  (str which " button has been clicked"))

(em/defaction show-ad [sel msg]
  sel (em/content msg))

(em/defaction setup []
  *left-button* (em/do->
                 (em/listen :mouseenter #(em/at (.-currentTarget %) (em/add-class "highlight")))
                 (em/listen :mouseleave #(em/at (.-currentTarget %) (em/remove-class "highlight")))
                 ;; (em/listen :click #(show-ad ["#column1"] (message "Left")))
                 (em/listen :click #(show-ad-ajax "http://localhost:3000/device/1/app/google" nil)))
  
  *bottom-button* (em/listen :click #(show-ad ["#row3"] (message "Bottom")))
  *right-button* (em/listen :click #(show-ad-ajax "http://localhost:3000/device/1/app/pizzahut" nil))
  ;; (em/listen :click #(show-ad ["#column3"] (message "Right")))
  *center-button* (em/do->
                   (em/listen :mouseenter #(em/at (.-currentTarget %) (em/add-class "btn-small")))
                   (em/listen :mouseleave #(em/at (.-currentTarget %) (em/remove-class "btn-small")))
                   (em/listen :click #(show-ad ["#column2"] (message "Center")))))

(defn start []
  (replace-content ["#column2"] "center"))

;; initialization
(em/defaction init []
  (setup)
  (start))

(set! (.-onload js/window)
      (init))