package ie.xenia.test;

import ie.xenia.database.MysqlManager;
import ie.xenia.resource.user.Person;
import ie.xenia.server.Server;
import ie.xenia.server.resource.authentication.CredentialsManager.XeniaRole;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;

public class XeniaTestHelper {
	
	protected String dbUrl = "jdbc:mysql://localhost:3306/";
	protected String dbUser = "'myrdebby'";
	protected String dbPassw = "myrdebby";
	protected String dbName = "xenia";
	
	protected MysqlManager mysql = null;
	
	protected String personNick = "myr";
	protected String personPassword = "secret";
	
	/** The admin username. */
	protected static final String admin = "admin";
	/** The admin password. */
	protected static final String admin_password = "admin";
	/** The testing user. */
	protected static final String user = "scott";
	/** The testing user password. */
	protected static final String user_password = "tiger";
	/** The testing user role. */
	protected static final XeniaRole user_role = XeniaRole.ADMIN;
	/** The Xenia server used in these tests. */
	private static Server xeniaServer;

	/** Milliseconds shift from the base time as a starting point of a time range. */
	protected String start_range = "5800";

	/** Milliseconds shift from the base time as an ending point of a time range. */
	protected String end_range = "10321";
	
	/** Milliseconds shift from the base time. */
	protected String base_datetime = "12-08-23T19:03Z";

	/** Locations in the form <spacerel # <name | lat_long>>. */
	protected String[] locations = new String[]{" # madrid", "near # 12.009_24.500"
			, "near # 19.489_23.52", "in # spain"};
	
	protected String location_name = "Patras";
	
	protected String location_coords = "38.24444_21.73444";
	
	protected Person author = new Person(
//			"Ioannis", "Chatzigiannakis", null, null, null, null, null);
			"Manfred", "Hauswirth", null, null, null, null, null);
	
	

	/**
	 * Constructor.
	 */
	public XeniaTestHelper() {
		// Does nothing.
	}

	/**
	 * Starts the server going for these tests.
	 * @throws Exception If problems occur setting up the server.
	 */
	@BeforeClass 
	public static void setupServer() throws Exception {
		// Create a testing version of the Ld4S.
		XeniaTestHelper.xeniaServer = Server.newInstance();
	}

	/**
	 * Returns the hostname associated with this Xenia test server.
	 *
	 * @return The host name, including the context root.
	 */
	protected String getXeniaHostName() {
		return XeniaTestHelper.xeniaServer.getHostName();
	}


	/**
	 * Returns the Xenia server instance.
	 *
	 * @return The Xenia server instance.
	 */
	protected Server getXeniaServer() {
		return XeniaTestHelper.xeniaServer;
	}

	protected JsonGenerator getAuthor(Person author) throws JsonGenerationException, JsonMappingException, IOException{
		JsonGenerator gen = null;
		ObjectMapper mapper = new ObjectMapper(); 
		mapper.writeValue(gen, author);
		
		return gen;
	}
	

}
