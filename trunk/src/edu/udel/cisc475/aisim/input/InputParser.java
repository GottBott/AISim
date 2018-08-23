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
import java.util.Random;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;

import edu.udel.cisc475.aisim.tasktree.DisablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Distribution;
import edu.udel.cisc475.aisim.tasktree.EnablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.FacilitatesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.HindersNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.Node;
import edu.udel.cisc475.aisim.tasktree.Task;
import edu.udel.cisc475.aisim.tasktree.TaskTree;

/**
 * This class is responsible for reading in the cTAEMS input file and passing
 * the data to the simulation.
 * 
 */
public class InputParser {

	private String fileLocation;
	private String fileData;

	/**
	 * The input data that will be created after the parse. It will contain all
	 * information in the cTAEMS file.
	 */
	private InputData inputData;

	/**
	 * A random number generator that will be used to calculate
	 * qualities/durations from their respective distributions.
	 */
	private Random randGen;

	enum QAF {
		AND, OR, SUM
	}

	/**
	 * All of the tokens that can be expected in the cTAEMS file.
	 */
	public static final int AgentToken = 1, VersionToken = 2,
			TaskGroupToken = 3, TaskToken = 4, MethodToken = 5,
			EnablesToken = 6, FacilitatesToken = 7, DisablesToken = 8,
			HindersToken = 9, spaces = 10, SpliceToken = 11, TimeToken=12;

	public InputParser(String fileLocation, long seed) {
		this.fileLocation = fileLocation;
		fileData = "";
		inputData = null;
		this.randGen = new Random(seed);
	}

	/**
	 * Reads in the input file.
	 * 
	 * @param inputFileLocation
	 *            The location of the configuration file within the project.
	 * 
	 * @return True if the file was read without throwing any exceptions, false
	 *         otherwise.
	 */
	public boolean readFile() {
		String dataOut = "";

		File f = new File(fileLocation);

		FileReader fr = null;

		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(fr);
		String line = "";
		int parens = 0;
		boolean newtaems = false;
		try {
			while ((line = reader.readLine()) != null) {
				if (!line.contains(";")) {// Removes all comments(;) from cTAEMS
										  // files.
					if(!line.contains("spec_newtaems")) { //Preproc out newtaems toekn cause parsing is dumb
														  //but keep what's inside
						if(newtaems) {
							parens += line.length() - line.replace("(", "").length(); //count "(" in line
							parens -= line.length() - line.replace(")", "").length(); //count ")" in line
							if (parens != 0) { //not end of newtaems section
								dataOut += line + " ";
							}
							else { //end of what would be the newtaems section
								dataOut += line.substring(0, line.lastIndexOf(')')) + " ";
								dataOut += "(time 0) "; //things after this arrive normally
								newtaems = false;
							}
						}
						else {
							dataOut += line + " ";
						}
					}
					else { //we saw newtaems so let's start counting parens!
						parens += line.length() - line.replace("(", "").length();
						parens -= line.length() - line.replace(")", "").length();
						newtaems = true;
					}
				}
			}
			reader.close();
			fr.close();
			this.fileData = dataOut;
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Parses the data currently in fileData.
	 * 
	 * @throws InvalidFileException
	 */
	public void parse() throws InvalidFileException {
		readFile();
		CtaemsLexer lexer = new CtaemsLexer(new ANTLRInputStream(fileData));
		CtaemsParser parser = new CtaemsParser(new CommonTokenStream(lexer));
		distributeTokens(parser, lexer);
	}

	/**
	 * This function provides the main functionality for parsing the various
	 * tokens present within the cTAEMS file.
	 * 
	 * @param parser
	 *            The CtaemsParser to be used for parsing.
	 * @param lexer
	 *            The CtaemsLexer to be used for lexing.
	 * @throws InvalidFileException
	 */
	public void distributeTokens(CtaemsParser parser, CtaemsLexer lexer)
			throws InvalidFileException {
		HashSet<String> agents = new HashSet<String>();
		ArrayList<EnablesNodeRelationship> enrs = new ArrayList<EnablesNodeRelationship>();
		ArrayList<DisablesNodeRelationship> dnrs = new ArrayList<DisablesNodeRelationship>();
		ArrayList<HindersNodeRelationship> hnrs = new ArrayList<HindersNodeRelationship>();
		ArrayList<FacilitatesNodeRelationship> fnrs = new ArrayList<FacilitatesNodeRelationship>();
		ArrayList<Method> methods = new ArrayList<Method>();
		ArrayList<Task> tasks = new ArrayList<Task>();
		Map<String, Node> subTaskMap = new HashMap<String, Node>();
		ArrayList<FileError> errors = new ArrayList<FileError>();
		String version = "";
		String parseType = "";
		Task head = null;
		int i = 0;
		int taskGroupCount = 0;
		int timeInt = 0;

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
				// Error Check -- No name specified
				if (agentName == "ERROR") {
					FileError fe = new FileError(i, "No Agent name specified");
					errors.add(fe);
					break;
				}
				// End Error Check

				agents.add(agentName);
				break;

			case EnablesToken:
				CtaemsLexer enablesLexer = new CtaemsLexer(
						new ANTLRInputStream(currToken.getText()));
				CtaemsParser enablesParser = new CtaemsParser(
						new CommonTokenStream(enablesLexer));
				enablesParser.labelParse();
				String enableName = enablesParser.labelParseString;

				// Error Check -- No name specified
				if (enableName == "ERROR") {
					FileError fe = new FileError(i, "No Enables name specified");
					errors.add(fe);
					break;
				}
				// End Error Check

				enablesLexer.reset();
				enablesParser.reset();
				enablesParser.fromParse();
				String e_from = enablesParser.fromParseString;

				enablesLexer.reset();
				enablesParser.reset();
				enablesParser.toParse();
				String e_to = enablesParser.toParseString;

				Node from;
				Node to;

				from = subTaskMap.get(e_from);
				to = subTaskMap.get(e_to);

				// Error Checks -- To/From doesn't exist.
				if (from == null) {
					FileError fe = new FileError(i, "From Node Doesn't Exist");
					errors.add(fe);
					if (to == null) {
						FileError fe2 = new FileError(i, "To Node Doesn't Exist");
						errors.add(fe2);
						break;
					}
					break;
				}
				if (to == null) {
					FileError fe = new FileError(i, "To Node Doesn't Exist");
					errors.add(fe);
					break;
				}
				// End Error Checks

				EnablesNodeRelationship enr = new EnablesNodeRelationship(from,
						to, enableName);
				if (timeInt != 0) {
					enr.setArrived(false);
					enr.setArrivalTime(timeInt);
				}

				enrs.add(enr);

				break;

			case DisablesToken:
				CtaemsLexer disablesLexer = new CtaemsLexer(
						new ANTLRInputStream(currToken.getText()));
				CtaemsParser disablesParser = new CtaemsParser(
						new CommonTokenStream(disablesLexer));
				disablesParser.labelParse();
				String disableName = disablesParser.labelParseString;

				// Error Check -- No name specified
				if (disableName == "ERROR") {
					FileError fe = new FileError(i,
							"No Disables name specified");
					errors.add(fe);
					break;
				}
				// End Error Check

				disablesLexer.reset();
				disablesParser.reset();
				disablesParser.fromParse();
				String d_from = disablesParser.fromParseString;

				disablesLexer.reset();
				disablesParser.reset();
				disablesParser.toParse();
				String d_to = disablesParser.toParseString;

				Node fromNode_d;
				Node toNode_d;

				fromNode_d = subTaskMap.get(d_from);
				toNode_d = subTaskMap.get(d_to);

				// Error Checks -- To/From doesn't exist.
				if (fromNode_d == null) {
					FileError fe = new FileError(i, "From Node Doesn't Exist");
					errors.add(fe);
					if (toNode_d == null) {
						FileError fe2 = new FileError(i, "To Node Doesn't Exist");
						errors.add(fe2);
						break;
					}
					break;
				}
				if (toNode_d == null) {
					FileError fe = new FileError(i, "To Node Doesn't Exist");
					errors.add(fe);
					break;
				}
				// End Error Checks

				DisablesNodeRelationship dnr = new DisablesNodeRelationship(
						fromNode_d, toNode_d, disableName);
				if (timeInt != 0) {
					dnr.setArrived(false);
					dnr.setArrivalTime(timeInt);
				}

				dnrs.add(dnr);

				break;

			case FacilitatesToken:
				CtaemsLexer facilitatesLexer = new CtaemsLexer(
						new ANTLRInputStream(currToken.getText()));
				CtaemsParser facilitatesParser = new CtaemsParser(
						new CommonTokenStream(facilitatesLexer));
				facilitatesParser.labelParse();
				String facilitatesName = facilitatesParser.labelParseString;

				// Error Check -- No name specified
				if (facilitatesName == "ERROR") {
					FileError fe = new FileError(i,
							"No Facilitates name specified");
					errors.add(fe);
					break;
				}
				// End Error Check

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

				Node fromNode;
				Node toNode;

				fromNode = subTaskMap.get(f_from);
				toNode = subTaskMap.get(f_to);

				// Error Checks -- To/From doesn't exist.
				if (fromNode == null) {
					FileError fe = new FileError(i, "From Node Doesn't Exist");
					errors.add(fe);
					if (toNode == null) {
						FileError fe2 = new FileError(i, "To Node Doesn't Exist");
						errors.add(fe2);
						break;
					}
					break;
				}
				if (toNode == null) {
					FileError fe = new FileError(i, "To Node Doesn't Exist");
					errors.add(fe);
					break;
				}
				// End Error Checks

				ArrayList<Double> vals_f = new ArrayList<Double>();
				ArrayList<Double> probs_f = new ArrayList<Double>();

				for (int k = 0; k < qualityPowerArray.length; k += 2) {
					vals_f.add(qualityPowerArray[k]);
					probs_f.add(qualityPowerArray[k + 1]);
				}
				Distribution dQuality_f = new Distribution(vals_f, probs_f);
				double quality_f = dQuality_f.evaluate(randGen.nextDouble());

				vals_f = new ArrayList<Double>();
				probs_f = new ArrayList<Double>();

				for (int k = 0; k < durationPowerArray.length; k += 2) {
					vals_f.add(durationPowerArray[k]);
					probs_f.add(durationPowerArray[k + 1]);
				}
				Distribution dDuration_f = new Distribution(vals_f, probs_f);
				double duration_f = dDuration_f.evaluate(randGen.nextDouble());

				double newQ = quality_f;
				double newD = duration_f;

				FacilitatesNodeRelationship fnr = new FacilitatesNodeRelationship(
						fromNode, toNode, facilitatesName, newQ, newD);
				if (timeInt != 0) {
					fnr.setArrived(false);
					fnr.setArrivalTime(timeInt);
				}
				
				fnrs.add(fnr);

				break;

			case HindersToken:
				CtaemsLexer hindersLexer = new CtaemsLexer(
						new ANTLRInputStream(currToken.getText()));
				CtaemsParser hindersParser = new CtaemsParser(
						new CommonTokenStream(hindersLexer));
				hindersParser.labelParse();
				String hindersName = hindersParser.labelParseString;

				// Error Check -- No name specified
				if (hindersName == "ERROR") {
					FileError fe = new FileError(i, "No Hinders name specified");
					errors.add(fe);
					break;
				}
				// End Error Check

				hindersLexer.reset();
				hindersParser.reset();
				hindersParser.fromParse();
				String h_from = hindersParser.fromParseString;

				hindersLexer.reset();
				hindersParser.reset();
				hindersParser.toParse();
				String h_to = hindersParser.toParseString;

				hindersLexer.reset();
				hindersParser.reset();
				hindersParser.qualityPowerParse();
				double[] qualityPowerArray_h = hindersParser.qualityPowerArray;

				hindersLexer.reset();
				hindersParser.reset();
				hindersParser.durationPowerParse();
				double[] durationPowerArray_h = hindersParser.durationPowerArray;

				Node fromNode_h;
				Node toNode_h;

				fromNode_h = subTaskMap.get(h_from);
				toNode_h = subTaskMap.get(h_to);

				// Error Checks -- To/From doesn't exist.
				if (fromNode_h == null) {
					FileError fe = new FileError(i, "From Node Doesn't Exist");
					errors.add(fe);
					if (toNode_h == null) {
						FileError fe2 = new FileError(i, "To Node Doesn't Exist");
						errors.add(fe2);
						break;
					}
					break;
				}
				if (toNode_h == null) {
					FileError fe = new FileError(i, "To Node Doesn't Exist");
					errors.add(fe);
					break;
				}
				// End Error Checks

				ArrayList<Double> vals_h = new ArrayList<Double>();
				ArrayList<Double> probs_h = new ArrayList<Double>();

				for (int k = 0; k < qualityPowerArray_h.length; k += 2) {
					vals_h.add(qualityPowerArray_h[k]);
					probs_h.add(qualityPowerArray_h[k + 1]);
				}
				Distribution dQuality_h = new Distribution(vals_h, probs_h);
				double quality_h = dQuality_h.evaluate(randGen.nextDouble());

				vals_h = new ArrayList<Double>();
				probs_h = new ArrayList<Double>();

				for (int k = 0; k < durationPowerArray_h.length; k += 2) {
					vals_h.add(durationPowerArray_h[k]);
					probs_h.add(durationPowerArray_h[k + 1]);
				}
				Distribution dDuration_h = new Distribution(vals_h, probs_h);
				double duration_h = dDuration_h.evaluate(randGen.nextDouble());

				double newQ_h = quality_h;
				double newD_h = duration_h;

				HindersNodeRelationship hnr = new HindersNodeRelationship(
						fromNode_h, toNode_h, hindersName, newQ_h, newD_h);
				if (timeInt != 0) {
					hnr.setArrived(false);
					hnr.setArrivalTime(timeInt);
				}
				
				hnrs.add(hnr);

				break;

			case MethodToken:
				CtaemsLexer methodLexer = new CtaemsLexer(new ANTLRInputStream(
						currToken.getText()));
				CtaemsParser methodParser = new CtaemsParser(
						new CommonTokenStream(methodLexer));
				methodParser.labelParse();
				String methodName = methodParser.labelParseString;

				// Error Check -- No name specified
				if (methodName == "ERROR") {
					FileError fe = new FileError(i, "No Method name specified");
					errors.add(fe);
					break;
				}
				// End Error Check

				methodLexer.reset();
				methodParser.reset();
				methodParser.agentParse();
				String methodAgent = methodParser.agentParseString;

				// Error Check -- No Agent specified
				if (methodAgent == "ERROR") {
					FileError fe = new FileError(i, "No Agent specified");
					errors.add(fe);
					break;
				}
				// End Error Check

				methodLexer.reset();
				methodParser.reset();
				methodParser.densityParse();

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
				for (int k = 0; k < methodQualityDistribution.length; k += 2) {
					vals.add(methodQualityDistribution[k]);
					probs.add(methodQualityDistribution[k + 1]);
				}
				Distribution dQuality = new Distribution(vals, probs);
				double quality = dQuality.evaluate(randGen.nextDouble());

				vals = new ArrayList<Double>();
				probs = new ArrayList<Double>();
				for (int k = 0; k < methodDurationDistribution.length; k += 2) {
					vals.add(methodDurationDistribution[k]);
					probs.add(methodDurationDistribution[k + 1]);
				}
				Distribution dDuration = new Distribution(vals, probs);
				int duration = (int) dDuration.evaluate(randGen.nextDouble());

				Method method = new Method(null, methodName, methodAgent);
				method.setAgent(methodAgent);

				method.setQuality(quality);
				method.setDuration(duration);
				method.setQualityDistribution(dQuality);
				method.setDurationDistribution(dDuration);

				if (timeInt != 0) {
					method.setArrived(false);
					method.setArrivalTime(timeInt);
				}
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
				parseType = taskGroupParser.parseType;

				// Error Check -- No name specified
				if (taskGroupName == "ERROR") {
					FileError fe = new FileError(i, "No name specified");
					errors.add(fe);
					break;
				}
				// End Error Check

				taskGroupLexer.reset();
				taskGroupParser.reset();
				taskGroupParser.qafParse();
				String taskGroupQaf = taskGroupParser.qafParseString;

				head = new Task(null, taskGroupName);
				head.setQaf(taskGroupQaf);
				subTaskMap.put(taskGroupName, head);

				if (parseType == "notNew")
					taskGroupCount++;

				if (taskGroupCount > 1) {
					FileError fe = new FileError(i, "More than 1 task group");
					errors.add(fe);
				}
				break;

			case TaskToken:
				CtaemsLexer taskLexer = new CtaemsLexer(new ANTLRInputStream(
						currToken.getText()));
				CtaemsParser taskParser = new CtaemsParser(
						new CommonTokenStream(taskLexer));
				taskParser.labelParse();
				String taskName = taskParser.labelParseString;
				parseType = taskParser.parseType;

				// Error Check -- No name specified
				if (taskName == "ERROR") {
					FileError fe = new FileError(i, "No Task name specified");
					errors.add(fe);
					break;
				}
				// End Error Check

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
				taskParser.qafParse();

				Task task = new Task(null, taskName);
				subTaskMap.put(taskName, task);

				if (taskDeadline != "-1")
					task.setDeadline((int) Integer.parseInt(taskDeadline));

				if (taskEST != "-1")
					task.setEarliestStartTime((int) Integer.parseInt(taskEST));

				task.setQaf(taskParser.qafParseString);
				if (timeInt != 0) {
					task.setArrived(false);
					task.setArrivalTime(timeInt);
				}
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
				
			case TimeToken:
				CtaemsLexer timeLexer = new CtaemsLexer(
						new ANTLRInputStream(currToken.getText()));
				CtaemsParser timeParser = new CtaemsParser(
						new CommonTokenStream(timeLexer));
				
				timeParser.timeParse();
				timeInt = timeParser.timeParseInt;
				//System.out.println(timeInt);
				
				break;
			
			default :
				break;
			
			}
			i++;
		}

		CtaemsLexer lexer2 = new CtaemsLexer(new ANTLRInputStream(fileData));
		CtaemsParser parser2 = new CtaemsParser(new CommonTokenStream(lexer2));
		i = 0;
		timeInt = 0;
		while (parser2.token != "done" && taskGroupCount == 1) {
			parser2.parse();
			CommonToken currToken = (CommonToken) parser2.getTokenStream().get(
					i);
			switch (currToken.getType()) {
			
			case SpliceToken:
				CtaemsLexer spliceLexer = new CtaemsLexer(
						new ANTLRInputStream(currToken.getText()));
				CtaemsParser spliceParser = new CtaemsParser(
						new CommonTokenStream(spliceLexer));
				
				spliceParser.spliceParse();
				String parentString = spliceParser.spliceString;
				//System.out.println(parentString);
				
				spliceLexer.reset();
				spliceParser.reset();
				
				spliceParser.subtaskParse();
				String[] subtasks = spliceParser.subtaskArray; //this may need to move to the 2nd pass
				
				Task task = (Task) subTaskMap.get(parentString);

				// Error Check -- Subtask Doesn't Exist
				if (task == null) {
					FileError fe = new FileError(i, "Parent Doesn't Exist");
					errors.add(fe);
					break;
				}
				// End Error Check
				
//				for(int j = 0; j < subtasks.length; j++){
//					System.out.println(subtasks[j]);
//				}
				for (int j = 0; j < subtasks.length; j++) {
					Node newSubTask = subTaskMap.get(subtasks[j]);
					if (newSubTask == null) {
						FileError fe = new FileError(i, "Child Doesn't Exist");
						errors.add(fe);
						break;
					}
					newSubTask.setParent(task);
					task.addSubTask(newSubTask);
				}
				
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

				taskParser.subtaskParse();
				String[] taskSubtasks = taskParser.subtaskArray;

				task = (Task) subTaskMap.get(taskName);

				for (int j = 0; j < taskSubtasks.length; j++) {
					Node newSubTask = subTaskMap.get(taskSubtasks[j]);
					if (newSubTask == null) {
						FileError fe = new FileError(i, "Child Doesn't Exist");
						errors.add(fe);
						break;
					}
					newSubTask.setParent(task);
					task.addSubTask(newSubTask);
				}

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

				head = (Task) subTaskMap.get(taskGroupName);

				for (int j = 0; j < taskGroupSubtasks.length; j++) {
					Node newSubTask = subTaskMap.get(taskGroupSubtasks[j]);
					if (newSubTask == null) {
						FileError fe = new FileError(i, "Child Doesn't Exist");
						errors.add(fe);
						break;
					}
					newSubTask.setParent(head);
					head.addSubTask(newSubTask);
				}

				break;

			default:
				break;

			}
			i++;
		}

		if (errors.size() > 0) {
			InvalidFileException ife = new InvalidInputException(errors);
			throw ife;
		}
		// Don't build tree if there are errors
		if (errors.size() == 0) {
			TaskTree tree = buildTree(enrs, fnrs, hnrs, dnrs, methods, tasks,
					head);
			this.inputData = new InputData(tree, version, agents);
		}
	}

	/**
	 * Builds the task tree for the current simulation.
	 * 
	 * @param enrs
	 *            The list of entity-node relationships
	 * @param fnrs
	 *            The list of facilitates-node relationships.
	 * @param hnrs
	 *            The list of hinders-node relationships
	 * @param dnrs
	 *            The list of disables-node relationships.
	 * @param methods
	 *            The list of the methods in the simulation.
	 * @param tasks
	 *            The list of tasks in the simualtion.
	 * @param head
	 *            The head of the resulting TaskTree.
	 * @return The TaskTree that will be used for the simulation.
	 */
	public TaskTree buildTree(ArrayList<EnablesNodeRelationship> enrs,
			ArrayList<FacilitatesNodeRelationship> fnrs,
			ArrayList<HindersNodeRelationship> hnrs,
			ArrayList<DisablesNodeRelationship> dnrs,
			ArrayList<Method> methods, ArrayList<Task> tasks, Node head) {

		for (int k = 0; k < enrs.size(); k++) {
			EnablesNodeRelationship curr = enrs.get(k);
			Node toNode = curr.getToNode();
			Node fromNode = curr.getFromNode();
			toNode.addRelationship(curr);
			fromNode.addRelationship(curr);
		}

		for (int k = 0; k < fnrs.size(); k++) {
			FacilitatesNodeRelationship curr = fnrs.get(k);
			Node toNode = curr.getToNode();
			Node fromNode = curr.getFromNode();
			toNode.addRelationship(curr);
			fromNode.addRelationship(curr);
		}

		for (int k = 0; k < hnrs.size(); k++) {
			HindersNodeRelationship curr = hnrs.get(k);
			Node toNode = curr.getToNode();
			Node fromNode = curr.getFromNode();
			toNode.addRelationship(curr);
			fromNode.addRelationship(curr);
		}

		for (int k = 0; k < dnrs.size(); k++) {
			DisablesNodeRelationship curr = dnrs.get(k);
			Node toNode = curr.getToNode();
			Node fromNode = curr.getFromNode();
			toNode.addRelationship(curr);
			fromNode.addRelationship(curr);
		}

		TaskTree tree = new TaskTree((Task) head);
		return tree;
	}

	public InputData getInputData() {

		return inputData;
	}

	public String getFileData() {
		return fileData;
	}
}
