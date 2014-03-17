package ie.xenia.server.resource.authentication;

import ie.xenia.database.MysqlManager;

import java.sql.SQLException;

import org.restlet.security.LocalVerifier;

public class CredentialsManager extends LocalVerifier {

	public enum XeniaRole{
		ADMIN("admin", 1), DEVELOPER("developer", 2), USER("user", 3), ANONYMOUS("anonymous", 4);

		private String name = null;
		private int code = -1;

		private XeniaRole(String name, int code){
			this.name = name;
			this.code = code;
		}

		public String getName(){
			return this.name;
		}	

		public int getCode(){
			return this.code;
		}

	}

	private MysqlManager mysql = null;
	
	public CredentialsManager(MysqlManager mysql){
		this.mysql = mysql;
	}

	@Override
	public char[] getLocalSecret(String identifier) {
		try {
			if (mysql.isRegistered(identifier)){
				String pass = mysql.getPassword(identifier);
				if (pass != null){
					return pass.toCharArray();	
				}				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//        if ("login".equals(identifier)) {
		//            return "secret".toCharArray();
		//        }

		return null;
	}

	public MysqlManager getMysql() {
		return mysql;
	}

	public void setMysql(MysqlManager mysql) {
		this.mysql = mysql;
	}


}
