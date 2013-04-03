(ns keyczar.cert-test
  (:use clojure.test
    keyczar.cert
    keyczar.test-helper))

(deftest create-test
  (let [cert (create "12345678")]
    (is (= (type cert) java.lang.String))))

(deftest reader-test
  (let [r (reader keyczar.test-helper/mock-key "12345678")]
    (is (=
      (type r)
      org.keyczar.PkcsKeyReader))
    (is (=
      (type (.getKey r))
      java.lang.String)))

  (is (thrown?
    org.keyczar.exceptions.KeyczarException
    (.getKey (reader keyczar.test-helper/mock-key "12")))))
