package ie.xenia.server.resource.authentication;

import ie.xenia.database.MysqlManager;
import ie.xenia.server.resource.authentication.CredentialsManager.XeniaRole;

import java.util.EnumMap;

import org.restlet.data.ClientInfo;
import org.restlet.security.Enroler;
import org.restlet.security.Role;
import org.restlet.security.User;

public class UsersEnroler implements Enroler  {
	
	private MysqlManager mysql = null;
	
	public UsersEnroler(MysqlManager mysql){
		this.mysql = mysql;
	}

	@Override
	public void enrole(ClientInfo clientInfo) {
//		Request request = Request.getCurrent(); 
		User user = clientInfo.getUser();		
		if (user != null){
			EnumMap<XeniaRole, Integer> allUserRoles = mysql.getRoles(user.getIdentifier());
            if (allUserRoles != null) {
                for (XeniaRole userRole : allUserRoles.keySet()) {
                    Role role = new Role(userRole.getName());
                    clientInfo.getRoles().add(role);
                }
            }			
		}
		
	}

}
