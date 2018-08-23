package edu.udel.cisc475.aisim.input;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

public class FileExceptionsTest {

	InvalidConfigurationException ce;
	InvalidInputException ie;
	ArrayList<FileError> errors;
	
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("FileExceptionTest");
    }
	
	@Before
	public void setUp() {
		errors = new ArrayList<FileError>();
		errors.add(new FileError(2, "ERROR"));
		
		ce = new InvalidConfigurationException(errors);
		ie = new InvalidInputException(errors);
	}
	
	@Test
	public void testExceptions() {
		Assert.assertEquals(errors, ce.getErrors());
		Assert.assertEquals(errors, ie.getErrors());
	}
}
