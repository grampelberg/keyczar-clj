(ns keyczar.cert
  (:require
    [clojure.java.io :as io])
  (:import
    (org.keyczar RsaPrivateKey PkcsKeyReader)
    (org.keyczar.enums KeyPurpose RsaPadding)
    (org.keyczar.util Util)))

(defn reader [pkcs pass]
  (PkcsKeyReader.
    KeyPurpose/DECRYPT_AND_ENCRYPT
    (io/input-stream (.getBytes pkcs))
    RsaPadding/OAEP
    pass))

(defn create [pass]
  (.getPemString (. RsaPrivateKey generate RsaPadding/OAEP) pass))