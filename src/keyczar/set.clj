(ns keyczar.set
  (:require [keyczar.metadata :as metadata])
  (:import
    (org.keyczar
      DefaultKeyType
      GenericKeyczar
      KeyczarEncryptedReader
      KeyczarFileReader)
    (org.keyczar.enums KeyPurpose KeyStatus)))

(defn reader
  ([name] (KeyczarFileReader. name))
  ([name guard] (KeyczarEncryptedReader. (reader name) guard)))

(defn open
  ([name] (GenericKeyczar. (reader name)))
  ([name guard] (GenericKeyczar. (reader name guard))))

;; Allow creation of different key types here
(defn create [name]
  (metadata/file name DefaultKeyType/AES)
  (let [set (open name)]
    (.addVersion set KeyStatus/PRIMARY)
    set))

(defn location [set]
  (str (.. set getMetadata getName) "/"))

(defn save
  ([set] (.write set (location set)))
  ([set guard] (.writeEncrypted set (location set) guard)))