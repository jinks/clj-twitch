(ns clj-twitch.core
  (:require [org.httpkit.client :as client]
            [cheshire.core :as json]))

(def base-url "https://api.twitch.tv/kraken")

(defn fetch-twitch
  "Fetch JSON from an API URL and return as clojure map."
  [url]
  (let [data (try @(client/get url {
                                   ;setting a sensible timeout as Twitch is "twitchy"
                                   :timeout 800
                                   ;v3 API for more info
                                   :headers {"Accept" "application/vnd.twitchtv.v3+json"}})
               (catch Exception e nil))]
    (json/parse-string (:body data) true)))
