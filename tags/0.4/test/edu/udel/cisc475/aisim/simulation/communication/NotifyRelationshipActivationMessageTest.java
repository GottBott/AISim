package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;
import org.junit.Assert;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class NotifyRelationshipActivationMessageTest extends BasicMessageStub {
	String notifyRelationshipActivationMessage;
	
	@Before
	public void initdata() throws IOException {
		notifyRelationshipActivationMessage = readJson("NotifyRelationshipActivationMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		NotifyRelationshipActivationMessage m = (NotifyRelationshipActivationMessage) Message.getMessageFromJSON(notifyRelationshipActivationMessage);
		Assert.assertTrue( m.toJSON().length() > 5);
		Assert.assertEquals("SIMULATOR", m.getSenderName());
		Assert.assertEquals("Something", m.getRelationshipName());
	}
}
