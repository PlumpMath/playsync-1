(ns playsync.core
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]])
  (:gen-class))

(def echo-chan (chan))

(defn -main
  [& args]
  (go (println (<! echo-chan)))
  (>!! echo-chan "ping")
  (go (>! echo-chan "ping")))
