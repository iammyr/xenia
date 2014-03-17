package ie.xenia.server;

import ie.xenia.database.MysqlManager;
import ie.xenia.server.resource.authentication.CredentialsManager;
import ie.xenia.server.resource.authentication.CredentialsManager.XeniaRole;
import ie.xenia.server.resource.authentication.RegisterResource;
import ie.xenia.server.resource.authentication.UsersAuthorizer;
import ie.xenia.server.resource.authentication.UsersEnroler;
import ie.xenia.server.resource.ping.PingResource;

import java.util.List;
import java.util.Map;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.ext.crypto.DigestAuthenticator;
import org.restlet.routing.Router;
import org.restlet.security.Role;

public class Server extends Application{

	private Component component;

	/** Holds the host name associated with this Server. */
	private String hostName;

	private DigestAuthenticator authenticator = null;

	private MysqlManager mysql = null;




	//	  /** Holds the logger for this Service. */
	//	  private Logger logger;

	/** Holds the ServerProperties instance for this Service. */
	private ServerProperties properties;


	public Server() {
		List<Role> allRoles = getRoles();
		// Complete the list of the application's known roles.
		// Used by ServerResource#isInRole(String), at the resource level
		Role r = null;
		for (XeniaRole xr : CredentialsManager.XeniaRole.values()){
			r = new Role();
			r.setName(xr.getName());			
			allRoles.add(r);
		}

	}

	/**
	 * Creates a new instance of the HTTP server, listening on the supplied port.
	 *
	 * @return The Server instance created.
	 * @throws Exception If problems occur starting up this server.
	 */
	public static Server newInstance() throws Exception {
		return newInstance(new ServerProperties());
	}

	/**
	 * Returns the full URI associated with this server. Example:
	 * "http://0.0.0.0:9877/ld4s"
	 *
	 * @return The host uri.
	 */
	public String getUri() {
		return "http://"+this.hostName;
	}

	/**
	 * Creates a new instance of the HTTP server suitable for unit testing. DPD
	 * properties are initialized from the User's dailyprojectdata.properties file, then set to their
	 * "testing" versions.
	 *
	 * @return The Server instance created.
	 * @throws Exception If problems occur starting up this server.
	 */
	public static Server newTestInstance() throws Exception {
		ServerProperties properties = new ServerProperties();
		//	    properties.setTestProperties();
		return newInstance(properties);
	}

	/**
	 * Creates a new instance of a LiSeD HTTP server, listening on the supplied port.
	 *
	 * @param properties The ServerProperties instance used to initialize this server.
	 * @return The Server instance created.
	 * @throws Exception If problems occur starting up this server.
	 */
	public static Server newInstance(ServerProperties properties) throws Exception {
		Server server = new Server();
		server.properties = properties;

		server.setMysql(new MysqlManager(server.properties.get(ServerProperties.DB_URL_KEY),
				server.properties.get(ServerProperties.DB_USERNAME_KEY),
				server.properties.get(ServerProperties.DB_PASSW_KEY),
				server.properties.get(ServerProperties.DB_NAME_KEY)));

		server.hostName = properties.get(ServerProperties.SERVER) + ":" + properties.get(ServerProperties.PORT_KEY) + "/" 
				+ properties.get(ServerProperties.CONTEXT_ROOT_KEY) + "/";

		server.component = new Component();
		server.component.getServers().add(Protocol.HTTP, Integer.valueOf(properties.get(ServerProperties.PORT_KEY)));
		server.component.getDefaultHost().attach("/" + properties.get(ServerProperties.CONTEXT_ROOT_KEY), server);

		// Create and store the JAXBContext instances on the server context.
		// They are supposed to be thread safe.
		Map<String, Object> attributes = server.getContext().getAttributes();

		// Provide a pointer to this server in the Context so that Resources can get at this server.
		attributes.put("XeniaServer", server);

		//		server.getMysql().initRoles();
		server.getMysql().initAuthorizations();




		// Now let's open for business.
		//	    server.logger.warning("Host: " + server.hostName);
		//
		//	    server.logger.warning("LD4Sensors (Version " + getVersion() + ") now running.");
		server.component.start();

		return server;
	}




	/**
	 * Starts up the web service. Control-c to exit.
	 *
	 * @param args Ignored.
	 * @throws Exception if problems occur.
	 */
	public static void main(final String[] args) throws Exception {
		Server.newInstance();
	}

	/**
	 * Dispatch to the specific LinkedServiceData resource based upon the URI. We will authenticate
	 * all requests.
	 *
	 * @return The router Restlet.
	 */
	@Override
	public Restlet createInboundRoot() {
		Context context = getContext();
		Router rootRouter = new Router(context);


		UsersEnroler usersEnroler = new UsersEnroler(mysql);	

		this.authenticator = createAuthenticator();
		//add the roles for this user to the request
		this.authenticator.setEnroler(usersEnroler);
		//check whether the user is allowed...
		UsersAuthorizer authorizer = new UsersAuthorizer(mysql);
		//to access this resource...
		Router authRouter = new Router(context);
		authRouter.attach("/ping", PingResource.class);
		authorizer.setNext(authRouter);
		//...which is guarded
		this.authenticator.setNext(authorizer);
		rootRouter.attach(authenticator);

		Router freeRouter = new Router(context);
		freeRouter.attach("/register", RegisterResource.class);
		DigestAuthenticator da = new DigestAuthenticator(null, "", ServerProperties.CONTEXT_ROOT);
		da.setNext(freeRouter);		
		rootRouter.attach(da);			






		return rootRouter;
	}

	public boolean authenticate(Request request, Response response) {
		if (!request.getClientInfo().isAuthenticated()) {
			authenticator.challenge(response, false);
			return false;
		}
		return true;
	}

	private DigestAuthenticator createAuthenticator(){
		String realm = ServerProperties.CONTEXT_ROOT+" Service: Authentication Required.";

		// MapVerifier isn't very secure; see docs for alternatives
		CredentialsManager verifier = new CredentialsManager(mysql);

		DigestAuthenticator auth = new DigestAuthenticator(null, realm, ServerProperties.CONTEXT_ROOT); 
		auth.setWrappedVerifier(verifier);

		return auth;
	}

	/**
	 * Returns the version associated with this Package, if available from the jar file manifest. If
	 * not being run from a jar file, then returns "Development".
	 *
	 * @return The version.
	 */
	public static String getVersion() {
		String version = Package.getPackage("ie.xenia.server")
				.getImplementationVersion();
		return (version == null) ? "Development" : version;
	}

	/**
	 * Returns the host name associated with this server. Example:
	 * "http://localhost:9877/ld4s"
	 *
	 * @return The host name.
	 */
	public String getHostName() {
		return this.hostName;
	}

	/**
	 * Returns the ServerProperties instance associated with this server.
	 *
	 * @return The server properties.
	 */
	public ServerProperties getServerProperties() {
		return this.properties;
	}

	public MysqlManager getMysql() {
		return mysql;
	}

	public void setMysql(MysqlManager mysql) {
		this.mysql = mysql;
	}





}

