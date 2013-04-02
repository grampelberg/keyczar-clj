(ns keyczar.set.guard
  (:require [keyczar.set :as set]
    [keyczar.reader :as reader])
  (:import (org.keyczar Crypter GenericKeyczar PkcsKeyReader)
    (org.keyczar.enums KeyPurpose RsaPadding)
    (java.io ByteArrayInputStream)))

(defn from-cert [pkcs pass]
  (Crypter. (reader/memory pkcs pass)))

(defn export [name passphrase]
  (let [source (set/open name)]
    (.getPemString (.getPrimaryKey source) passphrase)))

(defn open [name]
  (Crypter. (set/reader name)))

(defn create [name]
  (set/save (set/create name "RSA_PRIV"))
  (open name))