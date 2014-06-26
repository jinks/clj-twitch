(ns clj-twitch.examples
  (:require [clj-twitch.channel :as channel] [clj-twitch.stream :as stream]))

(defn summary
  [channel]
  "Returns a small summary of a given channel (name, played game, status, number of viewers and followers)."
  (def cname (channel/pretty channel))
  (def cgame (channel/game channel))
  (def cstatus (channel/status channel))
  (def vcount (stream/viewercount channel))
  (def fcount (channel/followercount channel))
  (str cname " playing " cgame " (" cstatus ") - " vcount
       (if (< vcount 2) " viewer," " viewers, ") fcount (if (< fcount 2) " follower" " followers")))