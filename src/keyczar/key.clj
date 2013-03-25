(ns keyczar.key
  (:import (org.keyczar Crypter KeyczarFileReader)))

(defn open [name]
  (Crypter. (KeyczarFileReader. name)))

(defn encrypt [key msg]
  (.encrypt key msg))

(defn decrypt [key msg]
  (.decrypt key msg))