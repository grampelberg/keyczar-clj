(ns keyczar.store
  (:require [clojure.java.io :as io])
  (:import (org.keyczar Keyczar KeyMetadata DefaultKeyType)
    (org.keyczar.enums KeyPurpose)))

(defn save [store]
  (with-open [wrtr (io/output-stream (.getName store))]
  (.write wrtr
    (.. store toString (getBytes (. Keyczar DEFAULT_ENCODING))))))

(defn create [name]
  (save (KeyMetadata.
    name
    (. KeyPurpose DECRYPT_AND_ENCRYPT)
    (. DefaultKeyType RSA_PRIV))))
