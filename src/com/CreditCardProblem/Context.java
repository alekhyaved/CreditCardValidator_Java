package com.CreditCardProblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Context {

	private FileIO fileOperations;

	public Context(FileIO fileOperations) {
		this.fileOperations = fileOperations;
	}

	/**
	 * implements strategy pattern. Takes a File, executes reading strategy and returns a list of cards
	 * @param file
	 * @return List<CreditCard>
	 */
	public List<CreditCard> executeReadStrategy(File file) {
		// fileOperations.read() i.e., CSVFileParser.read() which returns CreditCard
		// objects List
		// return CreditCard objects to main inside TaskRunner
		try {
			return fileOperations.read(file);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	
	/**
	 * Implements strategy pattern. Takes a list of credit card objects and a file to write the credit card information and type of card.
	 * @param cards
	 * @param file
	 * @throws FileNotFoundException
	 */
	public void executeWrite(List<CreditCard> cards, File file) throws FileNotFoundException {
		fileOperations.write(cards, file);
	}

}
