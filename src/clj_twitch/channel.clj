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

(defn firstfollow
  [channel]
  "Returns a map of the first follow of a given channel."
  (peek
   (:follows
    (core/fetch-twitch (str core/base-url "/channels/" channel "/follows?direction=ASC&limit=1&offset=0")))))

(defn lastfollow
  [channel]
  "Returns a map of the last follow of a given channel."
  (peek
   (:follows
    (core/fetch-twitch (str core/base-url "/channels/" channel "/follows?direction=DESC&limit=1&offset=0")))))

(defn followpretty
  [follow]
  "Return the displayname from a user of a given follow map."
  (:display_name (:user follow)))

(defn followdate
  [follow]
  "Returns the date of a given follow map."
  (:created_at follow))

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
