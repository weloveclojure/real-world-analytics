(ns weloveclojure.real-world-analytics
  (:require [clj-http.client :as http]
            [clj-jgit.porcelain :as git]
            [clojure.string :as string]
            [clojure.java.shell :as shell]
            [clojure.data.json :as json])
  (:gen-class))

(defn get-config [] (read-string (slurp "resources/config.edn")))

(defn get-dir-name [{:keys [url category]}]
  (format "repos/%s/%s"
          (-> category name str)
          (as-> url x
            (string/split x #"/")
            (drop 3 x)
            (string/join "." x))))

(defn clone-repo
  [{:keys [url category dir]}]
  (git/git-clone url :dir dir))

(defn assoc-category [category url]
  {:category category :url url})

(defn prepare-repos [config]
  (->> config
       :real-world
       (mapcat #(map (partial assoc-category (key %)) (val %)))
       (map #(assoc % :dir (get-dir-name %)))))

(defn clone-repos
  [config]
  (run! clone-repo (prepare-repos config)))

(defn count-lines [{:keys [dir] :as repo}]
  (assoc repo :analytics
         (try (json/read-str (:out (shell/sh "cloc" dir "--json")))
              (catch Exception e :error))))

(defn analyze-real-world 
  [config]
  (as-> config x
    (prepare-repos x)
    (map count-lines x)
    (into [] x)
    (spit "data/real-world.edn" x)))

(defn run-analytics
  "Clone repositories, run cloc for line count analytics, store the data on disk."
  [& _]
  (println "Running Analytics")
  (analyze-real-world (get-config))
  (println "Finished running analytics.")
  (println "Check ./data/real-world.edn")
  (System/exit 0))

(defn run-init [& _]
  (println "Cloning repositories.")
  (clone-repos (get-config))
  (println "Finished cloning repositories.")
  (println "Check ./repos/front-end & ./repos/back-end")
  (System/exit 0))