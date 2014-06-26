(ns clj-twitch.core
  (:require [clj-http.client :as client]))

(def base-url "https://api.twitch.tv/kraken")

(defn fetch-twitch
  "Fetch JSON from an API URL and return as clojure map."
  [url]
  (let [data (try (client/get url {:as :json
                                   ;setting a sensible timeout as Twitch is "twitchy"
                                   :socket-timeout 800
                                   :conn-timeout 800
                                   ;v2 API only for now
                                   :headers {"Accept" "application/vnd.twitchtv.v2+json"}
                                   :retry-handler (fn [ex try-count http-context]
                                               (if (> try-count 6) false true))})
               (catch Exception e nil))]
    (:body data)))
