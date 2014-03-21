(ns gephi-clj.core
  (import [org.gephi.io.importer.api Container ImportController ContainerFactory]
          [org.gephi.io.exporter.api ExportController]
          [org.gephi.io.exporter.spi GraphExporter]
          [org.gephi.project.api ProjectController Workspace]
          [org.openide.util Lookup]
          [org.gephi.io.processor.plugin DefaultProcessor AppendProcessor]))

(defn get-controller [c]
  (.lookup (Lookup/getDefault) c))

(defn ->container []
  (-> (Lookup/getDefault)
     (.lookup ContainerFactory)
     .newContainer))

(defn get-workspace
  ([]
     (get-workspace (get-controller ProjectController)))
  ([project]
     (.newProject project)
     (.getCurrentWorkspace project)))

(defn export! [filename]
  (.exportFile (get-controller ExportController) (clojure.java.io/file filename)))

(defn process-graph!
  ([graph]
     (process-graph! graph
                     (DefaultProcessor.)))
  ([graph processor]
     (process-graph! graph
                     processor
                     (->container)
                     (get-controller ImportController)
                     (get-workspace)))
  ([graph processor container import-controller workspace]
     (.generate graph (.getLoader container))
     (.process import-controller container processor workspace)))

(defn visualize!
  ([item exportfn]
     (let [r (visualize! item)]
       (exportfn)
       r))
  ([item]
     (let [workspace (get-workspace)
           container (->container)
           controller (get-controller ImportController)
           {:keys [graph processor]
            :or   {processor (DefaultProcessor.)}} item]
       (process-graph! graph processor container controller workspace)
       {:workspace workspace
        :container container
        :controller controller})))

;; (visualize! 
;;  {:graph (random-graph :number-of-nodes 6
;;                        :wiring-probability 0.05)
;;   :processor (DefaultProcessor.)}
;;  #(export! "random-graph.png"))


