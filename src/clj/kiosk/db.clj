(ns kiosk.db
  (:require [monger.core :as mg]
            [monger.collection :as mgc]))

;; (def ^:dynamic *default-host* "localhost")
;; (def ^:dynamic *default-port* 7878)
(def ^:dynamic *db-name* "kiosk")
(def ^:dynamic *device-collection* "devices")

(defn connect
  ([] (mg/connect!))
  ([host port] (mg/connect! {:host host :port port})))

(defn disconnect
  []
  (mg/disconnect!))

(defn connect-to-and-set-default-db
  [db-name]
  (connect)
  (mg/set-db! (mg/get-db db-name)))

(defn insert-batch
  [collection-name vector-of-doc-maps]
  (mgc/insert-batch collection-name vector-of-doc-maps))