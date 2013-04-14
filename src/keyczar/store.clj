(ns keyczar.store
  (:require [keyczar.metadata :as metadata]
    [keyczar.padlock :as padlock])
  (:import (org.keyczar
    DefaultKeyType GenericKeyczar KeyczarEncryptedReader KeyczarFileReader)))

(defn- reader [name padlock]
  (KeyczarEncryptedReader. (KeyczarFileReader. name) padlock))

(defn open[name padlock]
  (GenericKeyczar. (reader name padlock)))

(defn create [name]
  (metadata/create name (. DefaultKeyType AES))
  (let [store (GenericKeyczar. name)]
    (.addVersion store (. KeyStatus PRIMARY))
    store))

(defn save [store padlock]
  (.writeEncrypted
    store (str (.. store getMetadata getName) "/") padlock))






  ; (:require [clojure.java.io :as io])
  ; (:import (org.keyczar DefaultKeyType GenericKeyczar
  ;                       Keyczar KeyczarFileReader KeyMetadata)
  ;   (org.keyczar.enums KeyPurpose KeyStatus)))

; (defn add-key [store]
;   (.addVersion store (. KeyStatus PRIMARY))
;   (save store))

; (defn open [name]
;   (GenericKeyczar. (KeyczarFileReader. name)))



; (defn save [store]
;   (.write store (str (get-name store) "/")))

; (defn save-encrypted [store key]
;   (.writeEncrypted store (str (get-name store) "/") key))



; (defn stuff [store]
;   (.addVersion store (. KeyStatus PRIMARY))
;   (save-encrypted store
;     (org.keyczar.Encrypter. (KeyczarFileReader. "foo"))))