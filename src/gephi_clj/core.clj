(ns gephi-clj.core
  (import [org.gephi.io.importer.api Container ImportController]
          [org.gephi.project.api ProjectController Workspace]
          [org.openide.util Lookup])
  (require [clojure.reflect :as r]))

(defn- project-controller []
  (.lookup (Lookup/getDefault) ProjectController))

(defn- init-workspace
  ([]
     (init-workspace (project-controller)))
  ([project]
     (.newProject project)
     (.getCurrentWorkspace project)))

;; (init-workspace)

;; (let [f (clojure.java.io/file "resources/polblogs.gml")
;;       importController (.lookup
;;                         (Lookup/getDefault)
;;                         ImportController)
;;       container (-> importController (.importFile f))]
;;   container)
