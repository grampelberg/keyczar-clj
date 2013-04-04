(defproject keyczar "0.1.0-SNAPSHOT"
  :description "Clojure wrapper for keyczar"
  :url "https://github.com/pyronicide/keyczar-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.keyczar/keyczar "0.71f.1"]]
  :repositories [["keyczar" "https://raw.github.com/pyronicide/keyczar/mvn-repo/"]]
  :main keyczar.core
)
