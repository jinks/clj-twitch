(ns clj-twitch.core
  (:require [clj-http.client :as client]))

(def base-url "https://api.twitch.tv/kraken")

(defn fetch-twitch
  "Fetch JSON from an API URL and return as clojure map."
  [url]
  (let [data (try (client/get url {:as :json
                                :socket-timeout 400
                                :conn-timeout 400
                                :retry-handler (fn [ex try-count http-context]
                                                 (if (> try-count 6) false true))})
               (catch Exception e nil))]
    (:body data)))
