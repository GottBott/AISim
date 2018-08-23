package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;
import org.junit.Assert;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class ConfirmMethodStartMessageTest extends BasicMessageStub {
	String confirmMethodStartMessage;
	
	@Before
	public void initdata() throws IOException {
		confirmMethodStartMessage = readJson("ConfirmMethodStartMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		ConfirmMethodStartMessage m = (ConfirmMethodStartMessage) Message.getMessageFromJSON(confirmMethodStartMessage);
		Assert.assertTrue( m.toJSON().length() > 5);
		Assert.assertNotNull(m.getMethodName());
		Assert.assertTrue(m.isStarted());
	}
}
