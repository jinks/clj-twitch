(ns clj-twitch.core
  (:require [org.httpkit.client :as client]
            [cheshire.core :as json]))

(def base-url "https://api.twitch.tv/kraken")   ;ideally this will not change ever
(def client-id nil)                             ;set to avoid rate limits

(defn fetch-twitch
  "Fetch JSON from an API URL and return as clojure map."
  [url]
  (let [data (try @(client/get url {
                                   ;setting a sensible timeout as Twitch is "twitchy"
                                   :timeout 800
                                   ;v3 API for more info
                                   :headers {"Accept" "application/vnd.twitchtv.v3+json"
                                             ;don't give me old data kthxbye
                                             "Cache-Control" "no-cache"
                                             "Client-ID" (if-not (empty? client-id) (str client-id) "")}})
               (catch Exception e nil))]
    (json/parse-string (:body data) true)))
