package edu.udel.cisc475.aisim.input;

import java.util.ArrayList;

/**
 * This abstract class represents an exception that is thrown when the target
 * file is invalid.
 */
public abstract class InvalidFileException extends Exception {
	/**
	 * UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The list of errors that were found in the file.
	 */
	private ArrayList<FileError> errors;

	/**
	 * The default constructor for an InvalidFileException.
	 * 
	 * @param errors
	 *            The list of errors that were found in the file.
	 */
	public InvalidFileException(ArrayList<FileError> errors) {
		super();
		this.errors = errors;
	}

	public ArrayList<FileError> getErrors() {
		return errors;
	}
}
