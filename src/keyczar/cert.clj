(ns keyczar.cert
  (:require [clojure.data.codec.base64 :as b64])
  (:import (java.security KeyFactory)
    (java.security.interfaces RSAPrivateCrtKey)
    (java.security.spec PKCS8EncodedKeySpec)
    (javax.crypto Cipher EncryptedPrivateKeyInfo SecretKeyFactory)
    (javax.crypto.spec PBEKeySpec PBEParameterSpec)
    (org.keyczar RsaPrivateKey)
    (org.keyczar.enums RsaPadding)))

(defn der [pkcs]
  (b64/decode (.getBytes
    (clojure.string/join
      (clojure.string/split-lines
        (clojure.string/replace pkcs #"-----.*-----\n" ""))))))

(defn decryption-key [pass algname]
  (.generateSecret (SecretKeyFactory/getInstance algname)
    (PBEKeySpec. (.toCharArray pass))))

(defn data [pkcs pass]
  (let [enckey (EncryptedPrivateKeyInfo. (der pkcs))]
    (let [algname (.getAlgName enckey)
      cipher (Cipher/getInstance algname)]
      (.init cipher Cipher/DECRYPT_MODE (decryption-key pass algname)
        (.getParameterSpec (.getAlgParameters enckey)
          PBEParameterSpec))
      (.doFinal cipher (.getEncryptedData enckey)))))

(defn get-key [pkcs pass]
  (RsaPrivateKey.
    (cast RSAPrivateCrtKey
      (.generatePrivate (KeyFactory/getInstance "RSA")
        (PKCS8EncodedKeySpec. (data pkcs pass))))
    RsaPadding/OAEP))
