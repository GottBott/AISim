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
import edu.udel.cisc475.aisim.tasktree.EnablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.FacilitatesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.Node;
import edu.udel.cisc475.aisim.tasktree.Task;

/**
 * This class is responsible for reading in the cTAEMS input file and passing
 * the data to the simulation.
 * 
 */
public class InputParser extends SimulationParser {
	private InputData inputData;
	
	enum QAF{
		AND,
		OR,
		SUM}
	
	public static final int AgentToken = 1, VersionToken = 2,
			TaskGroupToken = 3, TaskToken = 4, MethodToken = 5,
			EnablesToken = 6, FacilitatesToken = 7, Spaces = 8;

	public InputParser(String fileLocation) {
		super(fileLocation);
		inputData = null;
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

		try {
			currDirect = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File f = new File(currDirect + this.getFileLocation());
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
			this.setFileData(dataOut);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Parses the data currently in fileData
	 */
	// TODO: Implement Antlr
	// http://stackoverflow.com/questions/1931307/antlr-is-there-a-simple-example
	@Override
	public void parse(){
		CtaemsLexer lexer = new CtaemsLexer(new ANTLRInputStream(this.getFileData()));
		CtaemsParser parser = new CtaemsParser(new CommonTokenStream(lexer));
		distributeTokens(parser,lexer);
	}
	
	public void distributeTokens(CtaemsParser parser,CtaemsLexer lexer) {
		Set<String> agents = new HashSet<String>();
		Map<String,Node> nodes = new HashMap<String,Node>();
		ArrayList<EnablesNodeRelationship> enrs = new ArrayList<EnablesNodeRelationship>();
		ArrayList<FacilitatesNodeRelationship> fnrs = new ArrayList<FacilitatesNodeRelationship>();
		ArrayList<Method> methods = new ArrayList<Method>();
		// ArrayList<TaskGroup> tgts = new ArrayList<TaskGroup>();
		// TODO: Find where this data goes
		ArrayList<Task> tasks = new ArrayList<Task>();
		String version;
		int i = 0;
		
		while (parser.token != "done") {
			parser.parse();
			CommonToken currToken = (CommonToken) parser.getTokenStream()
					.get(i);
			switch (currToken.getType()) {
			case AgentToken:
				CtaemsLexer agentLexer = new CtaemsLexer(new ANTLRInputStream(currToken.getText()));
				CtaemsParser agentParser = new CtaemsParser(new CommonTokenStream(agentLexer));
				agentParser.labelParse();
				String agentName = agentParser.labelParseString;
				//TODO: Shouldn't be making agents here. Just get their names
				agents.add(agentName);
				break;
			case EnablesToken:
				CtaemsLexer enablesLexer = new CtaemsLexer(new ANTLRInputStream(currToken.getText()));
				CtaemsParser enablesParser = new CtaemsParser(new CommonTokenStream(enablesLexer));
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
				
				EnablesNodeRelationship enr = new EnablesNodeRelationship(null,
						null, enableName);
				enrs.add(enr);
				break;
			case FacilitatesToken:
				CtaemsLexer facilitatesLexer = new CtaemsLexer(new ANTLRInputStream(currToken.getText()));
				CtaemsParser facilitatesParser = new CtaemsParser(new CommonTokenStream(facilitatesLexer));
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
				
				FacilitatesNodeRelationship fnr = new FacilitatesNodeRelationship(
						null, null, facilitatesName, 0, 0);
				fnrs.add(fnr);
				break;
			case MethodToken:
				CtaemsLexer methodLexer = new CtaemsLexer(new ANTLRInputStream(currToken.getText()));
				CtaemsParser methodParser = new CtaemsParser(new CommonTokenStream(methodLexer));				
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
				
				Method method = new Method(null, methodName, methodAgent);
				methods.add(method);
				break;
			case TaskGroupToken:
				System.out.println("What do we do with this data?");
				break;
			case TaskToken:
				CtaemsLexer taskLexer = new CtaemsLexer(new ANTLRInputStream(currToken.getText()));
				CtaemsParser taskParser = new CtaemsParser(new CommonTokenStream(taskLexer));
				taskParser.labelParse();
				String taskName = taskParser.labelParseString;
				
				taskLexer.reset();
				taskParser.reset();
				taskParser.earliestStartTimeParse();
				String taskEST = "-1";
				if(taskParser.hasEST)
					taskEST = taskParser.earliestStartTimeParseString;
				
				taskLexer.reset();
				taskParser.reset();
				taskParser.deadlineParse();
				String taskDeadline = "-1";
				if(taskParser.hasDeadline)
					taskDeadline = taskParser.deadlineParseString;
				
				taskLexer.reset();
				taskParser.reset();
				taskParser.subtaskParse();
				String[] taskSubtasks = taskParser.subtaskArray;
				
				taskLexer.reset();
				taskParser.reset();
				taskParser.qafParse();
					
				Task task = new Task(null, null, 0, false, false, null, 0, (int) Integer.parseInt(taskDeadline),
						null);
				task.setQaf(taskParser.qafParseString);
				tasks.add(task);
				break;
			case VersionToken:
				CtaemsLexer versionLexer = new CtaemsLexer(new ANTLRInputStream(currToken.getText()));
				CtaemsParser versionParser = new CtaemsParser(new CommonTokenStream(versionLexer));
				versionParser.versionParse();
				version = versionParser.versionParseString;
				break;
			}

			i++;
		}
	}

	public InputData getInputData() {
		return inputData;
	}

	public void setInputData(InputData inputData) {
		this.inputData = inputData;
	}

	public static void main(String[] args) {
		SimulationParser ip = new InputParser("/trunk/doc/simpleCtaems.txt");
		ip.readFile();
		ip.parse();
	}

}
