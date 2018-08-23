package edu.udel.cisc475.aisim.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;

import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;
import edu.udel.cisc475.aisim.tasktree.Distribution;
import edu.udel.cisc475.aisim.tasktree.EnablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.FacilitatesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.Node;
import edu.udel.cisc475.aisim.tasktree.Task;
import edu.udel.cisc475.aisim.tasktree.TaskTree;

/**
 * This class is responsible for reading in the cTAEMS input file and passing
 * the data to the simulation.
 * 
 */
public class InputParser extends SimulationParser {
	private InputData inputData;
	private double seed;

	enum QAF {
		AND, OR, SUM
	}

	public static final int AgentToken = 1, VersionToken = 2,
			TaskGroupToken = 3, TaskToken = 4, MethodToken = 5,
			EnablesToken = 6, FacilitatesToken = 7, Spaces = 8;

	public InputParser(String fileLocation, Double seed) {
		super(fileLocation);
		inputData = null;
		this.seed = seed;
	}

	/**
	 * Reads in the configuration file. NOTE: currently does nothing.
	 * 
	 * @param inputFileLocation
	 *            The location of the configuration file within the project.
	 * 
	 * @return True if the file was read without throwing any exceptions, false
	 *         otherwise.
	 */
	@Override
	public boolean readFile() {
		String currDirect = null;
		String dataOut = "";

		/*try {
			currDirect = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		//File f = new File(currDirect + this.getFileLocation());
		System.out.println(this.getFileLocation());
		File f = new File(this.getFileLocation());
		
		FileReader fr = null;

		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(fr);
		String line = "";

		try {
			while ((line = reader.readLine()) != null) {
				if (!line.contains(";"))// Removes all comments(;) from cTAEMS
										// files.
					dataOut += line + " ";
			}
			reader.close();
			fr.close();
			this.fileData = dataOut;
			System.out.println(dataOut);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Parses the data currently in fileData
	 */
	@Override
	public void parse() {
		readFile();
		CtaemsLexer lexer = new CtaemsLexer(new ANTLRInputStream(
				this.getFileData()));
		CtaemsParser parser = new CtaemsParser(new CommonTokenStream(lexer));
		distributeTokens(parser, lexer);
	}

	public void distributeTokens(CtaemsParser parser, CtaemsLexer lexer) {
		HashSet<String> agents = new HashSet<String>();
		Map<String, Node> nodes = new HashMap<String, Node>();
		ArrayList<EnablesNodeRelationship> enrs = new ArrayList<EnablesNodeRelationship>();
		ArrayList<FacilitatesNodeRelationship> fnrs = new ArrayList<FacilitatesNodeRelationship>();
		ArrayList<Method> methods = new ArrayList<Method>();
		ArrayList<Task> tasks = new ArrayList<Task>();
		Map<String,Node> subTaskMap = new HashMap<String,Node>(); 
		String version = "";
		Task head = null;
		Node from = null;
		Node to = null;
		int i = 0;

		while (parser.token != "done") {
			parser.parse();
			CommonToken currToken = (CommonToken) parser.getTokenStream()
					.get(i);
			switch (currToken.getType()) {
			case AgentToken:
				CtaemsLexer agentLexer = new CtaemsLexer(new ANTLRInputStream(
						currToken.getText()));
				CtaemsParser agentParser = new CtaemsParser(
						new CommonTokenStream(agentLexer));
				agentParser.labelParse();
				String agentName = agentParser.labelParseString;
				agents.add(agentName);
				break;
			case EnablesToken:
				CtaemsLexer enablesLexer = new CtaemsLexer(
						new ANTLRInputStream(currToken.getText()));
				CtaemsParser enablesParser = new CtaemsParser(
						new CommonTokenStream(enablesLexer));
				enablesParser.labelParse();
				String enableName = enablesParser.labelParseString;

				enablesLexer.reset();
				enablesParser.reset();
				enablesParser.fromParse();
				String e_from = enablesParser.fromParseString;

				enablesLexer.reset();
				enablesParser.reset();
				enablesParser.toParse();
				String e_to = enablesParser.toParseString;
				
				from = subTaskMap.get(e_from);
				to = subTaskMap.get(e_to);
				
				EnablesNodeRelationship enr = new EnablesNodeRelationship(from,
						to, enableName);

				enrs.add(enr);
				break;
			case FacilitatesToken:
				CtaemsLexer facilitatesLexer = new CtaemsLexer(
						new ANTLRInputStream(currToken.getText()));
				CtaemsParser facilitatesParser = new CtaemsParser(
						new CommonTokenStream(facilitatesLexer));
				facilitatesParser.labelParse();
				String facilitatesName = facilitatesParser.labelParseString;

				facilitatesLexer.reset();
				facilitatesParser.reset();
				facilitatesParser.fromParse();
				String f_from = facilitatesParser.fromParseString;

				facilitatesLexer.reset();
				facilitatesParser.reset();
				facilitatesParser.toParse();
				String f_to = facilitatesParser.toParseString;

				facilitatesLexer.reset();
				facilitatesParser.reset();
				facilitatesParser.qualityPowerParse();
				double[] qualityPowerArray = facilitatesParser.qualityPowerArray;				

				facilitatesLexer.reset();
				facilitatesParser.reset();
				facilitatesParser.durationPowerParse();
				double[] durationPowerArray = facilitatesParser.durationPowerArray;
							
				Node fromNode = subTaskMap.get(f_from);				
				Node toNode = subTaskMap.get(f_to);
								
				double newQ = to.getQuality() / qualityPowerArray[0];
				//double newD = ((Method) to).getDuration() * durationPowerArray[0];
				double newD = 10;
				
				FacilitatesNodeRelationship fnr = new FacilitatesNodeRelationship(fromNode, toNode, facilitatesName, newQ, (int) newD);
				fnrs.add(fnr);
				break;
			case MethodToken:
				CtaemsLexer methodLexer = new CtaemsLexer(new ANTLRInputStream(
						currToken.getText()));
				CtaemsParser methodParser = new CtaemsParser(
						new CommonTokenStream(methodLexer));
				methodParser.labelParse();
				String methodName = methodParser.labelParseString;

				methodLexer.reset();
				methodParser.reset();
				methodParser.agentParse();
				String methodAgent = methodParser.agentParseString;

				methodLexer.reset();
				methodParser.reset();
				methodParser.densityParse();
				String methodDensity = methodParser.densityParseString;

				methodLexer.reset();
				methodParser.reset();
				methodParser.qualityDistributionParse();
				double[] methodQualityDistribution = methodParser.qualityDistributionArray;

				methodLexer.reset();
				methodParser.reset();
				methodParser.durationDistributionParse();
				double[] methodDurationDistribution = methodParser.durationDistributionArray;
				
				ArrayList<Double> vals = new ArrayList<Double>();
				ArrayList<Double> probs = new ArrayList<Double>();
				for(int k = 0; k < methodQualityDistribution.length; k+=2){
					vals.add(methodQualityDistribution[k]);
					probs.add(methodQualityDistribution[k+1]);			
				}				
				Distribution dQuality = new Distribution(vals,probs);
				double quality = dQuality.evaluate(seed);
				
				vals = new ArrayList<Double>();
				probs = new ArrayList<Double>();
				for(int k = 0; k < methodDurationDistribution.length; k+=2){
					vals.add(methodDurationDistribution[k]);
					probs.add(methodDurationDistribution[k+1]);
				}				
				Distribution dDuration = new Distribution(vals,probs);
				int duration = (int) dDuration.evaluate(seed);
							
				Method method = new Method(null, methodName, methodAgent);
				method.setAgent(methodAgent);
				method.setQuality(quality);
				method.setDuration(duration);
				methods.add(method);
				subTaskMap.put(methodName, method);
				break;
			case TaskGroupToken:
				CtaemsLexer taskGroupLexer = new CtaemsLexer(
						new ANTLRInputStream(currToken.getText()));
				CtaemsParser taskGroupParser = new CtaemsParser(
						new CommonTokenStream(taskGroupLexer));

				taskGroupParser.labelParse();
				String taskGroupName = taskGroupParser.labelParseString;

				taskGroupLexer.reset();
				taskGroupParser.reset();
				taskGroupParser.subtaskParse();
				String[] taskGroupSubtasks = taskGroupParser.subtaskArray;
				
				taskGroupLexer.reset();
				taskGroupParser.reset();
				taskGroupParser.subtaskParse();
				String taskGroupQaf = taskGroupParser.qafParseString;
				
				head = new Task(null,taskGroupName);
				subTaskMap.put(taskGroupName, head);
				
				//for(int j = 0; j < taskGroupSubtasks.length; j++){
				//	Task newSubTask = new Task(head,taskGroupSubtasks[j]);
				//	subTaskMap.put(taskGroupSubtasks[j], newSubTask);
				//	head.addSubTask(newSubTask);
				//}
				break;
				
			case TaskToken:
				CtaemsLexer taskLexer = new CtaemsLexer(new ANTLRInputStream(
						currToken.getText()));
				CtaemsParser taskParser = new CtaemsParser(
						new CommonTokenStream(taskLexer));
				taskParser.labelParse();
				String taskName = taskParser.labelParseString;

				taskLexer.reset();
				taskParser.reset();
				taskParser.earliestStartTimeParse();
				String taskEST = "-1";
				
				if (taskParser.hasEST)
					taskEST = taskParser.earliestStartTimeParseString;

				taskLexer.reset();
				taskParser.reset();
				taskParser.deadlineParse();
				String taskDeadline = "-1";
				
				if (taskParser.hasDeadline)
					taskDeadline = taskParser.deadlineParseString;

				taskLexer.reset();
				taskParser.reset();
				taskParser.subtaskParse();
				String[] taskSubtasks = taskParser.subtaskArray;

				taskLexer.reset();
				taskParser.reset();
				taskParser.qafParse();
				
				Task task = new Task(null,taskName);
				subTaskMap.put(taskName, task);
				
				if(taskDeadline != "-1")
					task.setDeadline((int) Integer.parseInt(taskDeadline));
				
				if(taskEST != "-1")
					task.setEarliestStartTime((int) Integer.parseInt(taskEST));
				
				task.setQaf(taskParser.qafParseString);
				
				tasks.add(task);
				
				break;
			case VersionToken:
				CtaemsLexer versionLexer = new CtaemsLexer(
						new ANTLRInputStream(currToken.getText()));
				CtaemsParser versionParser = new CtaemsParser(
						new CommonTokenStream(versionLexer));
				versionParser.versionParse();
				version = versionParser.versionParseString;
				break;
			}

			i++;
		}
		
		CtaemsLexer lexer2 = new CtaemsLexer(new ANTLRInputStream(
				this.getFileData()));
		CtaemsParser parser2 = new CtaemsParser(new CommonTokenStream(lexer2));
		i = 0;
		while(parser2.token != "done"){
			parser2.parse();
			CommonToken currToken = (CommonToken) parser2.getTokenStream()
					.get(i);
			switch (currToken.getType()) {
				
				case TaskToken:
					CtaemsLexer taskLexer = new CtaemsLexer(new ANTLRInputStream(
							currToken.getText()));
					CtaemsParser taskParser = new CtaemsParser(
							new CommonTokenStream(taskLexer));
					taskParser.labelParse();
					String taskName = taskParser.labelParseString;

				    taskLexer.reset();
					taskParser.reset();
					
					taskParser.subtaskParse();
					String[] taskSubtasks = taskParser.subtaskArray;
					Task task = (Task) subTaskMap.get(taskName);
					
					for(int j = 0; j < taskSubtasks.length; j++){
						Node newSubTask = subTaskMap.get(taskSubtasks[j]);
						newSubTask.setParent(task);
						task.addSubTask(newSubTask);
					}
					
					break;
					
				case TaskGroupToken:
					CtaemsLexer taskGroupLexer = new CtaemsLexer(new ANTLRInputStream(
							currToken.getText()));
					CtaemsParser taskGroupParser = new CtaemsParser(
							new CommonTokenStream(taskGroupLexer));
					taskGroupParser.labelParse();
					String taskGroupName = taskGroupParser.labelParseString;

				    taskGroupLexer.reset();
					taskGroupParser.reset();
					
					taskGroupParser.subtaskParse();
					String[] taskGroupSubtasks = taskGroupParser.subtaskArray;
					
					head = (Task) subTaskMap.get(taskGroupName);
					
					for(int j = 0; j < taskGroupSubtasks.length; j++){
						Node newSubTask = subTaskMap.get(taskGroupSubtasks[j]);
						newSubTask.setParent(head);
						head.addSubTask(newSubTask);
					}
					
					break;
					
				case MethodToken:
					CtaemsLexer methodLexer = new CtaemsLexer(new ANTLRInputStream(
							currToken.getText()));
					CtaemsParser methodParser = new CtaemsParser(
							new CommonTokenStream(methodLexer));
					methodParser.labelParse();
					String methodName = methodParser.labelParseString;
					break;
					
				default:
					int k = 1;
					break;
					
			}
			i++;
		}
		TaskTree tree = buildTree(enrs, fnrs, methods, tasks, head);
		this.inputData = new InputData(tree, version, agents);
		
		
	}

	public TaskTree buildTree(ArrayList<EnablesNodeRelationship> enrs,
			ArrayList<FacilitatesNodeRelationship> fnrs,
			ArrayList<Method> methods, ArrayList<Task> tasks, Node head) {
				
		for(int k = 0; k < enrs.size(); k++){
			EnablesNodeRelationship curr = enrs.get(k);
			Node toNode = curr.getToNode();
			Node fromNode = curr.getFromNode();
			toNode.addRelationship(curr);
			fromNode.addRelationship(curr);
		}
		
		for(int k = 0; k < fnrs.size(); k++){
			FacilitatesNodeRelationship curr = fnrs.get(k);
			Node toNode = curr.getToNode();
			Node fromNode = curr.getFromNode();
			toNode.addRelationship(curr);
			fromNode.addRelationship(curr);
		}
	
		TaskTree tree = new TaskTree(head);
		return tree;
	}

	public InputData getInputData() {

		return inputData;
	}
}
