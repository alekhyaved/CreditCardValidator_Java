package com.CreditCardProblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

//import javax.json.stream.JsonParsingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONFileIO implements FileIO {


	/**
	 * implements strategy pattern. Takes a JSON File, executes reading strategy and returns a list of cards
	 * @param JSON File
	 * @return List<CreditCard>
	 */
	@Override
	public List<CreditCard> read(File file) {
		JSONParser jsonParser = new JSONParser();
		List<CreditCard> creditCardsList = new ArrayList<CreditCard>();
		try {
			FileReader reader = new FileReader(file);
			Object allCards = jsonParser.parse(reader);
			JSONArray allCardsArray = (JSONArray) allCards;
			for (int i = 0; i < allCardsArray.size(); i++) {
				CreditCard cj = new CreditCard();
				JSONObject cardObject = (JSONObject) allCardsArray.get(i);
				Long cardNumber = (Long) cardObject.get("CardNumber");
				String dateExpiry = (String) cardObject.get("ExpirationDate").toString();
				String nameOfCardHolder = (String) cardObject.get("NameOfCardholder");
				if (cardNumber == null || cardNumber.toString().isEmpty()) {
					cj.setCardNumber(0);
					System.out.println("Empty or null value of card number is present. check output file");
				} else {
					cj.setCardNumber(cardNumber);
				}
				if (dateExpiry != null && isValid(dateExpiry)) {

					cj.setExpirationDate(new SimpleDateFormat("MM/dd/yyyy").parse(dateExpiry));

				} else {
					cj.setExpirationDate(null);
					System.out.println("Incorrect value of date field is present.. check output file");
				}
				if (nameOfCardHolder == "" || nameOfCardHolder == null) {
					cj.setName("");
				} else {
					cj.setName(nameOfCardHolder);
				}

				creditCardsList.add(cj);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("There is an incorrect value in the file.");
		}
		return creditCardsList;

	}

	/**
	 * Given a string with date, returns whether the date is valid or not
	 * @param dateExpiry
	 * @return
	 */
	public boolean isValid(String dateExpiry) {
		DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
		date.setLenient(false);
		try {
			date.parse(dateExpiry);
			return true;
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			System.out.println("Incorrect date");
		}
		return false;
	}

	/**
	 * Implements strategy pattern. Takes a list of credit card objects and a JSON file to write the credit card information and type of card.
	 * @param cards
	 * @param JSON File
	 * @throws FileNotFoundException
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void write(List<CreditCard> cards, File file) {

		JSONArray cardsJSONArray = new JSONArray();

		for (CreditCard card : cards) {
			JSONObject cardJSON = new JSONObject();
			cardJSON.put("CardNumber", Long.toString(card.getCardNumber()));
			cardJSON.put("CardType", card.getCardType());
			cardJSON.put("Error", card.getError());
			cardsJSONArray.add(cardJSON);

		}
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(cardsJSONArray.toJSONString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
