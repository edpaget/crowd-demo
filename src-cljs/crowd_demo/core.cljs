(ns crowd-demo.core
    (:require [crowd-demo.history :as history]
     [crowd-demo.sender :as sender]
     [crowd-demo.websocket :as ws]))

(defn start-app
  "Start the Application Running"
  []
  (let [hist (history/create)
        soc (-> (ws/create)
                (ws/configure #(.log js/console %))
                (ws/open! "ws://localhost:8080/marks"))]
    (-> hist 
        (history/listen-hash-change 
          (history/router [["" #(sender/start-sender soc)]
                           ["/sender" (sender/start-sender soc)]]))
        (history/start!))))

(start-app)