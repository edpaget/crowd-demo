(ns crowd-demo.canvas)

(defn surface 
  "Retrieves canvas context"
  [canvas]
 (.getContext canvas "2d"))

(defn draw-line 
  "Draws on a line on a canvas surface from a vector of points"
  [surface [start & points]]
  (.beginPath surface)
  (.moveTo surface (first start)
                   (second start))
  (doseq [point points]
         (.lineTo surface (first point)
                          (second point)))
  (set! (.-strokeStyle surface) "orange")
  (.stroke surface))
