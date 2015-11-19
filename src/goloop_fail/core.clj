(ns goloop-fail.core
  (:require [clojure.core.async :refer [go-loop chan <! >!!]])
  (:gen-class))

(def c1 (chan))
(def c2 (chan))
(def c3 (chan))
(def c4 (chan))
(def c5 (chan))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (do

    ;; now let's have 5 separate go loops that take from each channel
    (go-loop [s (<! c1)]
      (println s)
      (recur (<! c1)))

    (go-loop [s (<! c2)]
      (println s)
      (recur (<! c2)))

    (go-loop [s (<! c3)]
      (println s)
      (recur (<! c3)))

    (go-loop [s (<! c4)]
      (println s)
      (recur (<! c4)))

    (go-loop [s (<! c5)]
      (println s)
      (recur (<! c5)))

    ;; notice that we put in particular order here....
    ;; but the order will be not preserved...
    (loop []
      (>!! c1 "1")
      (>!! c2 "2")
      (>!! c3 "3")
      (>!! c4 "4")
      (>!! c5 "5")
      (recur))))
