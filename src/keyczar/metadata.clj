(ns keyczar.metadata
  (:require [clojure.java.io :as io])
  (:import
    (org.keyczar Keyczar KeyMetadata KeyVersion)
    (org.keyczar.enums KeyPurpose KeyStatus)))

(defn generate [name]
  (KeyMetadata. "memory" KeyPurpose/DECRYPT_AND_ENCRYPT
    DefaultKeyType/RSA_PRIV))

(defn add-version [metadata]
  (.addVersion metadata (KeyVersion. 1 KeyStatus/PRIMARY false))
  metadata)

(defn file [name type]
  "Note that type is what kind of key to use for this store. (. DefaultKeyType AES)"
  (.mkdirs (io/file name))
  (with-open [wrtr (io/output-stream (str name "/" "meta"))]
  (.write wrtr
    (..
      (KeyMetadata.
        name (. KeyPurpose DECRYPT_AND_ENCRYPT) type)
      toString (getBytes (. Keyczar DEFAULT_ENCODING))))))