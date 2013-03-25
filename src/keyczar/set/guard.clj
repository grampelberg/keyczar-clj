(ns keyczar.set.guard
  (:require [keyczar.set :as set])
  (:import (org.keyczar Crypter GenericKeyczar PkcsKeyReader)
    (org.keyczar.enums KeyPurpose RsaPadding)
    (java.io ByteArrayInputStream)))

; (defn import [name passphrase]
;   )

(defn from-cert [cert passphrase]
  (GenericKeyczar. (PkcsKeyReader.
    (. KeyPurpose DECRYPT_AND_ENCRYPT)
    (ByteArrayInputStream. (.getBytes cert))
    (. RsaPadding OAEP)
    passphrase)))

(defn export [name passphrase]
  (let [source (set/open name)]
    (.getPemString (.getPrimaryKey source) passphrase)))

(defn open [name]
  (Crypter. (set/reader name)))

(defn create [name]
  (set/save (set/create name "RSA_PRIV"))
  (open name))