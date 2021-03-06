(defproject clj-twitch "0.1.0-SNAPSHOT"
  :description "A thin clojure wrapper around the twitch.tv REST API."
  :url "https://github.com/jinks/clj-twitch"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-http "0.9.2"]
                 [cheshire "5.3.1"]
                 [http-kit "2.1.16"]
                 [clj-time "0.7.0"]])
