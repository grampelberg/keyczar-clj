(ns keyczar.store
  (:require [clojure.java.io :as io])
  (:import (org.keyczar DefaultKeyType GenericKeyczar
                        Keyczar KeyczarFileReader KeyMetadata)
    (org.keyczar.enums KeyPurpose KeyStatus)))

(defn add-key [store]
  (.addVersion store (. KeyStatus PRIMARY))
  (save store))

(defn open [name]
  (GenericKeyczar. (KeyczarFileReader. name)))

(defn save [store]
  (.write store (str (.. store getMetadata getName) "/")))

(defn create [name]
  (.mkdirs (io/file name))
  (with-open [wrtr (io/output-stream (str name "/" "meta"))]
  (.write wrtr
    (..
      (KeyMetadata.
        name
        (. KeyPurpose DECRYPT_AND_ENCRYPT)
        (. DefaultKeyType RSA_PRIV))
      toString (getBytes (. Keyczar DEFAULT_ENCODING))))))

