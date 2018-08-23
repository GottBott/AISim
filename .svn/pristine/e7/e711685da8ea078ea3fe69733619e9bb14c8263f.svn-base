package edu.udel.cisc475.aisim;

//This is a test file just to test the post-commit hook
import java.util.ArrayList;

import edu.udel.cisc475.aisim.input.CtaemsBaseListener;
import edu.udel.cisc475.aisim.input.CtaemsParser.AgentParseContext;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Task;
import edu.udel.cisc475.aisim.tasktree.Task.QAF;


public class Test
{	
	private static ArrayList<NodeRelationship> relationships;
	


	public int addSomeNumbers(int a, int b){
		return a + b;
	}
	
	public int getNFib(int n){
		if(n <= 2){
			return 1;
		}
		else{
			return getNFib(n-1) + getNFib(n-2);
		}
	}
	
	
	public static void main(String[] args)
	{ 
	
		QAF qaf = null;
		
		/*
		 As the evaluate methode of the noderelationship is abstract in the class 
		 so it should be declared first to be called
		  */
		NodeRelationship n = new NodeRelationship(null, null, null) {
			
			@Override
			public void evaluate() {
				// TODO Auto-generated method stub
				
			}
		};
		
		
	// a task object is declared to setup different funtions 
			
		Task t1=new Task(relationships.get(1).getFromNode().getParent(), "task1", 4, true, false, relationships, 5, 10,QAF.AND);
		t1.addSubTask(null);
		t1.calculateQualityFromChildren();
		t1.disable();
		t1.enable();
		int deadline=t1.getDeadline();
		t1.setParent(t1);
		t1.setQaf("qaf");
		t1.setQuality(1);
		t1.getEarliestStartTime();
		t1.getName();
		t1.getParent();
		t1.getQaf();
		t1.getQuality();
		t1.getRelationships();
		
		// These are the funtions of the CtaemsBaseListner all of them are just prototypes as the funtions declared in the 
		//CtaemBaselistemer have no defination (empty) you can see the that class
		CtaemsBaseListener ctbaselistener = new CtaemsBaseListener();
		/*
		 ctx is the context from the agent parse context class which is passed in to
		 these methods ...they are just empty because or bull because they are just declared
		 in the CtaemBaselistener class not implemented with defination
		  */
		AgentParseContext ctx = null;
		ctbaselistener.enterAgentParse(ctx);
		ctbaselistener.enterEveryRule(ctx);
		ctbaselistener.enterDurationPowerParse(null);
		ctbaselistener.enterDeadlineParse(null);
		ctbaselistener.enterDensityParse(null);
		ctbaselistener.enterDurationPowerParse(null);
		ctbaselistener.enterEarliestStartTimeParse(null);
		ctbaselistener.enterFromParse(null);
		ctbaselistener.enterLabelParse(null);
		ctbaselistener.enterParse(null);
		ctbaselistener.enterQafParse(null);
		ctbaselistener.enterQualityDistributionParse(null);
		ctbaselistener.enterQualityPowerParse(null);
		ctbaselistener.enterSubtaskParse(null);
		ctbaselistener.enterToParse(null);
		ctbaselistener.exitVersionParse(null);
		
		System.out.println("Deadline of the project +  ::");
		System.exit(0);
		
	}
}

