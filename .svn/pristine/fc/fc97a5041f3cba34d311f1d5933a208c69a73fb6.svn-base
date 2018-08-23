package edu.udel.cisc475.aisim.input;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import edu.udel.cisc475.aisim.tasktree.Node;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;
import edu.udel.cisc475.aisim.tasktree.TaskTree;

public class InputParserTest {
	
    long seed = (long) 1.5;
	
	@Test
	public void basicConstructorTest() throws InvalidFileException {
		InputParser inPa = new InputParser("testdata/aisim/input/simpleCtaems.txt",seed);
		Assert.assertNotNull(inPa.getFileData());
		inPa.parse();
	}
	
	@Test(expected=Exception.class)
	public void exceptionTest() {
		InputParser inPa = new InputParser("notExistingFile",seed);
		inPa.readFile();
	}
	
	@Test(expected=Exception.class) 
	public void testErrors() throws InvalidFileException{
		InputParser inPa = new InputParser("testdata/aisim/input/errorCtaems.txt",seed);
		inPa.parse();
		InputData id = inPa.getInputData();
		Assert.assertNull(id);
	}
	
	@Test 
	public void testNotNull() throws InvalidFileException{
		InputParser inPa = new InputParser("testdata/aisim/input/simpleCtaems.txt",seed);
		inPa.parse();
		InputData id = inPa.getInputData();
		TaskTree tree = id.getTree();
		Assert.assertNotNull(id);
		Assert.assertNotNull(tree);
		Assert.assertNotNull(id.getVersion());
		Assert.assertNotNull(id.getAgents());
		Assert.assertNotNull(id.getTree());		
	}
	
	@Test
	public void testParse() throws InvalidFileException{
		InputParser inPa = new InputParser("testdata/aisim/input/simpleCtaems.txt",seed);
		inPa.parse();
		Assert.assertEquals(true,inPa.readFile());

		InputData id = inPa.getInputData();
		Assert.assertEquals(true,id.getAgents().contains("Agent_Green"));
		Assert.assertEquals(false,id.getAgents().contains("NotAnAgent"));
		
		String version = id.getVersion();
		Assert.assertEquals("V2_0", version);
		
		TaskTree tree = id.getTree();
		Node head = tree.getHead();
		
		Assert.assertEquals("Problem1",head.getName());
		
		Node window1 = tree.getNodeWithName("Window1");
		Assert.assertEquals("Problem1",window1.getParent().getName());
		
		Node m1 = tree.getNodeWithName("Method1A");
		Assert.assertNotNull(m1);
		
		Node m2 = tree.getNodeWithName("NotAMethod");
		Assert.assertNull(m2);
		
		ArrayList<NodeRelationship> relationships = m1.getRelationships();
		Assert.assertNotNull(relationships);
		Assert.assertEquals(1,relationships.size());		
		Assert.assertEquals("facilitates4",relationships.get(0).getName());
		Assert.assertEquals("Method1A",relationships.get(0).getFromNode().getName());
		Assert.assertEquals("Setup3aB",relationships.get(0).getToNode().getName());
	}
	
	@Test 
	public void testNewtaems() throws InvalidFileException {
		InputParser inPa = new InputParser("testdata/aisim/input/newtaemsTest.txt",seed);
		inPa.parse();
		InputData id = inPa.getInputData();
				
	}
}
