package ie.xenia.server.resource.authentication;

import ie.xenia.database.MysqlManager;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.ClientInfo;
import org.restlet.data.Reference;
import org.restlet.security.Authorizer;
import org.restlet.security.Role;

public class UsersAuthorizer extends Authorizer {
	
	private MysqlManager mysql = null;

	public UsersAuthorizer(MysqlManager mysql){
		this.setMysql(mysql);
	}
	@Override
	protected boolean authorize(Request request, Response response) {
		boolean authorized = true;
		//has the security roles and user from verifier and enroler
        ClientInfo info = request.getClientInfo();
        List<Role> roles = info.getRoles();
        //get http method
        String method = request.getMethod().getName();

        //need to get the route or resource user is attempting to access
        //allow or disallow access based on roles and method
        Reference ref = request.getResourceRef();
        String resource = ref.getIdentifier();
        Iterator<Role> it = roles.iterator();
        for (; it.hasNext() && authorized ;){
        	try {
				authorized = mysql.isAuthorized(it.next().getName(), method, resource);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return authorized;
	}
	public MysqlManager getMysql() {
		return mysql;
	}
	public void setMysql(MysqlManager mysql) {
		this.mysql = mysql;
	}

}
