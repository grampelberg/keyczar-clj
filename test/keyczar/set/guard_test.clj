(ns keyczar.set.guard-test
  (:use clojure.test
    keyczar.set.guard
    keyczar.test-helper))

(deftest open-test
  (is (thrown?
    org.keyczar.exceptions.KeyczarException
    (open keyczar.test-helper/mock-key "1234")))
  (let [g (open keyczar.test-helper/mock-key "12345678")]
    (is (=
      (type g)
      org.keyczar.Crypter))
    (is (=
      (type (.encrypt g "asdf"))
      java.lang.String))
    (is (=
      "asdf"
      (.decrypt g (.encrypt g "asdf"))))))