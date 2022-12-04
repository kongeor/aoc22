(ns aoc22.day1 
  (:require [aoc22.files :as f]))


;; 1
(comment
  (->>
   (f/read-and-split-ints "day1.txt")
   (partition-by nil?)
   (filter #(-> % first nil? not))
   (map #(apply + %))
   (apply max)
   )
  )

;; 2
(comment
  (->>
   (f/read-and-split-ints "day1.txt")
   (partition-by nil?)
   (filter #(-> % first nil? not))
   (map #(apply + %))
   (sort #(compare %2 %1))
   (take 3))
 
  )

