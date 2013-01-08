(ns kiosk.core
  (:require [enfocus.core :as ef]
            [clojure.browser.repl :as repl])
  (:require-macros [enfocus.macros :as em]))

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
  *left-button* (em/listen :click #(show-ad ["#column1"] (message "Left")))
  *bottom-button* (em/listen :click #(show-ad ["#row3"] (message "Bottom")))
  *right-button* (em/listen :click #(show-ad ["#column3"] (message "Right")))
  *center-button* (em/listen :click #(show-ad ["#column2"] (message "Center"))))

(defn start []
  (em/at js/document
         ["#column2"] (em/content "center")))

;; initialization
(em/defaction init []
  (setup)
  (start))

(set! (.-onload js/window)
      #(do
         (repl/connect "http://localhost:9000/repl")
         (init)))