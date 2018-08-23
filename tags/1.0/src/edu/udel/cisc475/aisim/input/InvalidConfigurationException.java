package edu.udel.cisc475.aisim.input;

import java.util.ArrayList;

/**
 * This class represents an exception that is thrown when the config file
 * contains errors.
 * 
 * @author Mike
 *
 */
public class InvalidConfigurationException extends InvalidFileException {

	/**
	 * UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The default constructor for an InvalidConfigurationException.
	 * 
	 * @param errors
	 *            The list of errors that were found in the file.
	 */
	public InvalidConfigurationException(ArrayList<FileError> errors) {
		super(errors);
	}

}
