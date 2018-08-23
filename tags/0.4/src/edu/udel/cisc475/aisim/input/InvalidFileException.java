package edu.udel.cisc475.aisim.input;

import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class InvalidFileException extends Exception{
	private ArrayList<FileError> errors;
		
	public InvalidFileException(ArrayList<FileError> errors) {
		super();
		this.errors = errors;
	}

	public ArrayList<FileError> getErrors() {
		return errors;
	}
}
