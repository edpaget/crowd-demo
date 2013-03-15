(ns crowd-demo.sender
    (:require [crowd-demo.websocket :as ws]
     [crowd-demo.canvas :as canvas]
     [goog.dom :as dom]
     [goog.events :as events]))

(def current-marking [])
(def all-markings [])

(def current-line-opts {:color "orange" :cap "round" :width 10})
(def submitted-line-opts {:color "blue" :cap "round" :width 10})

(defn send-marking
  "Sends the current marking"
  [soc]
  (ws/emit! soc current-marking))

(defn track-marking!
  "Mutates the state of current-marking to 
   add new points to it and trigger a draw"
  [surface ev]
  (set! current-marking (conj current-marking [ev/offsetX ev/offsetY]))
  (canvas/add-segment! surface ev/offsetX ev/offsetY))

(defn stop-tracking!
  "Remove the mousemove event listener reset current-marking"
  [surface ev]
  (set! all-markings (conj all-markings current-marking))
  (let [canvas (dom/getElement "subject-canvas")]
    (events/removeAll canvas "mousemove")
    (canvas/erase! canvas)
    (doseq [marking all-markings]
           (canvas/draw-line! (canvas/surface canvas) 
                              marking submitted-line-opts))))
 
(defn do-canvas-mousedown
  "Adds a mousemove listener and a mouseup listener"
  [ev]
  (let [canvas (dom/getElement "subject-canvas")
        surface (canvas/surface canvas)]
    (set! current-marking []) 
    (events/listen canvas "mousemove" #(track-marking! surface %))
    (events/listenOnce  canvas "mouseup" stop-tracking!)
    (canvas/start-draw! surface ev/offsetX ev/offsetY current-line-opts)))

(defn start-sender 
  "Set Event Listeners for the Sender Page"
  [soc]
  (let [button (dom/getElement "erase")
        canvas (dom/getElement "subject-canvas")]
    (events/listen button "click" #(canvas/erase! canvas))
    (events/listen canvas "mousedown" do-canvas-mousedown)
    (events/listen canvas "mouseup" #(send-marking soc))))
