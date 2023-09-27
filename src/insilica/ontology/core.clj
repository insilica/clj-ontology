(ns insilica.ontology.core
  (:require [clojure.java.io :as io]))

(declare Comment Domain Property Range Type)

(defrecord IRI [^String iri])

(defn iri? [x]
  (instance? IRI x))

(defn iri [s]
  (IRI. s))

(defrecord Triple [subj pred obj])

(defn triple [subj pred obj]
  (Triple. subj pred obj))

(defrecord Clazz [name doc iri property-type resource-prefix])

(defn clazz [name doc iri & {:keys [property-type resource-prefix]}]
  (Clazz. name doc (IRI. iri) property-type resource-prefix))

(defn resource-iri [{:keys [resource-prefix]} resource-id]
  (IRI. (str resource-prefix resource-id)))

(defn literal-property
  [{:as clazz :keys [doc property-type resource-prefix]}
   subj
   iri]
  (->> [(triple iri (:iri Type) (:iri Property))
        (when (seq doc)
          (triple iri (:iri Comment) doc))
        (when property-type
          (triple iri (:iri Range) (if (iri? property-type) property-type (:iri property-type))))
        (when resource-prefix
          (triple iri (:iri Range) (:iri clazz)))]
       (filterv identity)))

(defn has-fn [{:as clazz :keys [iri name property-type]}]
  (fn [subj resource-id-or-property]
    (let [has-iri (IRI. (str "http://example.com#has" name))]
      (if property-type
        (let []
          (concat
           [(triple
             subj
             has-iri
             resource-id-or-property)]
           (literal-property clazz subj has-iri)))
        (let [tgt-iri (resource-iri clazz resource-id-or-property)]
          [(triple
            tgt-iri
            (IRI. "http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
            iri)
           (triple
            subj
            has-iri
            tgt-iri)])))))

(defmacro def-classes [classes]
  `(do
     ~@(map (fn [clazz]
              (let [cname (:name clazz)
                    csym (symbol cname)]
                `(do
                   (let [clazz# ~clazz]
                     (def ~csym clazz#)
                     (alter-meta! (var ~csym) assoc :doc (:doc clazz#))
                     (when (or (:property-type clazz#) (:resource-prefix clazz#))
                       (def ~(->> cname (str "has") symbol) (has-fn clazz#)))))))
            (eval classes))))

(def classes
  [(clazz
    "Comment"
    nil
    "http://www.w3.org/1999/02/22-rdf-syntax-ns#comment")
   (clazz
    "Domain"
    nil
    "http://www.w3.org/1999/02/22-rdf-syntax-ns#domain")
   (clazz
    "XMLDouble"
    nil
    "http://www.w3.org/2001/XMLSchema#Double")
   (clazz
    "Literal"
    nil
    "http://www.w3.org/2000/01/rdf-schema#Literal")
   (clazz
    "Property"
    nil
    "http://www.w3.org/1999/02/22-rdf-syntax-ns#property")
   (clazz
    "Range"
    nil
    "http://www.w3.org/1999/02/22-rdf-syntax-ns#range")
   (clazz
    "Type"
    "The subject is an instance of a class."
    "http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
   (clazz
    "XMLString"
    nil
    "http://www.w3.org/2001/XMLSchema#String")])

(def-classes classes)

(defn isA [subj type]
  (triple
   subj
   (:iri Type)
   (if (iri? type)
     type
     (or (:iri type)
         (IRI. type)))))

(defn nt-repr [s]
  (if (iri? s)
    (str "<" (:iri s) ">")
    (pr-str (str s))))

(defn nt-format [{:keys [subj pred obj]}]
  (format "%s %s %s ." (nt-repr subj) (nt-repr pred) (nt-repr obj)))

(def def-preds
  (->> [Comment Domain Range Type]
       (map :iri)
       set))

(defn nt-seq*
  "For efficiency, emit the record values right away which are likely to be unique,
   but deduplicate common definitions that are often duplicated many times.
   A simple heuristic, but does not have to be 100% accurate because duplicate
   triples are allowed in the final output."
  [def-set [next & more]]
  (if next
    (lazy-seq
     (if (def-preds (:pred next))
       (nt-seq* (conj def-set next) more)
       (cons
        (nt-format next)
        (nt-seq* def-set more))))
    (map nt-format def-set)))

(defn nt-seq [triples]
  (nt-seq* #{} triples))

(defn subj-mappings [subj data mapping]
  (mapcat
   (fn [[k v]]
     (when-let [v' (if (string? v) (get data v) (v data))]
       (k subj v')))
   mapping))

(defn write-nt [filename triples]
  (with-open [w (io/writer filename)]
    (doseq [line (nt-seq triples)]
      (.write w line)
      (.write w "\n"))))
