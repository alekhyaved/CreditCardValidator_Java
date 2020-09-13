package com.CreditCardProblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class TaskRunner {

	/**
	 * Main() function takes two arguments - input file path and output file path.
	 * The input file contains a list of credit cards. The output file needs to be written with the credit card information along with card type and error
	 * Inherently calls classes that implement Strategy pattern and Chain of Responsibility
	 * @param args
	 */
	public static void main(String[] args) {

		String inputFilePath = args[0];
		String outputFilePath = args[1];

		File inputFile = new File(inputFilePath);
		System.out.println("Input file path is" + inputFilePath);
		File outputFile = new File(outputFilePath);
		System.out.println("Output file path is" + outputFilePath);

		Context context = null;
		String fileExtension = getFileExtension(inputFile);
		if (getFileExtension(inputFile).equals(getFileExtension(outputFile))) {

			if (fileExtension.equals("csv")) {
				context = new Context(new CSVFileIO());

			} else if (fileExtension.equals("xml")) {
				context = new Context(new XMLFileIO());

			} else if (fileExtension.equals("json")) {
				context = new Context(new JSONFileIO());
			}
		} else {
			System.out.println("File formats are not valid, please enter requested file formats that match");
		}
		if (context != null) {

			List<CreditCard> cards = context.executeReadStrategy(inputFile);

			CreditCardHandler invalidCardhandler = new InvalidCardProcessor();
			CreditCardHandler visaCardhandler = new VisaCardProcessor();
			CreditCardHandler masterCardhandler = new MasterCardProcessor();
			CreditCardHandler amExhandler = new AmericanExpressCardProcessor();
			CreditCardHandler discoverhandler = new DiscoverCardProcessor();

			invalidCardhandler.setNextChain(visaCardhandler);
			visaCardhandler.setNextChain(masterCardhandler);
			masterCardhandler.setNextChain(amExhandler);
			amExhandler.setNextChain(discoverhandler);

			for (CreditCard card : cards) {
				card = invalidCardhandler.determineCardType(card);
			}

			try {
				context.executeWrite(cards, outputFile);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}

	}

	/**
	 * Given a file, a helper function to return the extension of the file
	 * @param file
	 * @return
	 */
	public static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

}
