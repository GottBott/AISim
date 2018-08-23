package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;
import junit.framework.Assert;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class SetRandomSeedMessageTest extends BasicMessageStub {
	String setRandomSeed;
	
	@Before
	public void initdata() throws IOException {
		setRandomSeed = readJson("SetRandomSeedMessage.json");
	}
	
	@Test
	public void toJSONTest() throws JSONException {
		SetRandomSeedMessage m = (SetRandomSeedMessage) Message.getMessageFromJSON(setRandomSeed);
		Assert.assertTrue( m.toJSON().length() > 5);
		Assert.assertNotNull(m.getSeed());
	}
}
