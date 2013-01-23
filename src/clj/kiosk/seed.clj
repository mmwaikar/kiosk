(ns kiosk.seed
  (:use [kiosk.db])
  (:import [org.bson.types ObjectId]))

;; setup various devices
(def ^:dynamic *device1* {:name "Device 1" :type "Wide"})
(def ^:dynamic *device2* {:name "Device 2" :type "Tall"})
(def ^:dynamic *device3* {:name "Device 3" :type "Square"})
(def ^:dynamic *all-devices* [*device1* *device2* *device3*])

(defn- get-devices-with-ids
  []
  (mapv #(assoc % :_id (ObjectId.)) *all-devices*))

;; insert devices
(defn seed-devices
  []
  (connect-to-and-set-default-db *db-name*)
  (insert-batch *device-collection* (get-devices-with-ids)))