(ns gephi-clj.api
  (:require [potemkin.namespaces :as p]
            [gephi-clj.core :as core]
            [gephi-clj.generator :as generator]))

(p/import-fn core/export!)
(p/import-fn core/visualize!)

(p/import-fn generator/dynamic-graph)
(p/import-fn generator/random-graph)
