(require 
  '[clojure.string :refer [split-lines]])

(def nums
  (->>
    (slurp "input.txt")
    (split-lines)
    (map #(Integer/parseInt %))))

(defn first-duplicate-freq
  ([nums] (first-duplicate-freq (cycle nums) 0 #{0}))
  ([nums acc seen]
    (let [freq (+ acc (first nums))]
      (if (contains? seen freq)
        freq
        (recur (rest nums) freq (conj seen freq))))))

(println
  "Final Frequency:" (reduce + nums))

(println
  "First Duplicate Frequency:" (first-duplicate-freq nums))