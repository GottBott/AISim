package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;
import junit.framework.Assert;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class NotifyMethodCompletedMessageTest extends BasicMessageStub {
	String notifyMethodCompletedMessage;
	
	@Before
	public void initdata() throws IOException {
		notifyMethodCompletedMessage = readJson("NotifyMethodCompletedMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		NotifyMethodCompletedMessage m = (NotifyMethodCompletedMessage) Message.getMessageFromJSON(notifyMethodCompletedMessage);
		Assert.assertTrue( m.toJSON().length() > 5);
		Assert.assertNotNull(m.getMethodName());
		Assert.assertTrue((m.getQuality() + m.getDuration()) > 10);
	}
}
