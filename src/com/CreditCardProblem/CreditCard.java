package com.CreditCardProblem;

//import java.math.BigInteger;
import java.util.Date;

public class CreditCard {

	private long cardNumber;
	private Date expirationDate;
	private String nameOfCardHolder;

	public String cardType;
	public String Error;

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}

	public CreditCard(long cardNumber, Date expirationDate, String nameOfCardHolder) {
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.nameOfCardHolder = nameOfCardHolder;
	}

	public CreditCard() {

	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getName() {
		return nameOfCardHolder;
	}

	public void setName(String nameOfCardHolder) {
		this.nameOfCardHolder = nameOfCardHolder;
	}

	@Override
	public String toString() {
		return "CreditCard [cardNumber=" + cardNumber + ", expirationDate=" + expirationDate + ", nameOfCardHolder="
				+ nameOfCardHolder + "]";
	}

}
