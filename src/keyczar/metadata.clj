(ns keyczar.metadata
  (:require [clojure.java.io :as io])
  (:import
    (org.keyczar Keyczar KeyMetadata)
    (org.keyczar.enums KeyPurpose)))

(defn create [name type]
  "Note that type is what kind of key to use for this store. (. DefaultKeyType AES)"
  (.mkdirs (io/file name))
  (with-open [wrtr (io/output-stream (str name "/" "meta"))]
  (.write wrtr
    (..
      (KeyMetadata.
        name (. KeyPurpose DECRYPT_AND_ENCRYPT) type)
      toString (getBytes (. Keyczar DEFAULT_ENCODING))))))