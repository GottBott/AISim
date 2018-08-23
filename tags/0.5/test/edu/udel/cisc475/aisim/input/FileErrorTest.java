package edu.udel.cisc475.aisim.input;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class FileErrorTest {
	
	FileError e;
	
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("FileErrorTest");
    }
	
	@Before
	public void setUp() {
		e = new FileError(2, "ERROR");
	}
	
	@Test
	public void testFileError() {
		Assert.assertEquals(2, e.getLineNumber());
		Assert.assertEquals("ERROR", e.getError());
	}
	
	@Test
	public void testToString() {
		String s = "Line number: " + 2 + ", error: ERROR";
		Assert.assertEquals(s, e.toString());
	}
}
