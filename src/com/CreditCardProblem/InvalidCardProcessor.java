package com.CreditCardProblem;

public class InvalidCardProcessor implements CreditCardHandler {

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

		long cardNum = card.getCardNumber();

		if (!isValidCardNum(cardNum)) {
			card.setCardType("Invalid");
			card.setError("InvalidCardNumber");
			return card;
		} else {

			return nextChain.determineCardType(card);

		}

	}

	/**
	 * Helper function to determine invalid number
	 * @param cardNum
	 * @return
	 */
	public boolean isValidCardNum(long cardNum) {

		return (getSize(cardNum) >= 13 && getSize(cardNum) <= 19)
				&& (prefixMatched(cardNum, 4) || prefixMatched(cardNum, 5) || prefixMatched(cardNum, 37)
						|| prefixMatched(cardNum, 6))
				&& ((sumOfDoubleEvenPlace(cardNum) + sumOfOddPlace(cardNum)) % 10 == 0);
	}

	/**
	 * Helper function to sum the alternate digits in the card number
	 * @param cardNum
	 * @return
	 */
	public static int sumOfDoubleEvenPlace(long cardNum) {
		int sum = 0;
		String num = cardNum + "";
		for (int i = getSize(cardNum) - 2; i >= 0; i -= 2)
			sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

		return sum;
	}

	/**
	 * Returns this number if it is a single digit, otherwise, return the sum of the two digits
	 * @param cardNum
	 * @return
	 */
	
	public static int getDigit(int cardNum) {
		if (cardNum < 9)
			return cardNum;
		return cardNum / 10 + cardNum % 10;
	}

	/**
	 * Helper function to add up the odd places in the card number
	 * @param cardNum
	 * @return
	 */
	public static int sumOfOddPlace(long cardNum) {
		int sum = 0;
		String num = cardNum + "";
		for (int i = getSize(cardNum) - 1; i >= 0; i -= 2)
			sum += Integer.parseInt(num.charAt(i) + "");
		return sum;
	}

	/**
	 * Helper function to match the prefix on the card with the integer param
	 * @param cardNum
	 * @param d
	 * @return
	 */
	public static boolean prefixMatched(long cardNum, int d) {
		return getPrefix(cardNum, getSize(d)) == d;
	}

	/**
	 * given a number get the number of digits in the number
	 * @param d
	 * @return
	 */
	public static int getSize(long d) {
		String num = d + "";
		return num.length();
	}

	/**
	 * Find the prefix of an integer k in a given number
	 * @param cardNum
	 * @param k
	 * @return
	 */
	public static long getPrefix(long cardNum, int k) {
		if (getSize(cardNum) > k) {
			String num = cardNum + "";
			return Long.parseLong(num.substring(0, k));
		}
		return cardNum;

	}

}
