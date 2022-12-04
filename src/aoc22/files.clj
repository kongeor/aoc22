(ns aoc22.files 
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn read-and-split [f]
  (->
   (io/resource f)
   (slurp)
   (str/split #"\n")))

;; TODO there is readlines
(defn readlines-and-split [f]
  (let [lines (->
               (io/resource f)
               (slurp)
               (str/split #"\n")
               )]
    (map #(str/split % #" ") lines)))

(comment
  (readlines-and-split "day2.txt"))

(defn str->int [s]
  (Integer/parseInt s))

(defn str->int* [s]
  (try
    (str->int s)
    (catch NumberFormatException _)))

(comment
  (str->int "123")
  (str->int* "123a")
  )

(defn read-and-split-ints [f]
  (map str->int* (read-and-split f)))

(defn as-ints [coll]
  (map str->int coll))

(comment
  (read-and-split "day1.txt")
  )

(comment
  (partition-by #(and
                  (string? %)
                  (str/blank? %)) [1 2 3 "" 2 3 4])
  )