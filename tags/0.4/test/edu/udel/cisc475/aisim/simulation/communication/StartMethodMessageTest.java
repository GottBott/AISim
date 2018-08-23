package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;
import junit.framework.Assert;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class StartMethodMessageTest extends BasicMessageStub {
	String startMethod;
	
	@Before
	public void initdata() throws IOException {
		startMethod = readJson("StartMethodMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		StartMethodMessage m = (StartMethodMessage) Message.getMessageFromJSON(startMethod);
		Assert.assertEquals(startMethod, m.toJSON());
		Assert.assertNotNull(m.getMethodName());
	}
}
