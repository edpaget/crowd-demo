(ns crowd-demo.core
    (require [clojure.string :as s])
    (:import [org.webbitserver WebServer WebServers WebSocketHandler]
             [org.webbitserver.handler StaticFileHandler]))

(defn on-message
  [connection message]
  (pprint message))

(defn -main []
  (doto (WebServers/createWebServer 8080)
        (.add "/marks"
              (proxy [WebSocketHandler] []
                     (onOpen [c] (println "opened" c))
                     (onClose [c] (println "closed" c))
                     (onMessage [c j] (on-message c j))))
        (.add (StaticFileHandler. "./public"))
        (.start)))
