package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class MessageTest extends BasicMessageStub {
	String agentReg;
	String statusMethod;
	String startMethod;
	String nullType;

	@Before
	public void initdata() throws IOException {
		agentReg = readJson("AgentRegistrationMessage.json");
		statusMethod = readJson("AskMethodStatusMessage.json");
		startMethod = readJson("StartMethodMessage.json");
		nullType = readJson("NullMessage.json");

	}

	@Test
	public void getMessageFromJSONTest() throws JSONException {
		List<Message> mList = new ArrayList<Message>();
		mList.add(Message.getMessageFromJSON(agentReg));
		mList.add(Message.getMessageFromJSON(statusMethod));
		mList.add(Message.getMessageFromJSON(startMethod));
		for (Message m : mList) {
			Assert.assertNotNull(m.getSenderName());
			Assert.assertNotNull(m.getDestinationName());
		}
	}

	@Test
	public void getMessageFromJSON_Unknown_Test() throws JSONException {
		Message m = Message.getMessageFromJSON(nullType);
		Assert.assertNull(m);
	}

	@Test
	public void testgetLogMessageHeader() {
		Message m = new AgentRegistrationMessage("Agent 1");
		Assert.assertNotSame(m.getlogMessageHeader(), "");

	}

	@Test
	public void testgetLogMessageDetail() {
		Message m = new AgentRegistrationMessage("Agent 1");
		Assert.assertNotSame(m.getlogMessageDetail(), "");

	}

}
