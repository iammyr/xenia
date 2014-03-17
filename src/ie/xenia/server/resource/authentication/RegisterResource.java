package ie.xenia.server.resource.authentication;

import ie.xenia.server.resource.XeniaDataResource;

import org.codehaus.jackson.JsonGenerator;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;

public class RegisterResource extends XeniaDataResource{
	
	@Post
	public Representation register(JsonGenerator obj){
		Representation ret = null;
		
		return ret;		
	}

}
