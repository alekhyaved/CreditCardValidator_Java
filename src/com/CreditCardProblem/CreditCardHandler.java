package com.CreditCardProblem;

public interface CreditCardHandler {

	public void setNextChain(CreditCardHandler nextChain);

	public CreditCard determineCardType(CreditCard card);
}
