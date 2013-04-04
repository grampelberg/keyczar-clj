(ns keyczar.cert
  (:require
    [clojure.java.io :as io])
  (:import (org.keyczar DefaultKeyType PkcsKeyReader)
    (org.keyczar.enums KeyPurpose RsaPadding)))

(defn reader [pkcs pass]
  (PkcsKeyReader.
    KeyPurpose/DECRYPT_AND_ENCRYPT
    (io/input-stream (.getBytes pkcs))
    RsaPadding/OAEP
    pass))

(defn generate []
  (.generate
    (. DefaultKeyType/RSA_PRIV getBuilder)
    (. DefaultKeyType/RSA_PRIV applyDefaultParameters nil)))

(defn create [pass]
  (.getPemString (generate) pass))
