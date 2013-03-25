(ns keyczar.set.user
  (:require [keyczar.set :as set])
  (:import (org.keyczar Crypter DefaultKeyType)))

(defn open [name guard]
  (Crypter. (set/reader name guard)))

(defn create [name guard]
  (set/save (set/create name) guard)
  (open name guard))