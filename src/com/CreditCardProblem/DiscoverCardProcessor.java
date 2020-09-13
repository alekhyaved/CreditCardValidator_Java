package com.CreditCardProblem;

public class DiscoverCardProcessor implements CreditCardHandler {

	/**
	 * sets the nextChain object to determine whether the Chain of Responsibility should head next if this isn't the right usecase
	 */
	@Override
	public void setNextChain(CreditCardHandler nextChain) {

	}

	/**
	 * Given a CreditCard object determines the cardType value and sets it on the object
	 */
	@Override
	public CreditCard determineCardType(CreditCard card) {

		String str = Long.toString(card.getCardNumber());

		String sub = str.substring(0, 4);

		if (sub.equals("6011") && str.length() == 16) {
			card.setCardType("Discover");
			card.setError("None");
			return card;
		} else {
			card.setCardType("Invalid");
			card.setError("InvalidCardNumber");
			return card;
		}

	}

}
