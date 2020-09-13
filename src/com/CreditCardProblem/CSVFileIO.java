package com.CreditCardProblem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
//import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
//import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVFileIO implements FileIO {

	/**
	 * implements strategy pattern. Takes a CSV File, executes reading strategy and returns a list of cards
	 * @param CSVfile
	 * @return List<CreditCard>
	 */
	@Override
	public List<CreditCard> read(File file) throws FileNotFoundException {

		Scanner scanner = new Scanner(file);
		Scanner valueScanner = new Scanner(scanner.nextLine());
		List<CreditCard> creditCardList = new ArrayList<>();

		while (scanner.hasNextLine()) {
			valueScanner = new Scanner(scanner.nextLine());
			String[] tokens = valueScanner.nextLine().split(",");
			CreditCard card = new CreditCard();
			String cardNumStr = tokens[0];
			String expiryDateStr = tokens[1];
			String nameHolderStr = tokens.length == 3 ? tokens[2] : "";

			try {
				Long cardNumber;
				Date expirationDate;
				String nameCardHolder;
				if (cardNumStr == null || cardNumStr.length() == 0) {
					cardNumber = null;
				} else {
					cardNumber = Double.valueOf(cardNumStr).longValue();
				}

				card.setCardNumber(cardNumber);

				if (expiryDateStr == null || expiryDateStr.length() == 0 || !isValid(expiryDateStr)) {
					expirationDate = null;
					System.out.println("Date field is empty, please check ");
				} else {
					expirationDate = new SimpleDateFormat("MM/dd/yyyy").parse(expiryDateStr);
				}
				card.setExpirationDate(expirationDate);
				if (nameHolderStr.isEmpty()) {
					nameCardHolder = "";
					System.out.println("Name field is empty, please check ");
				} else {
					nameCardHolder = nameHolderStr;
				}
				card.setName(nameCardHolder);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.toString();
			}
			creditCardList.add(card);
		}
		valueScanner.close();
		scanner.close();
		return creditCardList;

	}

	/**
	 * Given a dateString checks whether the string is a valid date or not
	 * @param expiryDateStr
	 * @return
	 */
	public boolean isValid(String expiryDateStr) {
		DateFormat expiryDate = new SimpleDateFormat("MM/dd/yyyy");
		expiryDate.setLenient(false);
		try {
			expiryDate.parse(expiryDateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * Takes a stream of data and converts it to comma separated string
	 * @param data
	 * @return
	 */
	public String convertToCSV(String[] data) {
		return Stream.of(data).map(this::escapeSpecialCharacters).collect(Collectors.joining(","));
	}

	/**
	 * Helper function to escape special characters in a string of data
	 * @param data
	 * @return
	 */
	public String escapeSpecialCharacters(String data) {
		String escapedData = data.replaceAll("\\R", " ");
		if (data.contains(",") || data.contains("\"") || data.contains("'")) {
			data = data.replace("\"", "\"\"");
			escapedData = "\"" + data + "\"";
		}
		return escapedData;
	}

	/**
	 * Implements strategy pattern. Takes a list of credit card objects and a CSV File to write the credit card information and type of card.
	 * @param cards
	 * @param CSV File
	 * @throws FileNotFoundException
	 */
	@Override
	public void write(List<CreditCard> cards, File file) {
		List<String[]> dataLines = new ArrayList<>();
		dataLines.add(new String[] { "CardNumber", "CardType", "Error" });
		for (CreditCard card : cards) {
			dataLines.add(new String[] { Long.toString(card.getCardNumber()), card.getCardType(), card.getError() });
		}
		try (PrintWriter pw = new PrintWriter(file)) {
			dataLines.stream().map(this::convertToCSV).forEach(pw::println);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
