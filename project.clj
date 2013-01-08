(defproject kiosk "0.1.0-SNAPSHOT"
  :description "Kiosk application written using Clojure and ClojureScript."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  ;; CLJ source code path
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.3"]
                 [enlive "1.0.1"]
                 [enfocus "1.0.0-beta2"]]

  ;; lein-cljsbuild plugin to build a CLJS project
  :plugins [[lein-cljsbuild "0.2.10"]
            [lein-ring "0.7.5"]]

  :ring {:handler kiosk.core/app}

  :profiles {:dev {:dependencies [[ring-mock "0.1.3"]]}}

  ;; cljsbuild options configuration
  :cljsbuild {:builds
              [{;; CLJS source code path
                :source-path "src/cljs"

                ;; Google Closure (CLS) options configuration
                :compiler {;; CLS generated JS script filename
                           :output-to "resources/public/js/kiosk.js"

                           ;; minimal JS optimization directive
                           :optimizations :whitespace

                           ;; generated JS code prettyfication
                           :pretty-print true}}]})
