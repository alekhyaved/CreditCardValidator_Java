package com.CreditCardProblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileIO {

	public List<CreditCard> read(File file) throws FileNotFoundException;

	public void write(List<CreditCard> creditCardNumbers, File file);

}
