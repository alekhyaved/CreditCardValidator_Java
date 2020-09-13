package com.CreditCardProblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFileIO implements FileIO {

	/**
	 * implements strategy pattern. Takes a XML File, executes reading strategy and returns a list of cards
	 * @param XML File
	 * @return List<CreditCard>
	 */
	@Override
	public List<CreditCard> read(File file) {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("row");
			List<CreditCard> cardList = new ArrayList<CreditCard>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				cardList.add(getCreditCard(nodeList.item(i)));
			}
			return cardList;

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * Given a node from an XML file, creates a credit card object from the Node
	 * @param node
	 * @return
	 */
	private static CreditCard getCreditCard(Node node) {
		CreditCard cx = new CreditCard();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			try {

				if (getTagValue("CardNumber", element) == null || getTagValue("CardNumber", element) == "") {
					cx.setCardNumber(0L);
					System.out.println("There is a blank or null valued card number field in the file");
				} else {
					cx.setCardNumber(Double.valueOf(getTagValue("CardNumber", element)).longValue());

				}
				String expiryDateStr = getTagValue("ExpirationDate", element);
				if (getTagValue("ExpirationDate", element) == null || getTagValue("ExpirationDate", element) == ""
						|| !isValid(expiryDateStr)) {
					cx.setExpirationDate(null);
					System.out.println("There is a incorrect or null valued date field in the file");
				} else {
					cx.setExpirationDate(
							new SimpleDateFormat("MM/dd/yyyy").parse(getTagValue("ExpirationDate", element)));
				}
				if (getTagValue("NameOfCardholder", element) == null
						|| getTagValue("NameOfCardholder", element) == "") {
					cx.setName("");
					System.out.println("There is a blank or null valued name field in the file");
				} else {
					cx.setName(getTagValue("NameOfCardholder", element));

				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		return cx;
	}

	/**
	 * Given a dateString checks whether the string is a valid date or not
	 * @param expiryDateStr
	 * @return
	 */
	public static boolean isValid(String expiryDateStr) {
		DateFormat expiryDate = new SimpleDateFormat("MM/dd/yyyy");
		expiryDate.setLenient(false);
		try {
			expiryDate.parse(expiryDateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * Given a tag value from the XML file, fetches the value of the tag
	 * @param tag
	 * @param element
	 * @return
	 */
	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return (node != null ? node.getNodeValue() : null);
	}

	/**
	 * Implements strategy pattern. Takes a list of credit card objects and a XML file to write the credit card information and type of card.
	 * @param cards
	 * @param XML File
	 * @throws FileNotFoundException
	 */
	@Override
	public void write(List<CreditCard> cards, File file) {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {

			System.out.println("To implement write");
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);

			for (CreditCard card : cards) {
				rootElement.appendChild(getXMLFormattedCardDetails(doc, Long.toString(card.getCardNumber()),
						card.getCardType(), card.getError()));

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			StreamResult console = new StreamResult(System.out);
			StreamResult outputFile = new StreamResult(file);
			transformer.transform(source, console);
			transformer.transform(source, outputFile);
			System.out.println("DONE");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Sets node for card details
	 * @param doc
	 * @param cardNumber
	 * @param type
	 * @param Error
	 * @return
	 */
	private static Node getXMLFormattedCardDetails(Document doc, String cardNumber, String type, String Error) {
		Element card = doc.createElement("row");

		card.appendChild(getCardElements(doc, card, "CardNumber", cardNumber));
		card.appendChild(getCardElements(doc, card, "CardType", type));
		card.appendChild(getCardElements(doc, card, "Error", Error));

		return card;
	}

	/**
	 * Returns a Node for the card elements
	 * @param doc
	 * @param element
	 * @param name
	 * @param value
	 * @return
	 */
	private static Node getCardElements(Document doc, Element element, String name, String value) {

		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;

	}
}
