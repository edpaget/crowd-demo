(defproject crowd-demo "0.1.0-SNAPSHOT"
  :main crowd-demo.core
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :plugins [[lein-cljsbuild "0.3.0"]]
  :cljsbuild {
    :builds [{
             :source-paths ["src-cljs"]
             :compiler {
              :output-to "public/js/app.js"
              :optimizations :whitespace
              :pretty-print true}}]}
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.webbitserver/webbit "0.4.14"]
                 [com.taoensso/carmine "1.5.0"]])
