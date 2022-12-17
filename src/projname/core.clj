(ns ##name##.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(def data-file (io/resource "hello.txt" ))

(defn -main [& args]
  (println  (slurp data-file))
  (println "Empty template.."))
