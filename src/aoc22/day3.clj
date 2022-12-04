(ns aoc22.day3 
  (:require [aoc22.files :as f]
            [clojure.set :as set]))

(def sample
  [
   "vJrwpWtwJgWrhcsFMMfFFhFp"
   "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
   "PmmdzqPrVvPwwTWBwg"
   "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
   "ttgJtRGJQctTZtZT"
   "CrZsJsPPZsGzwwsLwLmpwMDw"])

(defn build-prioriry-map []
  (let [m (reduce (fn [a i]
                    (assoc a (char i) (- i 96))) {} (range (int \a) (inc (int \z))))]
    (reduce (fn [a i]
              (assoc a (char i) (- i 38))) m (range (int \A) (inc (int \Z))))))

(comment
  (map #(- % 38) (range (int \A) (inc (int \Z))))
  )

(def priorities (build-prioriry-map))


(defn letter-priority [l]
  (priorities l)
  )

(comment
  (letter-priority \a)
  (letter-priority \z)
  (letter-priority \Z))


(defn rearrangement-priority [e]
  (let [n (count e)
        [a b] (split-at (/ n 2) e)
        common (set/intersection (set a) (set b))]
    (reduce + (map letter-priority common))))

(defn sum-of-priorities [items]
  (reduce +
          (map 
           rearrangement-priority
           items
           )))

(comment
 (sum-of-priorities sample))

;; 1
(comment
 (sum-of-priorities
  (f/read-and-split "day3.txt")))

;; 2
(apply +
       (map (fn [[a b c]]
              (->
               (set/intersection (set a) (set b) (set c))
               first
               letter-priority)) 
            (partition 3 (f/read-and-split "day3.txt"))))