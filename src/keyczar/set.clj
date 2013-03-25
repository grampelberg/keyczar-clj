(ns keyczar.set
  (:require [keyczar.metadata :as metadata])
  (:import (org.keyczar DefaultKeyType GenericKeyczar KeyczarEncryptedReader KeyczarFileReader)
    (org.keyczar.enums KeyPurpose KeyStatus)))

(defn reader [name]
  (KeyczarFileReader. name))

(defn open
  ([name] (GenericKeyczar. (reader name)))
  ([name guard]
    (KeyczarEncryptedReader. (reader name) guard)))

(defn create
  ([name] (create name "AES"))
  ([name type]
    (metadata/create name
      (eval (read-string (str "DefaultKeyType" "/" type))))
    (let [set (open name)]
      (.addVersion set (. KeyStatus PRIMARY))
      set)))

(defn location [set]
  (str (.. set getMetadata getName) "/"))

(defn save
  ([set] (.write set (location set)))
  ([set guard] (.writeEncrypted set (location set) guard)))