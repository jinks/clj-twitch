(ns clj-twitch.stream
  (:require [clj-twitch.core :as core]))

(defn viewercount
  "Fetches current viewer count of the stream of a given channel."
  [channel]
  (or (:viewers (:stream (core/fetch-twitch (str core/base-url "/streams/" channel))) nil) 0))