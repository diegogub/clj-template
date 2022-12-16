(ns build
  (:require [clojure.tools.build.api :as b]))

(def version "0.0.1")
(def app-name "projname")
(def main-ns 'projname.core)

(def build-folder "target")
(def jar-content (str build-folder "/classes"))

(def basis (b/create-basis {:project "deps.edn"}))
(def uber-file-name (format "%s/%s-%s.jar" build-folder app-name version)) ; path for result uber file

(defn clean [_]
  (b/delete {:path "target"})
  (println (format "Build folder \"%s\" removed" build-folder)))

(defn uber [_]
  (clean nil)

  (b/compile-clj {:basis     basis               ; compile clojure code
                  :src-dirs  ["src/"]
                  :class-dir jar-content})

  (b/uber {:class-dir jar-content                ; create uber file
           :uber-file uber-file-name
           :basis     basis
           :main      main-ns })                ; here we specify the entry point for uberjar
  
  (println (format "Uber file created: \"%s\"" uber-file-name)))
