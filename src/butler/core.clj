(ns butler.core
  (:gen-class))

(def empty-profile '())

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn -copy
  "I Copy Files" [&dest, old])

(defstruct new-profile :profile :directory)


(defn -save-profiles [profile filename]
  (spit filename (pr-str profile)))

(defn add-profile [pro & new-pro]
  (into pro new-pro))

(defn -load-profiles [filename]
  (read-string
   (slurp filename)))

(defn -new-profile-list []
  (-save-profiles empty-profile "profiles"))

(defn make-profile [name dir]
  (-> -load-profiles ("profiles")
      (add-profile (struct new-profile name dir))
      (-save-profiles "profiles")))
