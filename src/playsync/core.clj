(ns playsync.core
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]])
  (:gen-class))

(def echo-chan (chan))

(defn get-google []
  (Thread/sleep 1000)
  (slurp "http://google.com"))

(defn sleep-then-print [sleep-time content]
  (Thread/sleep sleep-time)
  (println content))

(defn sleep-then-return [sleep-time content]
  (Thread/sleep sleep-time)
  content)

(defn -main
  [& args]
  (println "process started")

  (go (println (<! echo-chan)))
  (>!! echo-chan "first msg, non-blocking")

  (let [t (thread (sleep-then-return 2000 "second msg, blocking"))]
    (println (<!! t)))

  (go (println (<! echo-chan)))
  (go (println (<! echo-chan)))

  (>!! echo-chan (sleep-then-return 1000 "third msg, blocking"))
  (>!! echo-chan "fourth msg, blocking"))
