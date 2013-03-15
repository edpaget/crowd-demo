(ns crowd-demo.canvas)

(defn surface 
  "Retrieves canvas context"
  [canvas]
 (.getContext canvas "2d"))

(defn start-draw!
  "Sets the path"
  [surface x y {:keys [color width cap]}]
  (set! (.-lineWidth surface) width)
  (set! (.-strokeStyle surface) color)
  (set! (.-lineCap surface) cap)
  (.beginPath surface)
  (.moveTo surface x y))

(defn add-segment!
  "Adds an additional setment to a canvas that started a path"
  [surface x y]
  (.lineTo surface x y)
  (.stroke surface))

(defn draw-line!
  "Draw an Entire Line from a serious of x-y points"
  [surface [x y & points] opts]
  (start-draw! surface x y opts)
  (doseq [[x y] points]
         (add-segment! surface x y)))

(defn erase!
  "Takes a Canvas and clears everything in it"
  [canvas]
  (let [surface (surface canvas)]
    (.clearRect surface 0 0 canvas/width canvas/height)))
