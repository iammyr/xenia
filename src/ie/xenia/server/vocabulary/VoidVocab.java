package ie.xenia.server.vocabulary;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class VoidVocab {
	 /** <p>The ontology model that holds the vocabulary terms</p> */
    private static OntModel m_model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );

    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://rdfs.org/ns/void#";

    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}

    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );





    // Vocabulary properties
    ///////////////////////////

    /** <p>Announcement of an RDF dump of the dataset.</p> */
    public static final OntProperty DATA_DUMP = m_model.createOntProperty( "http://rdfs.org/ns/void#dataDump" );

    public static final OntProperty EXAMPLE_RESOURCE = m_model.createOntProperty( "http://rdfs.org/ns/void#exampleResource" );

    public static final OntProperty FEATURE = m_model.createOntProperty( "http://rdfs.org/ns/void#feature" );

    public static final OntProperty LINK_PREDICATE = m_model.createOntProperty( "http://rdfs.org/ns/void#linkPredicate" );

    /** <p>The sink target of an interlinking</p> */
    public static final OntProperty OBJECTS_TARGET = m_model.createOntProperty( "http://rdfs.org/ns/void#objectsTarget" );

    public static final OntProperty SPARQL_ENDPOINT = m_model.createOntProperty( "http://rdfs.org/ns/void#sparqlEndpoint" );

    public static final OntProperty STAT_ITEM = m_model.createOntProperty( "http://rdfs.org/ns/void#statItem" );

    /** <p>The source target of an interlinking</p> */
    public static final OntProperty SUBJECTS_TARGET = m_model.createOntProperty( "http://rdfs.org/ns/void#subjectsTarget" );

    public static final OntProperty SUBSET = m_model.createOntProperty( "http://rdfs.org/ns/void#subset" );

    public static final OntProperty TARGET = m_model.createOntProperty( "http://rdfs.org/ns/void#target" );

    /** <p>Defines a simple URI look-up protocol for accessing a dataset.</p> */
    public static final OntProperty URI_LOOKUP_ENDPOINT = m_model.createOntProperty( "http://rdfs.org/ns/void#uriLookupEndpoint" );

    /** <p>Defines a regular expression pattern matching URIs in the dataset.</p> */
    public static final OntProperty URI_REGEX_PATTERN = m_model.createOntProperty( "http://rdfs.org/ns/void#uriRegexPattern" );

    /** <p>A vocabulary that is used in the dataset.</p> */
    public static final OntProperty VOCABULARY = m_model.createOntProperty( "http://rdfs.org/ns/void#vocabulary" );


    // Vocabulary classes
    ///////////////////////////
    public static final OntClass DATASET = m_model.createClass("http://rdfs.org/ns/void#Dataset");


}
