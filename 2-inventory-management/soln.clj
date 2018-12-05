(require 
  '[clojure.string :refer [split-lines join]]
  '[clojure.math.combinatorics :refer [combinations]])

(def ids
  (->>
    (slurp "input.txt")
    (split-lines)))

(defn calc-checksum
  ([ids] (calc-checksum ids 0 0))
  ([ids twos threes]
    (if (empty? ids) 
      (* twos threes)
      (let [id (first ids) others (rest ids) freqs (vals (frequencies id))]
        (recur others (if (some #(= % 2) freqs) (inc twos) twos) (if (some #(= % 3) freqs) (inc threes) threes))))))

(println (calc-checksum ids))

(defn single-difference? [strings]
  (let [[x y] strings]
    (= 1 (->> (map compare x y)
      (filter #(not= 0 %))
      (count)))))

(defn find-similar-ids [ids]
  (->> (combinations ids 2)
    (some #(if (single-difference? %) %))))

(defn get-common-characters [ids]
  (let [[x y] (find-similar-ids ids)]
    (->> (map vector x y)
      (filter #(not (apply distinct? %)))
      (map first)
      (join))))

(println "Common characters:" (get-common-characters ids))