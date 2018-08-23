package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NextTickMessageTest extends BasicMessageStub {
	String nextTickMessage;
	
	@Before
	public void initdata() throws IOException {
		nextTickMessage = readJson("NextTickMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		NextTickMessage m = (NextTickMessage) Message.getMessageFromJSON(nextTickMessage);
		Assert.assertTrue( m.toJSON().length() > 5);
		Assert.assertNotNull(m.getTick());
	}
}
