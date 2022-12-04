(ns aoc22.day4 
  (:require [aoc22.files :as f]
            [clojure.string :as str]))

(defn fully-contained? [[a b] [x y]]
  (or
   (and (>= x a)
        (>= b y))
   (and (>= a x)
        (>= y b))))

(comment
  (fully-contained? [1 8] [2 9]))

(defn str->int [s]
  (Integer. s))

(defn parse-line [line]
  (let [tokens (str/split line #",")]
    (map #(let [tk (str/split % #"-")]
            (map str->int tk)) tokens)))

;; 1
(comment
    (->> (f/read-and-split "day4.txt")
         (map parse-line)
         (map #(apply fully-contained? %))
         (filter identity)
         count)
  )

(defn overlap? [[a b] [x y]]
  (not
   (or
    (> x b)
    (> a y))))

(comment
  (overlap? [3 6] [7 8]))

;; 1
(comment
    (->> (f/read-and-split "day4.txt")
         (map parse-line)
         (map #(apply overlap? %))
         (filter identity)
         count)
  )