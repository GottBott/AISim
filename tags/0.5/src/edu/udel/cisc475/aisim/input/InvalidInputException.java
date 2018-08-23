package edu.udel.cisc475.aisim.input;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class InvalidInputException extends InvalidFileException{

	public InvalidInputException(ArrayList<FileError> errors) {
		super(errors);
	}

}
