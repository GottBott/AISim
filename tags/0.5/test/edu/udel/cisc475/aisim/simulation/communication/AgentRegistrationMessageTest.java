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

public class AgentRegistrationMessageTest extends BasicMessageStub {
	String agentReg;
	
	@Before
	public void initdata() throws IOException {
		agentReg = readJson("AgentRegistrationMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		Message m = Message.getMessageFromJSON(agentReg);
		Assert.assertEquals(agentReg, m.toJSON());		
	}
}
