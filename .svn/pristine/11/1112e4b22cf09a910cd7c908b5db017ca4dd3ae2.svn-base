package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class EndSimulationMessageTest extends BasicMessageStub {
	String endSimulationMessage;
	
	@Before
	public void initdata() throws IOException {
		endSimulationMessage = readJson("EndSimulationMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		Message m = Message.getMessageFromJSON(endSimulationMessage);
		Assert.assertEquals(endSimulationMessage, m.toJSON());		
	}
}
