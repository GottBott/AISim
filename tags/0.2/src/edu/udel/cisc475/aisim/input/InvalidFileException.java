package edu.udel.cisc475.aisim.input;

import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class InvalidFileException extends Exception{
	private ArrayList<FileError> errors;
		
	public InvalidFileException(ArrayList<FileError> errors) {
		super();
		this.setErrors(errors);
	}

	public void displayErrors(){
		
	}

	public ArrayList<FileError> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<FileError> errors) {
		this.errors = errors;
	}
}
