package edu.udel.cisc475.aisim.input;

public class FileError {
	private int lineNumber;
	private String error;
	private String correct;
	
	public FileError(int lineNumber, String error, String correct) {
		this.lineNumber = lineNumber;
		this.error = error;
		this.correct = correct;
	}

	public String toString(){
		String str = "Line number: " + lineNumber
				+ ", error: " + error 
				+ ", correct: " + correct;
			
		return str;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}
}
