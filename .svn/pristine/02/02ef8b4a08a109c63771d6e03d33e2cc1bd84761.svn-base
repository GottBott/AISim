package edu.udel.cisc475.aisim;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class DummyFileTest
{
	private DummyFile dummy;

	public static void main(String args[])
	{
		org.junit.runner.JUnitCore.main("DummyTest");
    }

    @Before
    public void setUp()
    {
    	dummy = new DummyFile();

    }

    @Test
    public void testAddSomeNumbers()
    {
    	
	int result = dummy.addSomeNumbers(4, 5);

    	assertTrue(result == 9);
    }

    @Test
    public void testGetNFib()
    {
    	int result = dummy.getNFib(5);

    	assertTrue(result == 5);
    }
}
