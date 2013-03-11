(ns crowd-demo.sender
    (:require [crowd-demo.websocket :as ws]
     [crowd-demo.canvas :as canvas]
     [goog.dom :as dom]
     [goog.events :as events]
     [goog.graphics :as graphics]))

(def current-marking [])

(defn do-send-button-clicked
  "When the send button is clicked read from the input field
   and send data over websocket connection"
  [soc]
  (let [message (dom/getElement "message")]
    (ws/emit! soc message/value)))

(defn draw-current-marking
  "Draws the contents of current marking to the canvas"
  []
  (let [surface (canvas/surface (dom/getElement "subject-canvas"))]
    (canvas/draw-line surface current-marking)))

(defn track-marking!
  "Mutates the state of current-marking to add new points to it and trigger a draw"
  [ev]
  (set! current-marking (conj current-marking [ev/offsetX ev/offsetY]))
  (draw-current-marking))

(defn stop-tracking!
  "Remove the mousemove event listener reset current-marking"
  [ev]
  (events/unlisten (dom/getElement "subject-canvas")
                   "mousemove"
                   track-marking!)
  (set! current-marking []))
 
(defn do-canvas-mousedown
  "Adds a mousemove listener and a mouseup listener"
  [ev]
  (let [canvas (dom/getElement "subject-canvas")]
    (events/listen canvas 
                   "mousemove"
                   track-marking!)
    (events/listenOnce  canvas
                        "mouseup"
                        stop-tracking!)))

(defn start-sender 
  "Set Event Listeners for the Sender Page"
  []
  (let [button (dom/getElement "send-erase")
        canvas (dom/getElement "subject-canvas")]
    (events/listen button
                   "click"
                   do-send-button-clicked)
    (events/listen canvas
                   "mousedown"
                   do-canvas-mousedown)))
