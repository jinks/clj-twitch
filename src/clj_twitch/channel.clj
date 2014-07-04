(ns clj-twitch.channel
  (:require [clj-twitch.core :as core]))

(defn all
  "Fetches the whole channel map."
  [channel]
  (core/fetch-twitch (str core/base-url "/channels/" channel)))

(defn pretty
  "Fetches the displayname of a given channel."
  [channel]
  (:display_name (all channel)))

(defn status
  "Fetches the current status message of a given channel."
  [channel]
  (:status (all channel)))

(defn game
  "Fetches the active game of a given channel."
  [channel]
  (:game (all channel)))

(defn followercount
  "Fetches the Followercount of a given channel."
  [channel]
  (or (:followers (all channel)) 0))

(defn firstfollow
  "Returns a map of the first follow of a given channel."
  [channel]
  (peek
   (:follows
    (core/fetch-twitch (str core/base-url "/channels/" channel "/follows?direction=ASC&limit=1&offset=0")))))

(defn lastfollow
  "Returns a map of the last follow of a given channel."
  [channel]
  (peek
   (:follows
    (core/fetch-twitch (str core/base-url "/channels/" channel "/follows?direction=DESC&limit=1&offset=0")))))

(defn followpretty
  "Return the displayname from a user of a given follow map."
  [follow]
  (:display_name (:user follow)))

(defn followdate
  "Returns the date of a given follow map."
  [follow]
  (:created_at follow))
