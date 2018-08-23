package edu.udel.cisc475.aisim.input;

import java.util.ArrayList;

@SuppressWarnings("serial")
/**
 * This abstract class represents an exception that is thrown when the target file is invalid.
 */
public abstract class InvalidFileException extends Exception {
	/**
	 * A list of InvalidFileExceptions.
	 */
	private ArrayList<FileError> errors;

	public InvalidFileException(ArrayList<FileError> errors) {
		super();
		this.errors = errors;
	}

	public ArrayList<FileError> getErrors() {
		return errors;
	}
}
