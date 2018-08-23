package edu.udel.cisc475.aisim.input;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;

import edu.udel.cisc475.aisim.input.InputParser;
import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;
import edu.udel.cisc475.aisim.tasktree.EnablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.FacilitatesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.Node;
import edu.udel.cisc475.aisim.tasktree.Task;
import edu.udel.cisc475.aisim.tasktree.TaskTree;

public class InputParserTest {
	
	//DOEST COMPILE -ben
	double seed = 1.5;
	
	@Test
	public void basicConstructorTest() {
		InputParser inPa = new InputParser("testdata/aisim/input/simpleCtaems.txt",seed);
		//inPa.readFile();
		Assert.assertNotNull(inPa.getFileData());
		inPa.parse();
	}
	
	@Test(expected=Exception.class)
	public void exceptionTest() {
		InputParser inPa = new InputParser("notExistingFile",seed);
		inPa.readFile();
	}
	
	@Test 
	public void testParse(){
		InputParser inPa = new InputParser("testdata//aisim//input//simpleCtaems.txt",seed);
		CtaemsLexer lexer = new CtaemsLexer(new ANTLRInputStream(
				inPa.getFileData()));
		CtaemsParser parser = new CtaemsParser(new CommonTokenStream(lexer));
	}
	
	@Test
	public void testTokens(){
		InputParser inPa = new InputParser("testdata//aisim//input//testCtaems.txt",seed);
		inPa.parse();
		Assert.assertEquals(true,inPa.readFile());
		
		InputData id = inPa.getInputData();
		Assert.assertEquals(true,id.getAgents().contains("Agent_Green"));
		Assert.assertEquals(false,id.getAgents().contains("Agent_Red"));
		
		String version = id.getVersion();
		Assert.assertEquals("V2_0", version);
		
		TaskTree tree = id.getTree();
		Node head = tree.getHead();
		
		Node expectedHead = new Task(null,"Problem1");
		Assert.assertEquals(expectedHead,head);
		
		Node window1 = tree.getNodeWithName("Window1");
		Assert.assertEquals("Problem_1",window1.getParent().getName());
		
		Method m = (Method) tree.getNodeWithName("Method1A");
		m.setQuality(20);
		Assert.assertEquals(20,m.getQuality(),10);
		
	}
	
	@Test 
	public void testTree(){
		InputParser inPa = new InputParser("testdata/aisim/input/testCtaems.txt",seed);
		inPa.parse();
		InputData id = inPa.getInputData();
		TaskTree tree = id.getTree();
		System.out.println("");
		Node head = new Task(null,"Window1");
		Node temp1 = new Method((Task)head,"temp1","Agent1");
		Node temp2 = new Method((Task)head,"temp2","Agent2");
		Node temp3 = new Method((Task)head,"temp3","Agent3");
		Node temp4 = new Method((Task)head,"temp4","Agent4");
		FacilitatesNodeRelationship fnr = new FacilitatesNodeRelationship(temp1, temp2, "fnr", 24.1, 15);
		EnablesNodeRelationship enr = new EnablesNodeRelationship(temp3, temp4, "enr");
		ArrayList<EnablesNodeRelationship> enrs = new ArrayList<EnablesNodeRelationship>();
		ArrayList<FacilitatesNodeRelationship> fnrs = new ArrayList<FacilitatesNodeRelationship>();
		enrs.add(enr);
		fnrs.add(fnr);
		temp1.addRelationship(enr);
		temp2.addRelationship(enr);
		temp3.addRelationship(fnr);
		temp4.addRelationship(fnr);
		
		Assert.assertEquals("enr",enrs.get(0).getName());
		Assert.assertEquals("fnr",fnrs.get(0).getName());
		
		Assert.assertEquals(enr,temp1.getRelationships().get(0));
		Assert.assertEquals(enr,temp2.getRelationships().get(0));
		Assert.assertEquals(fnr,temp3.getRelationships().get(0));
		Assert.assertEquals(fnr,temp4.getRelationships().get(0));
		
		
		ArrayList<Method> methods = new ArrayList<Method>();
		ArrayList<Task> tasks = new ArrayList<Task>();
		TaskTree t = new TaskTree(head);
		
		Assert.assertEquals(t,inPa.buildTree(enrs, fnrs, methods, tasks, head));
	}
	

}
