# keyczar-clj

A Clojure library that makes using keyczar a little easier.

## Terminology

- set - A named set of key versions. There will be a primary key that all encryption occurs with and some older versions that can be used to decrypt at any time.
- guard - This is a set of RSA keys. It is used to encrypt the user keys.
- user - This set of AES keys is encrypted on disk via. the guard key. Once you've used the guard key to unlock the user keys, it can be used to encrypt data of any length.

## Usage

The building block of keyczar encryption is the key set. This takes the form of a directory that looks somewhat like this:

    foo/
    foo/meta
    foo/1
    foo/2

You can think of the directory name as the name of the key. Then, the numbers are the different versions of the key.

# Create a new key set

'''clojure
(keyczar.set.create "/tmp/foo")
'''

This will create a brand new key at `/tmp/foo` and return the set for you. Note that it will be an AES key usable for encryption and decryption.

#


## License

Copyright © 2013 Thomas Rampelberg

Distributed under the Eclipse Public License, the same as Clojure.
