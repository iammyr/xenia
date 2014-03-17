package ie.xenia.server.vocabulary;

import com.hp.hpl.jena.ontology.AnnotationProperty;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class FoafVocab {
	 /**
	   * <p>
	   * The ontology model that holds the vocabulary terms
	   * </p>
	   */
	  private static OntModel m_model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);

	  /**
	   * <p>
	   * The namespace of the vocabulary as a string
	   * </p>
	   */
	  public static final String NS = "http://xmlns.com/foaf/0.1/";

	  public static final String PREFIX = "foaf";

	  /**
	   * <p>
	   * The namespace of the vocabulary as a string
	   * </p>
	   *
	   * @see #NS
	   */
	  public static String getURI() {
	    return NS;
	  }

	  /**
	   * <p>
	   * The namespace of the vocabulary as a resource
	   * </p>
	   */
	  public static final Resource NAMESPACE = m_model.createResource(NS);

	  /** Factory for generating symbols */
	  /** private static KsValueFactory s_vf = new DefaultValueFactory(); */

	  // Vocabulary properties
	  // /////////////////////////
	  /**
	   * <p>
	   * Indicates a homepage of the service provide for this online account.
	   * </p>
	   */
	  public static final ObjectProperty ACCOUNT_SERVICE_HOMEPAGE = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/accountServiceHomepage");

	  /**
	   * <p>
	   * A location that something is based near, for some broadly human notion of near.
	   * </p>
	   */
	  public static final ObjectProperty BASED_NEAR = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/based_near");

	  /**
	   * <p>
	   * A current project this person works on.
	   * </p>
	   */
	  public static final ObjectProperty CURRENT_PROJECT = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/currentProject");

	  /**
	   * <p>
	   * A depiction of some thing.
	   * </p>
	   */
	  public static final ObjectProperty DEPICTION = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/depiction");

	  /**
	   * <p>
	   * A thing depicted in this representation.
	   * </p>
	   */
	  public static final ObjectProperty DEPICTS = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/depicts");

	  /**
	   * <p>
	   * An organization funding a project or person.
	   * </p>
	   */
	  public static final ObjectProperty FUNDED_BY = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/fundedBy");

	  /**
	   * <p>
	   * Indicates an account held by this agent.
	   * </p>
	   */
	  public static final ObjectProperty HOLDS_ACCOUNT = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/holdsAccount");

	  /**
	   * <p>
	   * A homepage for some thing.
	   * </p>
	   */
	  public static final ObjectProperty HOMEPAGE = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/homepage");

	  /**
	   * <p>
	   * An image that can be used to represent some thing (ie. those depictions which are particularly
	   * representative of something, eg. one's photo on a homepage).
	   * </p>
	   */
	  public static final ObjectProperty IMG = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/img");

	  /**
	   * <p>
	   * A page about a topic of interest to this person.
	   * </p>
	   */
	  public static final ObjectProperty INTEREST = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/interest");

	  /**
	   * <p>
	   * A person known by this person (indicating some level of reciprocated interaction between the
	   * parties).
	   * </p>
	   */
	  public static final ObjectProperty KNOWS = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/knows");

	  /**
	   * <p>
	   * A logo representing some thing.
	   * </p>
	   */
	  public static final ObjectProperty LOGO = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/logo");

	  /**
	   * <p>
	   * Something that was made by this agent.
	   * </p>
	   */
	  public static final ObjectProperty MADE = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/made");

	  /**
	   * <p>
	   * An agent that made this thing.
	   * </p>
	   */
	  public static final ObjectProperty MAKER = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/maker");

	  /**
	   * <p>
	   * A personal mailbox, ie. an Internet mailbox associated with exactly one owner, the first owner
	   * of this mailbox. This is a 'static inverse functional property', in that there is (across time
	   * and change) at most one individual that ever has any particular value for foaf:mbox.
	   * </p>
	   */
	  public static final ObjectProperty MBOX = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/mbox");

	  /**
	   * <p>
	   * Indicates a member of a Group
	   * </p>
	   */
	  public static final ObjectProperty MEMBER = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/member");

	  /**
	   * <p>
	   * A Myers Briggs (MBTI) personality classification.
	   * </p>
	   */
	  public static final ObjectProperty MYERS_BRIGGS = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/myersBriggs");

	  /**
	   * <p>
	   * An OpenID for an Agent.
	   * </p>
	   */
	  public static final ObjectProperty OPENID = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/openid");

	  /**
	   * <p>
	   * A page or document about this thing.
	   * </p>
	   */
	  public static final ObjectProperty PAGE = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/page");

	  /**
	   * <p>
	   * A project this person has previously worked on.
	   * </p>
	   */
	  public static final ObjectProperty PAST_PROJECT = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/pastProject");

	  /**
	   * <p>
	   * A phone, specified using fully qualified tel: URI scheme (refs:
	   * http://www.w3.org/Addressing/schemes.html#tel).
	   * </p>
	   */
	  public static final ObjectProperty PHONE = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/phone");

	  /**
	   * <p>
	   * The primary topic of some page or document.
	   * </p>
	   */
	  public static final ObjectProperty PRIMARY_TOPIC = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/primaryTopic");

	  /**
	   * <p>
	   * A link to the publications of this person.
	   * </p>
	   */
	  public static final ObjectProperty PUBLICATIONS = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/publications");

	  /**
	   * <p>
	   * A homepage of a school attended by the person.
	   * </p>
	   */
	  public static final ObjectProperty SCHOOL_HOMEPAGE = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/schoolHomepage");

	  /**
	   * <p>
	   * A theme.
	   * </p>
	   */
	  public static final ObjectProperty THEME = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/theme");

	  /**
	   * <p>
	   * A derived thumbnail image.
	   * </p>
	   */
	  public static final ObjectProperty THUMBNAIL = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/thumbnail");

	  /**
	   * <p>
	   * A tipjar document for this agent, describing means for payment and reward.
	   * </p>
	   */
	  public static final ObjectProperty TIPJAR = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/tipjar");

	  /**
	   * <p>
	   * A topic of some page or document.
	   * </p>
	   */
	  public static final ObjectProperty TOPIC = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/topic");

	  /**
	   * <p>
	   * A thing of interest to this person.
	   * </p>
	   */
	  public static final ObjectProperty TOPIC_INTEREST = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/topic_interest");

	  /**
	   * <p>
	   * A weblog of some thing (whether person, group, company etc.).
	   * </p>
	   */
	  public static final ObjectProperty WEBLOG = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/weblog");

	  /**
	   * <p>
	   * A work info homepage of some person; a page about their work for some organization.
	   * </p>
	   */
	  public static final ObjectProperty WORK_INFO_HOMEPAGE = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/workInfoHomepage");

	  /**
	   * <p>
	   * A workplace homepage of some person; the homepage of an organization they work for.
	   * </p>
	   */
	  public static final ObjectProperty WORKPLACE_HOMEPAGE = m_model
	      .createObjectProperty("http://xmlns.com/foaf/0.1/workplaceHomepage");

	  /**
	   * <p>
	   * Indicates the name (identifier) associated with this online account.
	   * </p>
	   */
	  public static final DatatypeProperty ACCOUNT_NAME = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/accountName");

	  /**
	   * <p>
	   * An AIM chat ID
	   * </p>
	   */
	  public static final DatatypeProperty AIM_CHAT_ID = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/aimChatID");

	  /**
	   * <p>
	   * The birthday of this Agent, represented in mm-dd string form, eg. '12-31'.
	   * </p>
	   */
	  public static final DatatypeProperty BIRTHDAY = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/birthday");

	  /**
	   * <p>
	   * A checksum for the DNA of some thing. Joke.
	   * </p>
	   */
	  public static final DatatypeProperty DNA_CHECKSUM = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/dnaChecksum");

	  /**
	   * <p>
	   * The family_name of some person.
	   * </p>
	   */
	  public static final DatatypeProperty FAMILY_NAME = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/family_name");

	  /**
	   * <p>
	   * The first name of a person.
	   * </p>
	   */
	  public static final DatatypeProperty FIRST_NAME = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/firstName");

	  /**
	   * <p>
	   * A textual geekcode for this person, see http://www.geekcode.com/geek.html
	   * </p>
	   */
	  public static final DatatypeProperty GEEKCODE = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/geekcode");

	  /**
	   * <p>
	   * The gender of this Agent (typically but not necessarily 'male' or 'female').
	   * </p>
	   */
	  public static final DatatypeProperty GENDER = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/gender");

	  /**
	   * <p>
	   * The given name of some person.
	   * </p>
	   */
	  public static final DatatypeProperty GIVENNAME = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/givenname");

	  /**
	   * <p>
	   * An ICQ chat ID
	   * </p>
	   */
	  public static final DatatypeProperty ICQ_CHAT_ID = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/icqChatID");

	  /**
	   * <p>
	   * A jabber ID for something.
	   * </p>
	   */
	  public static final DatatypeProperty JABBER_ID = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/jabberID");

	  /**
	   * <p>
	   * The sha1sum of the URI of an Internet mailbox associated with exactly one owner, the first
	   * owner of the mailbox.
	   * </p>
	   */
	  public static final DatatypeProperty MBOX_SHA1SUM = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/mbox_sha1sum");

	  /**
	   * <p>
	   * An MSN chat ID
	   * </p>
	   */
	  public static final DatatypeProperty MSN_CHAT_ID = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/msnChatID");

	  /**
	   * <p>
	   * A name for some thing.
	   * </p>
	   */
	  public static final DatatypeProperty NAME = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/name");

	  /**
	   * <p>
	   * A short informal nickname characterising an agent (includes login identifiers, IRC and other
	   * chat nicknames).
	   * </p>
	   */
	  public static final DatatypeProperty NICK = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/nick");

	  /**
	   * <p>
	   * A .plan comment, in the tradition of finger and '.plan' files.
	   * </p>
	   */
	  public static final DatatypeProperty PLAN = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/plan");

	  /**
	   * <p>
	   * A sha1sum hash, in hex.
	   * </p>
	   */
	  public static final DatatypeProperty SHA1 = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/sha1");

	  /**
	   * <p>
	   * The surname of some person.
	   * </p>
	   */
	  public static final DatatypeProperty SURNAME = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/surname");

	  /**
	   * <p>
	   * Title (Mr, Mrs, Ms, Dr. etc)
	   * </p>
	   */
	  public static final DatatypeProperty TITLE = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/title");

	  /**
	   * <p>
	   * A Yahoo chat ID
	   * </p>
	   */
	  public static final DatatypeProperty YAHOO_CHAT_ID = m_model
	      .createDatatypeProperty("http://xmlns.com/foaf/0.1/yahooChatID");

	  /**
	   * <p>
	   * Indicates the class of individuals that are a member of a Group
	   * </p>
	   */
	  public static final AnnotationProperty MEMBERSHIP_CLASS = m_model
	      .createAnnotationProperty("http://xmlns.com/foaf/0.1/membershipClass");

	  /**
	   * <p>
	   * A document that this thing is the primary topic of.
	   * </p>
	   */
	  public static final OntProperty IS_PRIMARY_TOPIC_OF = m_model
	      .createOntProperty("http://xmlns.com/foaf/0.1/isPrimaryTopicOf");

	  // Vocabulary classes
	  // /////////////////////////

	  /**
	   * <p>
	   * An agent (eg. person, group, software or physical artifact).
	   * </p>
	   */
	  public static final OntClass AGENT = m_model.createClass("http://xmlns.com/foaf/0.1/Agent");

	  /**
	   * <p>
	   * A document.
	   * </p>
	   */
	  public static final OntClass DOCUMENT = m_model.createClass("http://xmlns.com/foaf/0.1/Document");

	  /**
	   * <p>
	   * A class of Agents.
	   * </p>
	   */
	  public static final OntClass GROUP = m_model.createClass("http://xmlns.com/foaf/0.1/Group");

	  /**
	   * <p>
	   * An image.
	   * </p>
	   */
	  public static final OntClass IMAGE = m_model.createClass("http://xmlns.com/foaf/0.1/Image");

	  /**
	   * <p>
	   * An online account.
	   * </p>
	   */
	  public static final OntClass ONLINE_ACCOUNT = m_model
	      .createClass("http://xmlns.com/foaf/0.1/OnlineAccount");

	  /**
	   * <p>
	   * An online chat account.
	   * </p>
	   */
	  public static final OntClass ONLINE_CHAT_ACCOUNT = m_model
	      .createClass("http://xmlns.com/foaf/0.1/OnlineChatAccount");

	  /**
	   * <p>
	   * An online e-commerce account.
	   * </p>
	   */
	  public static final OntClass ONLINE_ECOMMERCE_ACCOUNT = m_model
	      .createClass("http://xmlns.com/foaf/0.1/OnlineEcommerceAccount");

	  /**
	   * <p>
	   * An online gaming account.
	   * </p>
	   */
	  public static final OntClass ONLINE_GAMING_ACCOUNT = m_model
	      .createClass("http://xmlns.com/foaf/0.1/OnlineGamingAccount");

	  /**
	   * <p>
	   * An organization.
	   * </p>
	   */
	  public static final OntClass ORGANIZATION = m_model
	      .createClass("http://xmlns.com/foaf/0.1/Organization");

	  /**
	   * <p>
	   * A person.
	   * </p>
	   */
	  public static final OntClass PERSON = m_model.createClass("http://xmlns.com/foaf/0.1/Person");

	  /**
	   * <p>
	   * A personal profile RDF document.
	   * </p>
	   */
	  public static final OntClass PERSONAL_PROFILE_DOCUMENT = m_model
	      .createClass("http://xmlns.com/foaf/0.1/PersonalProfileDocument");

	  /**
	   * <p>
	   * A project (a collective endeavour of some kind).
	   * </p>
	   */
	  public static final OntClass PROJECT = m_model.createClass("http://xmlns.com/foaf/0.1/Project");

	  // Vocabulary individuals
	  // /////////////////////////


}
