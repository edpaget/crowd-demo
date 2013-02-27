(ns crowd-demo.websocket
    (:require [goog.events :as events]
              [goog.events.EventHandler :as ev-handler]
              [goog.net.WebSocket :as ws]
              [goog.net.WebSocket.EventType :as ws-event]
              [goog.net.WebSocket.MessageEvent :as ws-message]))

(defn create [] (goog.net.WebSocket.))

(defn configure
  "Adds Event Listeners to WebSocket"
  ([soc message]
    (configure soc message nil nil nil))
  ([soc message opened]
    (configure soc message opened nil nil))
  ([soc message opened error]
    (configure soc message opened error nil))
  ([soc message opened error closed]
    (let [handler (goog.events.EventHandler.)]
      (.listen handler soc ws-event/MESSAGE
               (fn [payload] (message payload)))
      (when opened
        (.listen handler soc ws-event/OPENED opened))
      (when error
        (.listen handler soc ws-event/ERROR error))
      (when closed
        (.listen handler soc ws-event/CLOSED closed)))
    soc))

(defn open!
  "Opens a WebSocket or prints a warning to console if it fails"
  [soc url]
  (try
    (.open soc url)
    soc
    (catch js/Error e
           (.log js/console "Your Browser Doesn't Support WebSockets" e))))

(defn close!
  "Terminates WebSocket"
  [soc]
  (.close soc))

(defn emit!
  "Send a message on the websocket"
  [soc message]
  (.send soc message))
