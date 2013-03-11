(ns crowd-demo.history
    (:require [goog.History :as history]
     [goog.History.EventType :as history-ev]
     [goog.events :as events]))

(defn create [] (goog.History.))

(defn listen-hash-change 
  "Sets the passed function to be called when history state changes"
  [hist nav-function]
  (events/listen hist history-ev/NAVIGATE nav-function)
  hist)

(defn start!
  "Start listening for changes"
  [hist]
  (.setEnabled hist true)
  hist)

(defn router
  "Create a function that routes nav changes to other functions"
  [routes]
  (fn route
      [ev]
      ((-> (filter #(= ev/token (first %)) routes)
           (first)
           (second)))))
