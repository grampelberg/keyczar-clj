(ns keyczar.reader
  (:require [keyczar.cert :as cert]
    [keyczar.metadata :as metadata]))

(defn from-cert [pkcs passphrase]
  (let [k (cert/get-key pkcs passphrase)]
    (proxy [org.keyczar.interfaces.KeyczarReader] []
      (getKey [v] (.toString k))
      (getMetadata []
        (.toString (metadata/add-version
          (metadata/generate "memory")))))))
