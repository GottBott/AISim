package edu.udel.cisc475.aisim.input;

/**
 * This class represents an error that is present in the cTAEMS or Configuration file.
 */
public class FileError {
	/**
	 * The line number the error occurred on.
	 */
	private int lineNumber;
	
	/**
	 * The string representation of the error.
	 */
	private String error;
	
	public FileError(int lineNumber, String error) {
		this.lineNumber = lineNumber;
		this.error = error;
	}

	public String toString(){
		String str = "Line number: " + lineNumber
				+ ", error: " + error;			
		return str;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getError() {
		return error;
	}
}
