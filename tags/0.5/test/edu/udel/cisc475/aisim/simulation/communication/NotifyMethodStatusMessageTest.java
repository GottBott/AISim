package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;
import org.junit.Assert;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class NotifyMethodStatusMessageTest extends BasicMessageStub {
	String notifyMethodStatusMessage;
	
	@Before
	public void initdata() throws IOException {
		notifyMethodStatusMessage = readJson("NotifyMethodStatusMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		NotifyMethodStatusMessage m = (NotifyMethodStatusMessage) Message.getMessageFromJSON(notifyMethodStatusMessage);
		Assert.assertTrue( m.toJSON().length() > 5);
		Assert.assertNotNull(m.getMethodName());
		Assert.assertTrue(m.isInProgress());
		Assert.assertFalse(m.isCompleted());
		Assert.assertTrue(m.isEnabled());
	}
}
