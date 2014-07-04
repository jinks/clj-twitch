(ns clj-twitch.stream
  (:require [clj-twitch.core :as core]))

(defn all
  "Fetches the whole stream map."
  [channel]
  (core/fetch-twitch (str core/base-url "/streams/" channel)))

(defn viewercount
  "Fetches current viewer count of a given stream map."
  [channel]
  (or (:viewers (:stream (all channel))) 0))
