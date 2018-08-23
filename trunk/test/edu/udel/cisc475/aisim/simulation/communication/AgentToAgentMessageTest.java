package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AgentToAgentMessageTest extends BasicMessageStub {
	String agentToAgentMessage;
	
	@Before
	public void initdata() throws IOException {
		agentToAgentMessage = readJson("AgentToAgentMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		AgentToAgentMessage m = (AgentToAgentMessage) Message.getMessageFromJSON(agentToAgentMessage);
		Assert.assertTrue( m.toJSON().length() > 5);
		JSONObject content = m.getContent();
		Assert.assertEquals(1, content.getInt("Content1"));
		Assert.assertEquals(2, content.getInt("Content2"));
	}
}
