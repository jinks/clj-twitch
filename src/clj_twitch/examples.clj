;These functions represent but a small portion of what you can do with the
;Twitch REST API. Their only purpose now is to give you a general idea how
;to use twitch information in a creative way. They were originally written
;to test the code.

(ns clj-twitch.examples
  (:require [clj-twitch.channel :as ch]
            [clj-twitch.stream :as st]
            [clj-time.core :as time]
            [clj-time.format :as format]))

(defn summary
  "Returns a small summary of a given channel (name, played game, status, number of viewers and followers)."
  [channel]
  (let [smap (st/all channel)
        cmap (or (:channel (:stream smap)) (ch/all channel))
        cname (:display_name cmap)
        cgame (:game cmap)
        cstatus (:status cmap)
        vcount (or (:viewers (:stream smap)) 0)
        fcount (:followers cmap)]
  (str cname " playing " cgame " (" cstatus ") - " vcount " viewer"
       (if (> vcount 1) "s") ", " fcount " follower" (if (> fcount 1) "s"))))

(defn lastvictim
  "Returns the last user to follow a given channel and the time that has elapsed since then."
  [channel]
  (let [standard-formatter (format/formatters :date-time-no-ms)
        lfollow (ch/followpretty (ch/lastfollow channel))
        elapsed (time/in-minutes
                 (time/interval
                  (format/parse standard-formatter
                   (ch/followdate (ch/lastfollow channel)))
                  (time/now)))
        cname (:display_name (ch/all channel))]
    (letfn [(in-days-hours
             [minutes]
             (let [days (quot (quot minutes 60) 24)
                   hours (mod (quot minutes 60) 24)]
               (str (if (> minutes 1439) (str days " day" (if (> days 1) "s") " "))
                    (if (> minutes 59) (str hours " hour" (if (> hours 1) "s") " "))
                    (mod minutes 60) " minute" (if (> minutes 1) "s")))
             )]
      (str lfollow " followed " cname " " (in-days-hours elapsed) " ago!"))))
