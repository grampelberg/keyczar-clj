# keyczar-clj

A Clojure library that makes using keyczar a little easier.

## Terminology

- set - A named set of key versions. There will be a primary key that all encryption occurs with and some older versions that can be used to decrypt at any time.
- guard - This is a set of RSA keys. It is used to encrypt the user keys.
- user - This set of AES keys is encrypted on disk via. the guard key. Once you've used the guard key to unlock the user keys, it can be used to encrypt data of any length.

## Usage

## License

Copyright © 2013 Thomas Rampelberg

Distributed under the Eclipse Public License, the same as Clojure.
