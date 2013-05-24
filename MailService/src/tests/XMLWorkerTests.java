package tests;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Testing the XMLWorker Class.
 *
 * @author shahisha
 */
public class XMLWorkerTests{

	/**
	 * Checks if all the xml file exist.
	 */
	@Test	
	public void XMLFileExistTest(){
		File fXmlFile = new File("routes.xml");
		assertTrue(fXmlFile.exists());
		fXmlFile = new File("trips.xml");
		assertFalse(fXmlFile.exists());
		fXmlFile = new File("mailevents.xml");
		assertTrue(fXmlFile.exists());
		fXmlFile = new File("costevents.xml");
		assertTrue(fXmlFile.exists());
		fXmlFile = new File("countrycitytown.xml");
		assertTrue(fXmlFile.exists());
	}
	
	
	/**
	 * Tests that the tag in the XML file match.
	 */
	@Test
	public void XMLTagTest(){
		try {

			File fXmlFile = new File("routes.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("route");
			System.out.println("-----------------------");

			getNodeElements(nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getNodeElements(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				System.out.println("Origin : " + getTagValue("origin", eElement));
				System.out.println("Destination : " + getTagValue("destination", eElement));
				System.out.println("Company Name : " + getTagValue("companyName", eElement));
				System.out.println("Max Weight: " + getTagValue("maxWeight", eElement));
				System.out.println("Max Volume : " + getTagValue("maxVolume", eElement));
				System.out.println("Weight Cost : " + getTagValue("weightCost", eElement));
				System.out.println("Volume Cost : " + getTagValue("volumeCost", eElement));
				System.out.println("Mail Cost : " + getTagValue("mailcost", eElement));
				System.out.println("Frequency : " + getTagValue("frequency", eElement));
				System.out.println("Estimated Delivery Time : " + getTagValue("estimatedDeliveryTime", eElement));
				System.out.println("Priority : " + getTagValue("priority", eElement));
				System.out.println();

			}
		}
	}
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

		Node nValue = nlList.item(0);

		return nValue.getNodeValue();
	}
	
}
