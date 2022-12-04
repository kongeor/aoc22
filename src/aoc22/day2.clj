(ns aoc22.day2 
  (:require [aoc22.files :as f]))


(defn shape-score [s]
  (case s
    "X" 1
    "Y" 2
    "Z" 3))

(comment
  (shape-score "Z"))

;; A, X rock
;; B, Y paper
;; C, Z scissors

(defn round-score [[opponent-shape shape]]
  (case [opponent-shape shape]
    ["A" "X"] 3
    ["A" "Y"] 6
    ["A" "Z"] 0
    ["B" "X"] 0
    ["B" "Y"] 3
    ["B" "Z"] 6
    ["C" "X"] 6
    ["C" "Y"] 0
    ["C" "Z"] 3))

(comment
  (round-score ["A" "Y"])
  )

(defn game-score [rounds]
  (->> rounds
       (map #(+ (round-score %)
                (shape-score (second %))))
       (reduce +)))

(comment
  (game-score [["A" "Y"]
               ["B" "X"]
               ["C" "Z"]])
  )

;; 1
(comment
  (game-score (f/readlines-and-split "day2.txt"))
  )

;; A, X rock
;; B, Y paper
;; C, Z scissors
;; X lose
;; Y draw
;; Z win
(defn what-to-play? [[opponent-shape outcome]]
  (case [opponent-shape outcome]
    ["A" "X"] "Z"
    ["A" "Y"] "X"
    ["A" "Z"] "Y"
    ["B" "X"] "X"
    ["B" "Y"] "Y"
    ["B" "Z"] "Z"
    ["C" "X"] "Y"
    ["C" "Y"] "Z"
    ["C" "Z"] "X"))

(defn decrypted-game-score [rounds]
  (->> rounds
       (map #(assoc % 1 (what-to-play? %)))
       game-score))

(comment
  (decrypted-game-score [["A" "Y"]
                         ["B" "X"]
                         ["C" "Z"]])
  )

;; 2
(comment
  (decrypted-game-score (f/readlines-and-split "day2.txt")))