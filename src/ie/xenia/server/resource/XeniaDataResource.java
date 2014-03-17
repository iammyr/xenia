package ie.xenia.server.resource;

import ie.xenia.server.Server;
import ie.xenia.server.vocabulary.FoafVocab;
import ie.xenia.server.vocabulary.SiocVocab;
import ie.xenia.server.vocabulary.VoidVocab;
import ie.xenia.server.vocabulary.XeniaVocab;
import ie.xenia.util.XeniaConstants;

import java.util.Date;
import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.data.Preference;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.security.Role;
import org.restlet.security.User;
import org.restlet.service.MetadataService;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class XeniaDataResource extends ServerResource {
	
	/** Current user. */
	protected User user;

	/** Its role(s). */
	protected List<Role> roles;
	
	protected String credentialsName = null;
	protected String credentialsPassword = null;

	/** This server (xenia). */
	protected Server xeniaServer;

	/** Resource identification */
	protected String resourceId;

	/** To be retrieved from the URL as the 'timestamp' template parameter, or null. */
	protected String timestamp = null;

	/** Records the time at which each HTTP request was initiated. */
	protected long requestStartTime = new Date().getTime();

	/**  Preferred media type. */
	protected MediaType requestedMedia;

	//	/** Default URI for annotating unknown resources. */
	//	protected String defaultUri = null;

	/** Query string from the URI. */
	protected String query = null;

	/** Requested string. */
	protected String uristr = null;

	/** Vocabulary of Interlinked Data used to describe the Hackystat dataset. */
	public static final Model voIDModel = XeniaDataResource.initVoIDModel();

	/** Logger for messages. */
	protected java.util.logging.Logger logger = null;

	/**Dataset to handle the data stored in the triple db. */
	protected Dataset dataset = null;

	/**Submitted Entity. */
	protected Representation entity = null;

	/** Modality in which the user prefers to get a resource, i.e., linked with external data or not. */
	protected boolean linked = true;

	/** Name of the Named Graph where all the instances of each Service resource are stored. */
	protected String namedModel = null;

	private String generalNamedModel = null;



	@Override
	protected void doInit() throws ResourceException {
		this.user = getClientInfo().getUser();
		if (this.user == null){
			this.user = new User();
		}
		this.roles = getClientInfo().getRoles();
		this.requestedMedia = selectMedia(getClientInfo().getAcceptedMediaTypes());
		this.entity = getRequestEntity();
		MetadataService ms = getMetadataService(); 
        ms.addCommonExtensions(); 
        ms.addExtension("ttl", MediaType.APPLICATION_RDF_TURTLE);
        ms.addExtension("rdf", MediaType.APPLICATION_RDF_XML);
        ms.addExtension("n3", MediaType.TEXT_RDF_N3);
        
		//		if (this.requestedMedia == null){
		//			setStatusError("The requested Media Type " + requestedMedia + " is not supported .");
		//		}
		this.xeniaServer = (Server) getContext().getAttributes().get("XeniaServer");
		this.resourceId = ((String) getRequest().getAttributes().get("resource_id"));
		this.timestamp = (String) getRequest().getAttributes().get("timestamp");
		this.credentialsPassword = (String) getContext().getAttributes().get("password");
		this.credentialsName = (String) getContext().getAttributes().get("username");
		
		this.uristr = this.getRequest().getResourceRef().toString();
		this.generalNamedModel = this.xeniaServer.getHostName()+"graph/general";
		this.namedModel = XeniaConstants.NAMEDMODEL;
		if (this.namedModel == null){
			this.namedModel = generalNamedModel ;
		}
		this.query = ((String) getRequest().getAttributes().get("query"));

	
		logger = this.xeniaServer.getLogger();
	}

	

	private MediaType selectMedia(List<Preference<MediaType>> acceptedMediaTypes) {
		MediaType ret = null;
		MediaType media = null;
		if (acceptedMediaTypes.size() == 0){
			ret = MediaType.APPLICATION_ALL;
		}else{
			for (int i=0; i<acceptedMediaTypes.size() && ret==null ;i++){
				media = acceptedMediaTypes.get(i).getMetadata();
				if (media.equals(MediaType.ALL)
						|| media.equals(MediaType.APPLICATION_ALL) || media.equals(MediaType.TEXT_ALL)
						|| media.equals(MediaType.TEXT_RDF_N3) || media.equals(MediaType.APPLICATION_RDF_XML)
						|| media.equals(MediaType.TEXT_RDF_NTRIPLES)
						|| media.equals(MediaType.APPLICATION_RDF_TURTLE))
				{
					ret = media;
				}
			}
		}
		return ret;
	}

	/**
	 * Create the RDF model that describes this Spitfire published 
	 * sensor dataset, using the voID vocabulary.
	 */
	public static Model initVoIDModel() {
		Model model = ModelFactory.createDefaultModel();
		com.hp.hpl.jena.rdf.model.Resource dataset = model.createResource(XeniaVocab.NS);
		dataset.addProperty(RDF.type, VoidVocab.DATASET);
		dataset.addProperty(DC.title, "LD4Sensors");
		dataset.addProperty(DC.description,
				"Data about sensors, sensing devices in general and " +
				"sensor measurements stored in the LD4Sensors Triple DB"
				+ "published as Linked Data.");
		dataset.addProperty(VoidVocab.URI_REGEX_PATTERN, ".*resource/ov/.*");
		dataset.addProperty(VoidVocab.URI_REGEX_PATTERN, ".*resource/ov/sparql?query=.*");
		dataset.addProperty(DC.creator,
		"http://myr.altervista.org/foaf.rdf#iammyr");
		dataset
		.addProperty(
				DC.publisher,
				model
				.createResource()
				.addProperty(RDF.type, FoafVocab.ORGANIZATION)
				.addProperty(
						RDFS.label,
				"LD4Sensors - Digital Enterprise Research Institute (DERI) - National University of Ireland, Galway at Galway")
				.addProperty(FoafVocab.HOMEPAGE, "http://spitfire-project.eu/xenia"));
		/** The following subject URIs come from the UMBEL dataset (based upon OpenCyc). */
		dataset.addProperty(DC.subject, "http://umbel.org/umbel/sc/SoftwareProject");
		dataset.addProperty(DCTerms.accessRights, "http://www.gnu.org/copyleft/fdl.html");
		dataset.addProperty(VoidVocab.SPARQL_ENDPOINT,
		"http://spitfire-project.eu/xenia/ov/sparql?query=");
		dataset.addProperty(VoidVocab.VOCABULARY, FoafVocab.NS);
		dataset.addProperty(VoidVocab.VOCABULARY, SiocVocab.NS);
		dataset.addProperty(VoidVocab.VOCABULARY, VoidVocab.NS);
		dataset.addProperty(VoidVocab.VOCABULARY, DC.NS);
		dataset.addProperty(VoidVocab.VOCABULARY, OWL.NS);
		dataset.addProperty(VoidVocab.VOCABULARY, DCTerms.NS);
		dataset.addProperty(VoidVocab.VOCABULARY, "http://umbel.org/umbel/sc/");
		return model;
	}
	
}
