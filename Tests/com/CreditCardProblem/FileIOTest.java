package com.CreditCardProblem;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileIOTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Tests whether the read() functionality of CSVFileIO works or not
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	@Test
	void testcsv() throws FileNotFoundException, ParseException {
		CSVFileIO csvFileParser = new CSVFileIO();
		File file = new File("Tests/com/CreditCardProblem/sampleData.csv");
		List<CreditCard> expectedList = new ArrayList<>();
		CreditCard c1 = new CreditCard(Double.valueOf(5.10511E+15).longValue(),
				new SimpleDateFormat("MM/dd/yyyy").parse("6/24/30"), "carrot");
		CreditCard c2 = new CreditCard(Double.valueOf(6011000990139424L).longValue(),
				new SimpleDateFormat("MM/dd/yyyy").parse("6/25/30"), "Almond");
		CreditCard c3 = new CreditCard(Double.valueOf(371449635398431L).longValue(),
				new SimpleDateFormat("MM/dd/yyyy").parse("6/26/30"), "pecan");
		CreditCard c4 = new CreditCard(Double.valueOf(6011004725136296349L).longValue(),
				new SimpleDateFormat("MM/dd/yyyy").parse("6/27/30"), "cashew");
		expectedList.add(c1);
		expectedList.add(c2);
		expectedList.add(c3);
		expectedList.add(c4);
		List<CreditCard> actualList = csvFileParser.read(file);
		assertEquals(expectedList.size(), actualList.size());

		for (int i = 0; i < expectedList.size(); i++) {
			assertEquals(expectedList.get(i).getCardNumber(), actualList.get(i).getCardNumber());
			assertTrue(expectedList.get(i).getExpirationDate().equals(actualList.get(i).getExpirationDate()));
			assertEquals(expectedList.get(i).getName(), actualList.get(i).getName());
		}

	}

	/**
	 * Tests whether the read() functionality of XMLFileIO works or not
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	@Test
	void testxml() throws FileNotFoundException, ParseException {
		XMLFileIO xmlFileParser = new XMLFileIO();
		File file = new File("Tests/com/CreditCardProblem/sampleData.xml");
		List<CreditCard> expectedList = new ArrayList<>();
		CreditCard c1 = new CreditCard(5410000000000000L, new SimpleDateFormat("MM/dd/yyyy").parse("3/20/2030"),
				"Alice");
		CreditCard c2 = new CreditCard(4120000000000L, new SimpleDateFormat("MM/dd/yyyy").parse("4/20/2030"), "Bob");
		CreditCard c3 = new CreditCard(341000000000000L, new SimpleDateFormat("MM/dd/yyyy").parse("5/20/2030"), "Eve");
		CreditCard c4 = new CreditCard(6010000000000000L, new SimpleDateFormat("MM/dd/yyyy").parse("6/20/2030"),
				"Richard");
		expectedList.add(c1);
		expectedList.add(c2);
		expectedList.add(c3);
		expectedList.add(c4);
		List<CreditCard> actualList = xmlFileParser.read(file);
		for (int i = 0; i < expectedList.size(); i++) {
			assertEquals(expectedList.get(i).getCardNumber(), actualList.get(i).getCardNumber());
			assertTrue(expectedList.get(i).getExpirationDate().equals(actualList.get(i).getExpirationDate()));
			assertEquals(expectedList.get(i).getName(), actualList.get(i).getName());

		}
	}

	/**
	 * Tests whether the read() functionality of JSONFileIO works or not
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	@Test
	void testjson() throws FileNotFoundException, ParseException {
		JSONFileIO jsonFileParser = new JSONFileIO();
		File file = new File("Tests/com/CreditCardProblem/sampleData.json");

		List<CreditCard> expectedList = new ArrayList<>();

		CreditCard c1 = new CreditCard(5410000000000000L, new SimpleDateFormat("MM/dd/yyyy").parse("3/20/2020"),
				"Alice");
		CreditCard c2 = new CreditCard(4120000000000L, new SimpleDateFormat("MM/dd/yyyy").parse("4/20/2020"), "Bob");
		CreditCard c3 = new CreditCard(5545255334360667L, new SimpleDateFormat("MM/dd/yyyy").parse("6/26/2020"),
				"Almond");

		expectedList.add(c1);
		expectedList.add(c2);
		expectedList.add(c3);
		List<CreditCard> actualList = jsonFileParser.read(file);
		for (int i = 0; i < expectedList.size(); i++) {
			assertEquals(expectedList.get(i).getCardNumber(), actualList.get(i).getCardNumber());
			assertTrue(expectedList.get(i).getExpirationDate().equals(actualList.get(i).getExpirationDate()));
			assertEquals(expectedList.get(i).getName(), actualList.get(i).getName());

		}

	}

}
