package com.CreditCardProblem;

public class AmericanExpressCardProcessor implements CreditCardHandler {

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

		if (arr[0].equals("3") && Integer.parseInt(arr[1]) == 4 || Integer.parseInt(arr[1]) == 7 && arr.length == 15) {
			card.setCardType("AmericanExpress");
			card.setError("None");
			return card;
		} else {
			return nextChain.determineCardType(card);
		}

	}

}
