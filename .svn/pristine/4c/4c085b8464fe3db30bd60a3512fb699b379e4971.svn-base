package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class StartSimulationMessageTest extends BasicMessageStub {
	String startSimulationMessage;
	
	@Before
	public void initdata() throws IOException {
		startSimulationMessage = readJson("StartSimulationMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		Message m = Message.getMessageFromJSON(startSimulationMessage);
		Assert.assertEquals(startSimulationMessage, m.toJSON());		
	}
}