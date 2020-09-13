package com.CreditCardProblem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardProcessorTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Determines whether the mock data is a credit card of Visa type
	 */
	@Test
	void visaCardTest() {
		VisaCardProcessor visaTest = new VisaCardProcessor();
		CreditCard testCard = new CreditCard();
		testCard.setCardNumber(4773694814422868L);
		testCard.setExpirationDate(new Date(06 / 23 / 2025));
		testCard.setName("Jacob");
		String expectedCardType = "Visa";
		CreditCard actual = visaTest.determineCardType(testCard);
		assertEquals(expectedCardType, actual.getCardType());
		assertNotEquals(testCard.getCardType(), "Invalid");

	}
	
	/**
	 * Determines whether the mock data is a credit card of MasterCard type
	 */
	@Test
	void MasterCardTest() {
		MasterCardProcessor masterTest = new MasterCardProcessor();
		CreditCard testCard = new CreditCard();
		testCard.setCardNumber(5105110000000000L);

		testCard.setExpirationDate(new Date(06 / 24 / 2025));
		testCard.setName("John");
		testCard.setCardNumber(5105110000000000L);
		String expectedCardType = "MasterCard";
		CreditCard actual = masterTest.determineCardType(testCard);
		assertEquals(expectedCardType, actual.getCardType());
		assertNotEquals(testCard.getCardType(), "Invalid");

	}

	/**
	 * Determines whether the mock data is a credit card of AmericanExpress type
	 */
	@Test
	void AmericanExpressCardTest() {
		AmericanExpressCardProcessor amExTest = new AmericanExpressCardProcessor();
		CreditCard testCard = new CreditCard();
		testCard.setCardNumber(371449635398431L);

		testCard.setExpirationDate(new Date(06 / 26 / 2022));
		testCard.setName("Alice");
		testCard.setCardNumber(371449635398431L);
		String expectedCardType = "AmericanExpress";
		CreditCard actual = amExTest.determineCardType(testCard);
		assertEquals(expectedCardType, actual.getCardType());
		assertNotEquals(testCard.getCardType(), "Invalid");

	}

	/**
	 * Determines whether the mock data is a credit card of Discover type
	 */
	@Test
	void DiscoverCardTest() {
		DiscoverCardProcessor discoverTest = new DiscoverCardProcessor();
		CreditCard testCard = new CreditCard();
		testCard.setCardNumber(6011000990139420L);

		testCard.setExpirationDate(new Date(06 / 26 / 2020));
		testCard.setName("Esha");
		testCard.setCardNumber(6011000990139420L);
		String expectedCardType = "Discover";
		CreditCard actual = discoverTest.determineCardType(testCard);
		assertEquals(expectedCardType, actual.getCardType());
		assertNotEquals(testCard.getCardType(), "Invalid");

	}

	/**
	 * Determines whether the mock data is a credit card is invalid
	 */
	@Test
	void InvalidCardTest() {
		InvalidCardProcessor invalidCardsTest = new InvalidCardProcessor();
		CreditCard testCard = new CreditCard();
		testCard.setCardNumber(6010000000000000L);

		testCard.setExpirationDate(new Date(9 / 26 / 2020));
		testCard.setName("Isabella");
		testCard.setCardNumber(6010000000000000L);
		String expectedCardType = "Invalid";
		CreditCard actual = invalidCardsTest.determineCardType(testCard);
		assertEquals(expectedCardType, actual.getCardType());

	}

}
