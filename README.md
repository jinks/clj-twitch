# clj-twitch

A thin clojure wrapper around the twitch.tv REST API.

## Usage

API functions will be added as I need them, consider everything
pretty much in flux.

### Example
```Clojure
(require '[clj-twitch.core :as twitch])

(twitch/game "ksptv")
;=> "Kerbal Space Program"
```

## License

Copyright © 2014 Jinks

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
