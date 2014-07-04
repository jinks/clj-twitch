(ns clj-twitch.user
  (:require [clj-twitch.core :as core]))

(defn all
  "Fetches the whole user map."
  [user]
  (core/fetch-twitch (str core/base-url "/users/" user)))

(defn follows
  "Fetches all the channels the user is following."
  [user]
  (core/fetch-twitch (str core/base-url "/users/" user "/follows?limit=1000")))
