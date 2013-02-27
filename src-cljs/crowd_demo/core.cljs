(ns crowd-demo.core
    (:require [crowd-demo.history :as history]
     [crowd-demo.sender :as sender]))

(defn start-app
  "Start the Application Running"
  []
  (let [hist (history/create)]
    (.log js/console hist)
    (-> hist 
        (history/listen-hash-change (history/router [["" sender/start-sender]
                                                     ["#/sender" sender/start-sender]]))
        (history/start!))))

(start-app)