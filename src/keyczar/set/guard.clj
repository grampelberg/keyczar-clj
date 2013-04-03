(ns keyczar.set.guard
  (:require [keyczar.cert :as cert])
  (:import (org.keyczar Crypter)))

(defn open [pkcs pass]
  (Crypter. (cert/reader pkcs pass)))
