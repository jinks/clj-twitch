(ns clj-twitch.core
  (:require [clj-http.client :as client]))

(def base-url "https://api.twitch.tv/kraken")

(defn fetch-twitch
  "Fetch JSON from an API URL and return as clojure map."
  [url]
  (let [data (try (client/get url {:as :json
                                :retry-handler (fn [ex try-count http-context]
                                                 (if (> try-count 6) false true))})
               (catch Exception e nil))]
    (:body data)))

(defn game
  "Fetches the active game of a given channel."
  [channel]
  (:game (fetch-twitch (str base-url "/channels/" channel))))

(defn pretty
  "Fetches the displayname of a given channel."
  [channel]
  (:display_name (fetch-twitch (str base-url "/channels/" channel))))

(defn all
  "Fetches the whole channel map."
  [channel]
  (fetch-twitch (str base-url "/channels/" channel)))
