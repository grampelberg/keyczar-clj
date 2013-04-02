(ns keyczar.reader
  (:require [keyczar.cert :as cert])
  (:import (org.keyczar DefaultKeyType KeyMetadata)
    (org.keyczar.enums KeyPurpose)))

(defn metadata []
  (KeyMetadata. "memory"
    KeyPurpose/DECRYPT_AND_ENCRYPT
    DefaultKeyType/RSA_PRIV))

(defn memory [pkcs passphrase]
  (let [k (cert/get-key pkcs passphrase)]
    (proxy [org.keyczar.interfaces.KeyczarReader] []
      (getKey [v] (.toString k))
      (getMetadata [] (.toString (metadata))))))
