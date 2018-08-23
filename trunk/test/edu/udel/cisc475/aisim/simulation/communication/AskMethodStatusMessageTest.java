package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class AskMethodStatusMessageTest extends BasicMessageStub {
	String statusMethod;
	
	@Before
	public void initdata() throws IOException {
		statusMethod = readJson("AskMethodStatusMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		AskMethodStatusMessage m = (AskMethodStatusMessage) Message.getMessageFromJSON(statusMethod);
		Assert.assertEquals(statusMethod, m.toJSON());
		Assert.assertNotNull(m.getMethodName());
	}
}
