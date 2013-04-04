(ns keyczar.set.user-test
  (:use clojure.test
    keyczar.set.user
    keyczar.test-helper))

(deftest create-test
  (let [g (keyczar.set.guard/open
    keyczar.test-helper/mock-key
    "12345678")]
    (is (=
      (type (create "tmp/foo" g))
      org.keyczar.Crypter))
    (is (not (re-find
      #"PRIVATE"
      (slurp "tmp/foo/1")
      )))))

(deftest open-test
  (let [g (keyczar.set.guard/open
    keyczar.test-helper/mock-key
    "12345678")]
    (let [u (create "tmp/foo" g)]
      (is (=
        (type (open "tmp/foo" g))
        org.keyczar.Crypter))
      (is (=
        (.decrypt u (.encrypt (open "tmp/foo" g) "asdf"))
        "asdf")))))