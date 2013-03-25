(ns keyczar.set.guard
  (:require [keyczar.set :as set])
  (:import (org.keyczar Crypter)))

(defn open [name]
  (Crypter. (set/reader name)))

(defn create [name]
  (set/create name "RSA_PRIV")
  (open name))