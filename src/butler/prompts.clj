(ns butler.prompts)

(defn prompt-read [prompt]
  (print (format "%s: " prompt))
  (flush)
  (read-line))

(defn y-or-n-p [prompt]
  (= "y"
     (loop []
       (or
        (re-matches #"[yn]" (.toLowerCase (prompt-read prompt)))
        (recur)))))

(defn prompt-for-profile []
  (struct butler.core/new-profile
          (prompt-read "Profile Name")
          (prompt-read "Directory to Monitor")))

(defn add-profile-prompt [db]
  (lazy-cat db
            (loop [prev-profiles '()]
              (let [profiles (cons (prompt-for-profile) prev-profiles)]
                (if (not (y-or-n-p "Another? [y/n]"))
                  profiles
                  (recur profiles))))))
