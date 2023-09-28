(ns insilica.ontology.biochem
  (:require [insilica.ontology.core :as ont :refer (clazz def-classes)]))

(def classes
  [(clazz
    "AC50"
    "The effective concentration of a perturbagen, which produces 50% of the maximal possible response, which could mean either activation (EC50) or inhibition (IC50) for that perturbagen."
    "http://www.bioassayontology.org/bao#BAO_0000186"
    :property-type ont/XMLDouble)
   (clazz
    "CASNumber"
    "Identifier used by the Chemical Abstracts Service database."
    "http://semanticscience.org/resource/CHEMINF_000446"
    :resource-prefix "https://commonchemistry.cas.org/detail?cas_rn=")
   (clazz
    "CCDSID"
    nil
    "http://identifiers.org/idot:ccdsId"
    :resource-prefix "http://identifiers.org/ccds/")
   (clazz
    "Efficacy"
    "The maximum response that can be achieved with the perturbagen as percentage of the positive control."
    "http://www.bioassayontology.org/bao#BAO_0000656"
    :property-type ont/XMLDouble)
   (clazz
    "EnsemblID"
    nil
    "http://edamontology.org/data_2610"
    :property-type ont/XMLString)
   (clazz
    "EntrezID"
    nil
    "http://dbpedia.org/ontology/entrezgene"
    :property-type ont/XMLString)
   (clazz
    "Gene"
    nil
    "http://dbpedia.org/ontology/gene")
   (clazz
    "GeneGroup"
    nil
    "http://purl.obolibrary.org/obo/obo:SO0005855"
    :property-type ont/XMLString)
   (clazz
    "GeneLocation"
    nil
    "http://dbpedia.org/ontology/GeneLocation"
    :property-type ont/XMLString)
   (clazz
    "GeneLocus"
    nil
    "http://purl.jp/bio/4/id/200906015157899027"
    :property-type ont/XMLString)
   (clazz
    "GeneName"
    "The name of a gene, (typically) assigned by a person and/or according to a naming scheme. It may contain white space characters and is typically more intuitive and readable than a gene symbol. It (typically) may be used to identify similar genes in different species and to derive a gene symbol."
    "http://edamontology.org/data_2299"
    :property-type ont/XMLString)
   (clazz
    "HGNCID"
    nil
    "http://identifiers.org/idot:hgnc"
    :resource-prefix "http://identifiers.org/hgnc/")
   (clazz
    "MeropsID"
    nil
    "http://edamontology.org/data_2629"
    :property-type ont/XMLString)
   (clazz
    "MGDMGI"
    nil
    "http://purl.obolibrary.org/obo/MI_0479"
    :property-type ont/XMLString)
   (clazz
    "OmimID"
    nil
    "http://dbpedia.org/ontology/omim"
    :property-type ont/XMLString)
   (clazz
    "ProtocolName"
    nil
    "http://example.com/ProtocolName"
    :property-type ont/XMLString)
   (clazz
    "PubmedID"
    nil
    "http://identifiers.org/idot:pubmedId"
    :resource-prefix "http://identifiers.org/pubmed/")
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
    "RefSeqAccession"
    "Accession number of a RefSeq database entry."
    "http://edamontology.org/data_1098"
    :property-type ont/XMLString)
   (clazz
    "RGDID"
    nil
    "http://identifiers.org/idot:rgdId"
    :resource-prefix "http://identifiers.org/rgd/")
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
    :property-type ont/XMLString)
   (clazz
    "SMILES"
    nil
    "http://edamontology.org/format_1196"
    :property-type ont/XMLString)
   (clazz
    "Symbol"
    nil
    "http://dbpedia.org/ontology/symbol"
    :property-type ont/XMLString)
   (clazz
    "UCSCID"
    nil
    "http://example.com/UCSCID"
    :property-type ont/XMLString)
   (clazz
    "UniprotID"
    nil
    "http://dbpedia.org/ontology/uniprot"
    :resource-prefix "http://identifiers.org/uniprot/")
   (clazz
    "VegaID"
    nil
    "http://example.com/VegaID"
    :property-type ont/XMLString)])

(def-classes classes)
