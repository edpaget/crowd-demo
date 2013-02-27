(ns crowd-demo.sender
    (:require [crowd-demo.websocket :as ws]
     [goog.dom :as dom]
     [goog.events :as events]))

(defn do-send-button-clicked
  "When the send button is clicked read from the input field
   and send data over websocket connection"
  [soc]
  (let [message (dom/getElement "message")]
    (ws/emit! soc message/value)))

(defn do-canvas-mousedown
  "Adds a mousemouse listener and a mouseup listener"
  [ev]
  (.log js/console ev))

(defn start-sender 
  "Set Event Listeners for the Sender Page"
  []
  (do 
    (let [button (dom/getElement "send-erase")
          canvas (dom/getElement "subject-canvas")]
      (events/listen button
                     "click"
                     do-send-button-clicked)
      (events/listen canvas
                     "mousedown"
                     do-canvas-mousedown))))
