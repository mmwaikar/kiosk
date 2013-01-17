(ns kiosk.index
  (:require [net.cgrand.enlive-html :as html]))

;; ===================================================================
;; helper functions
;; ===================================================================

(defn- map-for
  [show-top-row show-bottom-row show-left-col show-right-col]
  (hash-map :show-top-row show-top-row
            :show-bottom-row show-bottom-row
            :show-left-col show-left-col
            :show-right-col show-right-col))

(defn show-row-cols-for [device]
  ;; (prn (str "from show-row-cols-for " device))
  (cond
   (= device 1) (map-for false false true true)
   (= device 2) (map-for true true false false)
   (= device 3) (map-for true true true true)))

;; ===================================================================
;; transformation related functions
;; ===================================================================

(defn- what-height [show]
  ;; (prn (str "from what-height " show))
  (if (true? show)
    100
    0))

(defn- transform-row
  [row-selector height]
  (prn (str "from transform-row " row-selector ", height " height))
  (if (= height 0)
    (do
      (html/set-attr :style (str "height: " height "px;"))
      (html/add-class "hidden"))
    (do
      (html/set-attr :style (str "height: " height "px;"))
      (html/remove-class "hidden"))))

(defn- transform-col
  [col-selector height]
  (prn (str "from transform-col " col-selector ", height " height))
  (if (= height 0)
    (html/remove-class "span2")
    (html/add-class "span2")))

;; (html/defsnippet top-row "index.html" [:body#main]
;;   [{:keys [show-top-row show-bottom-row show-left-col show-right-col]}]
;;   [:div#toprow] (transform-row-col [:div#toprow] (what-height show-top-row))
;;   [:div#bottomrow] (transform-row-col [:div#bottomrow] (what-height show-bottom-row))
;;   [:div#leftcolumn] (transform-row-col [:div#leftcolumn] (what-height show-left-col))
;;   [:div#rightcolumn] (transform-row-col [:div#rightcolumn] (what-height show-right-col)))

(html/deftemplate index "../resources/public/index.html"
  [{:keys [show-top-row show-bottom-row show-left-col show-right-col]}]
  [:div#toprow] (transform-row [:div#toprow] (what-height show-top-row))
  [:div#bottomrow] (transform-row [:div#bottomrow] (what-height show-bottom-row))
  [:div#leftcolumn] (transform-col [:div#leftcolumn] (what-height show-left-col))
  [:div#rightcolumn] (transform-col [:div#rightcolumn] (what-height show-right-col)))
