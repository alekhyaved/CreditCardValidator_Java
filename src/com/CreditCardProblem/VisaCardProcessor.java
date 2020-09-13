package com.CreditCardProblem;

public class VisaCardProcessor implements CreditCardHandler {

	public CreditCardHandler nextChain;

	/**
	 * sets the nextChain object to determine whether the Chain of Responsibility should head next if this isn't the right usecase
	 */
	@Override
	public void setNextChain(CreditCardHandler nextChain) {
		this.nextChain = nextChain;

	}

	/**
	 * Given a CreditCard object determines the cardType value and sets it on the object
	 */
	@Override
	public CreditCard determineCardType(CreditCard card) {
		String str = Long.toString(card.getCardNumber());

		String[] arr = str.split("");

		if (arr[0].equals("4") && (arr.length == 13 || arr.length == 16)) {
			card.setCardType("Visa");
			card.setError("None");
			return card;
		} else {
			return nextChain.determineCardType(card);
		}

	}

}
