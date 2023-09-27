(ns insilica.ontology.biochem
  (:require [insilica.ontology.core :as ont :refer (clazz def-classes)]))

(def classes
  [(clazz
    "AC50"
    "The effective concentration of a perturbagen, which produces 50% of the maximal possible response, which could mean either activation (EC50) or inhibition (IC50) for that perturbagen."
    "http://www.bioassayontology.org/bao#BAO_0000186"
    :property-type ont/Literal)
   (clazz
    "CASNumber"
    "Identifier used by the Chemical Abstracts Service database."
    "http://semanticscience.org/resource/CHEMINF_000446"
    :resource-prefix "https://commonchemistry.cas.org/detail?cas_rn=")
   (clazz
    "Efficacy"
    "The maximum response that can be achieved with the perturbagen as percentage of the positive control."
    "http://www.bioassayontology.org/bao#BAO_0000656"
    :property-type ont/Literal)
   (clazz
    "ProtocolName"
    nil
    "http://example.com/ProtocolName"
    :property-type ont/XMLString)
   (clazz
    "PubchemCID"
    nil
    "http://identifiers.org/idot:pubchem.compound"
    :resource-prefix "http://identifiers.org/pubchem.compound/")
   (clazz
    "PubchemSID"
    nil
    "http://identifiers.org/idot:pubchem.substance"
    :resource-prefix "http://identifiers.org/pubchem.substance/")
   (clazz
    "SampleDataID"
    nil
    "http://example.com/SampleDataID"
    :property-type ont/XMLString)
   (clazz
    "SampleID"
    nil
    "http://example.com/SampleID"
    :property-type ont/XMLString)
   (clazz
    "SampleName"
    nil
    "http://example.com/SampleName"
    :property-type ont/Literal)
   (clazz
    "SMILES"
    nil
    "http://edamontology.org/format_1196"
    :property-type ont/XMLString)])

(def-classes classes)
