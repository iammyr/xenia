package ie.xenia.util;

import ie.xenia.server.ServerProperties;

public class XeniaConstants {

	public static String NAMEDMODEL = "xenia_generic";

	public static final String URI_LIGHT = "http://dbpedia.org/resource/Light";
	public static final String URI_TEMP = "http://dbpedia.org/resource/Temperature";
	public static final String URI_ACCELEROMETER = "http://dbpedia.org/resource/Accelerometer";
	public static final String URI_PIR = "http://dbpedia.org/resource/Motion_(physics)";
	public static final String URI_PIR1 = "http://spitfire-project.eu/ontology/ns/ct/Motion";

	public static final String SINDICE_VOCAB = "http://sindice.com/vocab/fields#";
	public static final String SINDICE_SEARCH_VOCAB = "http://sindice.com/vocab/search#";


	public static final String SYSTEM_SEPARATOR = System.getProperty("file.separator");
	public static enum Roles {ADMIN, PUBLISHER}; 
	public static final String PINGTEXT = "Xenia Server is up and running :)";
	public static final String AUTHENTICATED_PINGTEXT = "XeniaServer: Authenticated as ";

	/** as stated at: http://www.w3.org/2008/01/rdf-media-types **/
	public static final String MEDIA_TYPE_TURTLE = "application/x-turtle";
	public static final String MEDIA_TYPE_NTRIPLES = "text/plain";
	public static final String MEDIA_TYPE_SPARQL_RESULTS = "application/sparql-results+xml";
	public static final String MEDIA_TYPE_RDF_JSON = "application/rdf+json";
	public static final String LANG_N3 = "N3";
	public static final String LANG_TURTLE = "TURTLE";
	public static final String LANG_RDFXML = "RDF/XML";
	public static final String LANG_RDFJSON = "RDF/JSON";
	public static final String LANG_NTRIPLE = "N-TRIPLE";
	public static final String LANG_RDFXML_ABBREV = "RDF/XML-ABBREV";

	public static final String RESOURCE_URI_BASE = "http://"
			+ System.getProperty(ServerProperties.SERVER) + ":"
			+ System.getProperty(String.valueOf(ServerProperties.PORT)) + "/"
			+ System.getProperty(ServerProperties.CONTEXT_ROOT) + "/";



	/** VoID dataset description URI. */
	public static final String voIDURI = RESOURCE_URI_BASE + "void";

	public static final String SEPARATOR1_ID = " ";
	public static final String SEPARATOR2_ID = "__";
	public static final String JSON_SEPARATOR = "_";

	public static String UOM_FILE_PATH = null;


}
