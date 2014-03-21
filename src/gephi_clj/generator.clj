(ns gephi-clj.generator
  (import [org.gephi.io.generator.plugin RandomGraph DynamicGraph]))

(defn dynamic-graph
  "Lightly wraps the org.gephi.io.generator.plugin.DynamicGraph
  constructor.

  Example usage:

  (dynamic-graph)"

  []
  (DynamicGraph.))

(defn random-graph
  "Lightly wraps the org.gephi.io.generator.plugin.DynamicGraph
  constructor.

  Example usage:

  (random-graph 50 0.05)"

  [number-of-nodes wiring-probability]
  (doto (RandomGraph.)
    (.setNumberOfNodes number-of-nodes)
    (.setWiringProbability wiring-probability)))








