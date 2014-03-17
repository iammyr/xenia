package ie.xenia.server.resource.ping;

import ie.xenia.server.resource.XeniaDataResource;
import ie.xenia.util.XeniaConstants;

import org.codehaus.jackson.JsonGenerator;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public class PingResource extends XeniaDataResource{

	@Get
	public String ping() {
		StringBuilder sb = new StringBuilder();
		if (this.xeniaServer.authenticate(getRequest(), getResponse())){
			sb.append("XeniaServer is up and running. You are logged in.");
			if (credentialsName != null){
				sb.append(credentialsName + " is a registered user.");
			}
		}else{
			sb.append("Xenia Server is up and running :) You are not logged in.");
		}
		return sb.toString();
	}

	@Post 
	public Representation register(JsonGenerator obj){
		Representation ret = null;
		//register the user
		return ret;

	}

}
