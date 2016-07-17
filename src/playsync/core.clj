(ns playsync.core
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]])
  (:gen-class))


(defn -main
  [& args]
  (def echo-chan (chan))
  (go (println (<! echo-chan)))
  (>!! echo-chan "ping"))
