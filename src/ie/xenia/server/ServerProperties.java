package ie.xenia.server;

import ie.xenia.util.XeniaConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class ServerProperties {

	
	public static final String RULES_FILE_KEY = "xenia.rules.file";
	public static final String SENSORS_RDF_DIR_KEY = "xenia.sensors.rdf.dir";
	
	
	
	public static final String DB_URL = "jdbc:mysql://localhost:3306/";
	public static final String DB_URL_KEY = "xenia.db.url";
	
	public static final String DB_USERNAME = "myrdebby";
	public static final String DB_USERNAME_KEY = "xenia.db.username";
	
	
	public static final String DB_PASSW = "myrdebby";
	public static final String DB_PASSW_KEY = "xenia.db.passw";
	
	public static final String DB_NAME = "xenia";
	public static final String DB_NAME_KEY = "xenia.db.name";
	
	public static String SERVER = "http://0.0.0.0";
	public static final int PORT = 8182;
	public static final String CONTEXT_ROOT = "xenia";

	/** The xenia server admin e-mail */
	public static final String ADMIN_EMAIL_KEY = "xenia.admin.email";
	/** The xenia hostname key. */
	public static final String HOSTNAME_KEY = "xenia.hostname";
	/** The xenia context root. */
	public static final String CONTEXT_ROOT_KEY = "xenia.context.root";
	/** The logging level key. */
	public static final String LOGGING_LEVEL_KEY = "xenia.logging.level";
	/** The xenia port key. */
	public static final String PORT_KEY = "xenia.port";
	/** The RDF directory key. */
	public static final String RDF_DIR_KEY = "xenia.rdf.dir";
	/** The directory containing the Unit of Measurement file. */
	public static final String UOM_FILE_KEY = "xenia.uom.file";
	/** The Restlet Logging key. */
	public static final String RESTLET_LOGGING_KEY = "xenia.restlet.logging";
	/** The dpd port key during testing. */
	public static final String TEST_PORT_KEY = "xenia.test.port";
	/** The test installation key. */
	public static final String TEST_INSTALL_KEY = "xenia.test.install";
	/** The test installation key. */
	public static final String TEST_HOSTNAME_KEY = "xenia.test.hostname";

	/** Where we store the properties. */
	private Properties properties;
	
	private final String foldername = System.getProperty("user.home") + XeniaConstants.SYSTEM_SEPARATOR
	+".xenia";

	/**
	 * Creates a new ServerProperties instance. Prints an error to the console if problems occur on
	 * loading.
	 */
	public ServerProperties() {
		try {
			initializeProperties();
//			initTDB();
		}
		catch (Exception e) {
			System.out.println("Error initializing server properties: " + e.getMessage());
		}
	}

	

	/**
	 * Reads in the properties in ~/.hackystat/xenia/xenia.properties if this
	 * file exists, and provides default values for all properties.
	 *
	 * @throws Exception if errors occur.
	 */
	private void initializeProperties() throws Exception {
		String propFile = getFoldername()+XeniaConstants.SYSTEM_SEPARATOR+"xenia.properties",
		userDir = System.getProperty("user.dir");
		String SENSORS_RDF_DIR_DEFAULT = System.getProperty("user.dir") + XeniaConstants.SYSTEM_SEPARATOR+".xenia"+
		XeniaConstants.SYSTEM_SEPARATOR+"sensors-rdf";
		String UOM_FILE_DEFAULT = System.getProperty("user.dir") + XeniaConstants.SYSTEM_SEPARATOR+".xenia"+
		XeniaConstants.SYSTEM_SEPARATOR+"uom"+XeniaConstants.SYSTEM_SEPARATOR+"ucum-essence.xml";
		String RULES_FILE_DEFAULT = System.getProperty("user.dir") + XeniaConstants.SYSTEM_SEPARATOR+".xenia"+
		XeniaConstants.SYSTEM_SEPARATOR+"rules.txt";
		
		this.properties = new Properties();
		// Set defaults
		properties.setProperty(DB_NAME_KEY, DB_NAME);
		properties.setProperty(DB_USERNAME_KEY, DB_USERNAME);
		properties.setProperty(DB_PASSW_KEY, DB_PASSW);
		properties.setProperty(DB_URL_KEY, DB_URL);
		properties.setProperty(CONTEXT_ROOT_KEY, CONTEXT_ROOT);
		properties.setProperty(HOSTNAME_KEY, InetAddress.getLocalHost().getHostAddress());
		ServerProperties.SERVER = properties.getProperty(HOSTNAME_KEY);
		properties.setProperty(PORT_KEY, String.valueOf(PORT));
		properties.setProperty(CONTEXT_ROOT_KEY, CONTEXT_ROOT);
		properties.setProperty(LOGGING_LEVEL_KEY, "INFO");
		
		properties.setProperty(RDF_DIR_KEY, userDir + XeniaConstants.SYSTEM_SEPARATOR+".xenia"+
				XeniaConstants.SYSTEM_SEPARATOR+"rdf");
		properties.setProperty(SENSORS_RDF_DIR_KEY, SENSORS_RDF_DIR_DEFAULT);
		properties.setProperty(UOM_FILE_KEY, UOM_FILE_DEFAULT);
		properties.setProperty(RULES_FILE_KEY, RULES_FILE_DEFAULT);
		
		properties.setProperty(TEST_PORT_KEY, "9875");
		properties.setProperty(TEST_HOSTNAME_KEY, "0.0.0.0");
		//	    properties.setProperty(CACHE_ENABLED, "true");
		//	    properties.setProperty(CACHE_MAX_LIFE, "365");
		//	    properties.setProperty(CACHE_CAPACITY, "500000");
		properties.setProperty(ADMIN_EMAIL_KEY, "admin");
		
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(propFile);
			System.out.println("Loading Xenia properties from: " + propFile);
			properties.load(stream);
		}
		catch (IOException e) {
			System.out.println(propFile + " not found.");
			//try to create the required folder and file
			File folder = new File(getFoldername());
			if (!folder.exists()){
				folder.mkdir();
			}
			File file = new File (propFile);
			if (folder.exists() && !file.exists()){
				try{if(file.createNewFile()){
					trimProperties(properties);
					properties.store(new FileOutputStream(propFile), null);
					System.out.println("File "+propFile+" created.");
				}}catch(IOException e1){ System.out.println("Unable to create "+propFile); }
			}
			System.out.println("Using default xenia properties.");
		}
		finally {
			if (stream != null) {
				stream.close();
			}
		}

		trimProperties(properties);
		// Now add to System properties.
		Properties systemProperties = System.getProperties();
		systemProperties.putAll(properties);
		System.setProperties(systemProperties);
	}

	/**
	 * Sets the following properties' values to their "test" equivalent.
	 * <ul>
	 * <li>HOSTNAME_KEY
	 * <li>PORT_KEY
	 * <li>DEFINITIONS_DIR
	 * </ul>
	 * Also sets TEST_INSTALL_KEY's value to "true".
	 */
	public void setTestProperties() {
		properties.setProperty(HOSTNAME_KEY, properties.getProperty(TEST_HOSTNAME_KEY));
		properties.setProperty(PORT_KEY, properties.getProperty(TEST_PORT_KEY));
		properties.setProperty(TEST_INSTALL_KEY, "true");
		trimProperties(properties);
	}

	/**
	 * Returns the value of the Server Property specified by the key.
	 *
	 * @param key Should be one of the public static final strings in this class.
	 * @return The value of the key, or null if not found.
	 */
	public String get(String key) {
		return this.properties.getProperty(key);
	}

	/**
	 * Ensures that the there is no leading or trailing whitespace in the property values. The fact
	 * that we need to do this indicates a bug in Java's Properties implementation to me.
	 *
	 * @param properties The properties.
	 */
	private void trimProperties(Properties properties) {
		// Have to do this iteration in a Java 5 compatible manner. no stringPropertyNames().
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			String propName = (String) entry.getKey();
			properties.setProperty(propName, properties.getProperty(propName).trim());
		}
	}

	/**
	 * Returns the fully qualified host name, such as "http://0.0.0.0:9877/xenia/".
	 *
	 * @return The fully qualified host name.
	 */
	public String getFullHost() {
		return "http://" + get(HOSTNAME_KEY) + ":" + get(PORT_KEY) + "/" + get(CONTEXT_ROOT_KEY) + "/";
	}




	/**
	 * Returns a string containing all current properties in alphabetical order.
	 *
	 * @return A string with the properties.
	 */
	public String echoProperties() {
		String cr = System.getProperty("line.separator");
		String eq = " = ";
		String pad = "                ";
		// Adding them to a treemap has the effect of alphabetizing them.
		TreeMap<String, String> alphaProps = new TreeMap<String, String>();
		for (Map.Entry<Object, Object> entry : this.properties.entrySet()) {
			String propName = (String) entry.getKey();
			String propValue = (String) entry.getValue();
			alphaProps.put(propName, propValue);
		}
		StringBuffer buff = new StringBuffer(30);
		buff.append("Xenia Properties:").append(cr);
		for (String key : alphaProps.keySet()) {
			buff.append(pad).append(key).append(eq).append(get(key)).append(cr);
		}
		return buff.toString();
	}



	public String getFoldername() {
		return foldername;
	}
	
}
