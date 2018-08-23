package edu.udel.cisc475.aisim.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import edu.udel.cisc475.aisim.tasktree.Method;

//Ben Gotthold

public abstract class LogWriter {

	private String formattedFileDestination;
	private String fileDestination;
	private String fileName;
	private File dir;
	protected File logFile;

	/**
	 * constructor for a logger
	 * 
	 * @param fileDestination
	 * @param fileName
	 */
	public LogWriter(String fileDestination, String fileName) {

		this.fileDestination = fileDestination;
		this.fileName = fileName;
		setUp();

	}

	/**
	 * sets up the logger and creates a file at given destination
	 * 
	 * @return
	 */
	private Boolean setUp() {
		if (fileDestination == "") {
			URL location = LogWriter.class.getProtectionDomain()
					.getCodeSource().getLocation();
			fileDestination = location.getFile();
		}
		formattedFileDestination = formattFileDestination(fileDestination);
		dir = new File(formattedFileDestination);
		dir.mkdir();
		logFile = new File(dir, fileName);

		return true;
	}

	/**
	 * helper function to format the output location this makes the style of the
	 * input location flexible
	 * 
	 * @param fileDest
	 * @return
	 */
	private String formattFileDestination(String fileDest) {
		int i = 0;
		String formattedFD = "";
		while (i < fileDest.length()) {
			if (fileDest.charAt(i) == '/' && i != fileDest.length() - 1) {
				formattedFD += "/";
				i++;
				if (fileDest.charAt(i) != '/') {
					formattedFD += "/";
				}
			}
			formattedFD += fileDest.charAt(i);
			i++;
		}
		return formattedFD;
	}

	protected String createTab(int numOfTabs) {
		String tabs = "";
		for (int i = 0; i < numOfTabs; i++) {
			tabs += "    ";
		}
		return tabs;
	}

	public Boolean LogMethodEvent(Method m) {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println(createTab(1) + m.getName());
			out.println(createTab(2) + "  " + "Completed By : " + m.getAgent());
			out.println(createTab(2) + "  " + "With Quality : "
					+ m.getQuality());
			out.println(createTab(2) + "  " + "With Duration: "
					+ m.getDuration());
			out.println("");
			out.close();

			return true;

		} catch (IOException e) {
			// do some exception handling
			e.printStackTrace();
			return false;
		}
	}

	public String getPathToFile() {
		return formattedFileDestination.toString();
	}

}
