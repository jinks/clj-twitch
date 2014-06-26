(ns clj-twitch.channel
  (:require [clj-twitch.core :as core]))

(defn game
  "Fetches the active game of a given channel."
  [channel]
  (:game (core/fetch-twitch (str core/base-url "/channels/" channel))))

(defn followercount
  [channel]
  "Fetches the Followercount of a given channel."
  (:followers (core/fetch-twitch (str core/base-url "/channels/" channel))))

(defn pretty
  "Fetches the displayname of a given channel."
  [channel]
  (:display_name (core/fetch-twitch (str core/base-url "/channels/" channel))))

(defn status
  [channel]
  "Fetches the current status message of a given channel."
  (:status (core/fetch-twitch (str core/base-url "/channels/" channel))))

(defn all
  "Fetches the whole channel map."
  [channel]
  (core/fetch-twitch (str core/base-url "/channels/" channel)))