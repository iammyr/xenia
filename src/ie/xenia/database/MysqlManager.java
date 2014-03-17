package ie.xenia.database;

import ie.xenia.resource.user.Person;
import ie.xenia.server.resource.authentication.CredentialsManager.XeniaRole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EnumMap;

public class MysqlManager {

	private static final String aeskey = "hola que tal?";

	private String dbUrl = null;

	private String dbName = null;

	private String dbUser = null;

	private String dbPassw = null;

	public MysqlManager(String url, String username, String password, String dbname){
		this.dbUrl = url;
		this.dbName = dbname;
		this.dbUser = username;
		this.dbPassw = password;
	}

	public Connection connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Connection conn = null;
		//		String url = "jdbc:mysql://localhost:3306/";
		//		String dbName = "jdbctutorial";
		String driver = "com.mysql.jdbc.Driver";
		//		String username = "root"; 
		//		String password = "root";
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(dbUrl+dbName,dbUser,dbPassw);
		System.out.println("Connected to the database");

		return conn;
	}

	public static boolean disconnect(Connection conn){
		boolean disconnected = false;
		if (conn != null){
			try {
				conn.close();
				disconnected = true;
				System.out.println("Disconnected from the database");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return disconnected;
	}

	/**
	 * 
	 * @param user
	 * @param conn
	 * @return -1 error occurred
	 * 			0 incomplete data submitted
	 * 			1 success
	 * 			2 already registered
	 * @throws SQLException 
	 */
	public int register(Person user) throws SQLException{
		int registered = -1;		
		String username = null, password = null;
		if (user == null || (password=user.getPassword()) == null || user.getName() == null || (username=user.getName().getNick()) == null){
			return 0;
		}
		Connection conn = null;
		try{
			conn = connect();
			if (isRegistered(user.getName().getNick())){
				return 2;
			}

			//		// Hash a password 
			//		// gensalt's log_rounds parameter determines the complexity
			//		// the work factor is 2**log_rounds, and the default is 10
			//		password = BCrypt.hashpw(password, BCrypt.gensalt(12));

			String query = "INSERT INTO users (username, password, first, last, email, homepage, weblog, uri";
			if (user.getGender() != null){
				query += ", gender";
			}
			query += ") "
					+"VALUES ('"+username+"', " +
					"aes_encrypt('"+password+"', '"+ MysqlManager.aeskey +"'), " +
					"'"+user.getName().getFirst()+"', " +
					"'"+user.getName().getLast()+"', " +
					"'"+user.getEmail()+"', " +
					"'"+user.getHomepage()+"', " +
					"'"+user.getWeblog()+"', " +
					"'"+user.getUri()+"'";
			if (user.getGender() != null){
				query +=  "', " +"'"+user.getGender().name();
			}
			query += ")";
			Statement st;
			st = conn.createStatement();		
			System.out.println("Executing query: "+query);
			st.executeUpdate(query);
			registered = 1;
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			MysqlManager.disconnect(conn);
		}

		return registered;
	}

	public boolean isRegistered (String username) throws SQLException{
		boolean isregistered = false;
		String query = "select * from users where username = '"+username+"';";
		Statement st;
		Connection conn = null;
		try{
			conn = connect();

			st = conn.createStatement();		
			System.out.println("Executing query: "+query);
			ResultSet rs = st.executeQuery(query);
			isregistered = rs.first();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			MysqlManager.disconnect(conn);
		}
		return isregistered;
	}

	//	public static boolean passwordVerifier(String candidatePassword){
	//
	//		String hashedFromDb = null;		
	//
	//		// Check that an unencrypted password matches one that has
	//		// previously been hashed
	//		return BCrypt.checkpw(candidatePassword, hashedFromDb);
	//	}

	public String getPassword(String username){
		String password = null;
		String query = "SELECT AES_DECRYPT(password, '"+MysqlManager.aeskey+"') AS unencrypted " +
				"FROM users where username='"+username+"';";
		Statement st;
		Connection conn = null;
		try{
			conn = connect();

			st = conn.createStatement();		
			System.out.println("Executing query: "+query);
			ResultSet rs = st.executeQuery(query);
			if (rs.first()){
				password = rs.getString("unencrypted");
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			MysqlManager.disconnect(conn);
		}
		return password;
	}

	public EnumMap<XeniaRole, Integer> getRoles(String username){
		EnumMap<XeniaRole, Integer> roles = new EnumMap<XeniaRole, Integer>(XeniaRole.class);
		String query = "SELECT role FROM users where username='"+username+"';";
		Statement st;
		Connection conn = null;
		try{
			conn = connect();

			st = conn.createStatement();		
			System.out.println("Executing query: "+query);
			ResultSet rs = st.executeQuery(query);
			String rname = null;
			int i = 0;
			while (rs.next()){
				rname = rs.getString("role");
				if (rname != null){
					for (XeniaRole r : XeniaRole.values()){
						if (r.getName().compareTo(rname) == 0){
							roles.put(r, ++i);
						}
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			MysqlManager.disconnect(conn);
		}
		return roles;		
	}

	public boolean isAuthorized(String role, String method, String resource) throws SQLException{
		boolean isauthorized = true;
		String query = "SELECT * FROM forbids where role='"+role+"';";
		Statement st;
		Connection conn = null;
		try{
			conn = connect();

			st = conn.createStatement();		
			System.out.println("Executing query: "+query);
			ResultSet rs = st.executeQuery(query);
			String forbmethod = null, forbresource = null;
			while (rs.next()){
				forbmethod = rs.getString("forbmethod");
				forbresource = rs.getString("forbresource");
				if (forbmethod.compareTo(method) == 0){
					if (forbresource.compareTo(resource) == 0
							|| forbresource.compareTo("any") == 0){
						isauthorized = false;
					}
				}				
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			MysqlManager.disconnect(conn);
		}
		return isauthorized;
	}

	public void initRoles(){
		String base = "insert into roles (name) values('", query = null;
		Statement st = null;
		for (XeniaRole xr : XeniaRole.values()){
			query = base + xr.getName()+"');";
			Connection conn = null;
			try{
				conn = connect();

				st = conn.createStatement();		
				System.out.println("Executing query: "+query);
				st.executeUpdate(query);
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				MysqlManager.disconnect(conn);
			}
		}
	}

	public void initAuthorizations() throws SQLException{
		String base = "insert into forbids (forbmethod, forbresource) values('", query = null;
		Statement st = null;
		for (XeniaRole xr : XeniaRole.values()){
			Connection conn = null;
			try{
				conn = connect();

				switch (xr){
				case ANONYMOUS:
					query = base+"get', 'users');";
					st = conn.createStatement();		
					System.out.println("Executing query: "+query);
					st.executeUpdate(query);
					query = base+"post', 'any');";
					st = conn.createStatement();		
					System.out.println("Executing query: "+query);
					st.executeUpdate(query);
					query = base+"put', 'any');";
					st = conn.createStatement();		
					System.out.println("Executing query: "+query);
					st.executeUpdate(query);
					break;
				case ADMIN:
					break;
				case DEVELOPER:
					break;
				case USER:
					break;
				default:
					break;


				}
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				MysqlManager.disconnect(conn);
			}

		}
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassw() {
		return dbPassw;
	}

	public void setDbPassw(String dbPassw) {
		this.dbPassw = dbPassw;
	}

}
