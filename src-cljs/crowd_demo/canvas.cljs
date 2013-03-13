(ns crowd-demo.canvas)

(defn surface 
  "Retrieves canvas context"
  [canvas]
 (.getContext canvas "2d"))

(defn start-draw!
  "Sets the path"
  [surface x y]
  (set! (.-strokeStyle surface) "orange")
  (set! (.-lineCap "round"))
  (.beginPath surface)
  (.moveTo surface x y))

(defn add-segment!
  "Adds an additional setment to a canvas that started a path"
  [surface x y]
  (.lineTo surface x y)
  (.stroke surface))
