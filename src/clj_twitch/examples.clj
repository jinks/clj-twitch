;These functions represent but a small portion of what you can do with the Twitch REST API. Their only purpose is to
;give you a general idea how to use twitch information in a creative way.

(ns clj-twitch.examples
  (:require [clj-twitch.channel :as channel]
            [clj-twitch.stream :as stream]
            [clj-time.core :as time]
            [clj-time.format :as format]))

(defn summary
  [channel]
  "Returns a small summary of a given channel (name, played game, status, number of viewers and followers)."
  (let [cname (channel/pretty channel)
        cgame (channel/game channel)
        cstatus (channel/status channel)
        vcount (stream/viewercount channel)
        fcount (channel/followercount channel)]
  (str cname " playing " cgame " (" cstatus ") - " vcount " viewer"
       (if (> vcount 1) "s") ", " fcount " follower" (if (> fcount 1) "s"))))

(defn lastvictim
  [channel]
  "Returns the last user to follow a given channel and the time that has elapsed since then."
  (let [standard-formatter (format/formatters :date-time-no-ms)
        lfollow (channel/followpretty (channel/lastfollow channel))
        elapsed (time/in-minutes
                 (time/interval
                  (format/parse standard-formatter
                   (channel/followdate (channel/lastfollow channel)))
                  (time/now)))
        cname (channel/pretty channel)]
    (letfn [(in-days-hours
             [minutes]
             (let [days (quot (quot minutes 60) 24)
                   hours (mod (quot minutes 60) 24)]
               (str (if (> minutes 1439) (str days " day" (if (> days 1) "s") " "))
                    (if (> minutes 59) (str hours " hour" (if (> hours 1) "s") " "))
                    (mod minutes 60) " minute" (if (> minutes 1) "s")))
             )]
      (str lfollow " followed " cname " " (in-days-hours elapsed) " ago!"))))
