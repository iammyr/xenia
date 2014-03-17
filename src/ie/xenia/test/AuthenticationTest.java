package ie.xenia.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.restlet.data.ChallengeRequest;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class AuthenticationTest extends XeniaTestHelper{
	
	@Test
	public void testAuthPing(){
		ClientResource resource = new ClientResource("http://localhost:8182/xenia/ping");
		
		resource.setChallengeResponse(ChallengeScheme.HTTP_DIGEST, this.personNick, personPassword);
		//1. Send the first request with insufficient authentication.
		try {
		    resource.get();
		} catch (ResourceException re) {
		}
		// Should be 401, since the client needs some data sent by the server in
		// order to complete the ChallengeResponse.
		assertTrue(resource.getStatus().getCode() == 401);
		
		//2. get the required data for the final computation of a correct ChallengeResponse object:
		// Complete the challengeResponse object according to the server's data
		// 1- Loop over the challengeRequest objects sent by the server.
		ChallengeRequest c1 = null;
		for (ChallengeRequest challengeRequest : resource.getChallengeRequests()) {
		    if (ChallengeScheme.HTTP_DIGEST.equals(challengeRequest.getScheme())) {
		        c1 = challengeRequest;
		        break;
		    }
		}
		
		ChallengeResponse challengeResponse = new ChallengeResponse(c1,
                resource.getResponse(),
                this.personNick,
                personPassword.toCharArray());
		
		//3.  the request is completed with the computed ChallengeResponse instance
		resource.setChallengeResponse(challengeResponse);

		// Try authenticated request
		resource.get();
		// Should be 200.
		assertTrue(resource.getStatus().isSuccess());
		
	}

}
