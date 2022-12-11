(ns aoc22.day7 
  (:require [clojure.string :as str]))

(def sample "$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k")

(defn commands->data [input]
  (let [commands (str/split-lines input)]
    (loop [data {(keyword "/") {}}
           path []
           [c & commands] commands]
      (if-not c
        data
        (cond
          (= c "$ cd /")
          (recur data [(keyword "/")] commands)

          (= c "$ cd ..")
          (recur data (vec (butlast path)) commands)

          (= c "$ ls")
          (recur data path commands)

          (str/starts-with? c "dir")
          (recur data path commands)

          (str/starts-with? c "$ cd")
          (let [[_ _ dir] (str/split c #"\s")]
            (recur data (conj path (keyword dir)) commands))

          :else
          (let [[size name] (str/split c #"\s")]
            (recur (assoc-in data (conj path (keyword name)) (Long/parseLong size)) 
                   path commands)))))))

(comment
 (commands->data sample))

(comment
  (clojure.walk/postwalk (fn [e]
                           (println e)
                           e) (commands->data sample))
  )


(defn dir-sum [sa path data]
  (reduce-kv (fn [a k v]
               (+ a
                  (if (map? v)
                    (let [path' (conj path k)]
                      (get (swap! sa assoc path' (dir-sum sa path' v)) path'))
                    (+ v)))) 0 data))

(comment
  (let [sa (atom {})]
    (dir-sum sa [] (commands->data (slurp "resources/day7.txt")))
    @sa
    ;; 1
    #_(->> @sa
        vals
        (filter #(<= % 100000))
        (apply +))

    #_(@sa [:/])
    ;; 37072768

    ;; available 28481047

    ;; 2
    (let [available (- 70000000 (@sa [:/]))]
      (println "*" available)
      (->> @sa
        #_(filter (fn [[k _]] (= (count k) 2)))
        (filter (fn [[_ v]] (>= (+ available v) 30000000)))
        (sort-by second))))

  )
