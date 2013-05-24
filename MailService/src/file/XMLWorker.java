package file;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import service.City;
import service.Mail;
import service.Parcel;
import service.Route;

public class XMLWorker {


	/**
	 * 
	 * @param fileName
	 * @param keyTag
	 * @param tags
	 * @param match
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static ArrayList<ArrayList<String>> readTagsConditional(String fileName, String keyTag, String[] tags, String[] match) throws ParserConfigurationException, SAXException, IOException{
		Element root = getDocElement(fileName).getDocumentElement();
		ArrayList<ArrayList<String>> retArr = new ArrayList<ArrayList<String>>();

		NodeList rootList = root.getElementsByTagName(keyTag);
		for (int i = 0; i < rootList.getLength(); i++){
			ArrayList<String> dat = new ArrayList<String>();
			Element e = (Element)rootList.item(i);

			boolean isOk = true;
			for (int k = 0; k < tags.length; k++){
				NodeList nl = e.getElementsByTagName(tags[k]);
				// The list of nodes in indent
				if(nl != null && nl.getLength() > 0) {
					Element el = (Element)nl.item(0);
					
					if (match == null || match[k] == null || (match[k]!= null && el.getTextContent().equals(match[k])))
						dat.add(el.getTextContent());
					else isOk = false;
				}
			}	
			if(isOk)
				retArr.add(dat);
		}

		return retArr;
	}


	/**
	 * Method which loads the given xml file, and returns the root of the xml's tree.
	 * (This is the tag which contains ALL the data).
	 * @param filename XML File to read
	 * @return root node
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static Document getDocElement(String filename) throws ParserConfigurationException, SAXException, IOException{
		File file = new File(filename + ".xml");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();		
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();

		//get the root element
		return doc;		
	}

	
	
	
	/**
	 * Method which reads in the list of countries. 
	 * @return List of countries
	 */
	public static ArrayList<String> loadCountries(){
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			Document doc = getDocElement("countrycitytown");
			Element root = doc.getDocumentElement();
			NodeList nl = root.getElementsByTagName("country");
			for (int i = 0; i < nl.getLength(); i++)
				data.add(nl.item(i).getAttributes().getNamedItem("name").getTextContent());
			
		} 
		catch (Exception e) { e.printStackTrace(); }
		return data;
	}
	
	
	/**
	 * Returns the list of mailable locations to the given country.
	 * @param country Country to get mailable locations from.
	 * @return Arraylist of cities within the given country.
	 */
	public static ArrayList<String> getCitiesFromCountry(String country){
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			Document doc = getDocElement("countrycitytown");
			Element root = doc.getDocumentElement();
			NodeList nl = root.getElementsByTagName("country");
			for (int i = 0; i < nl.getLength(); i++)
				if (nl.item(i).getAttributes().getNamedItem("name").getTextContent().equals(country)){
					NodeList cities = ((Element)(nl.item(i))).getElementsByTagName("city");
					for (int j = 0; j < cities.getLength(); j++)
						data.add(cities.item(j).getTextContent());
				}
		} 
		catch (Exception e) { e.printStackTrace(); }
		return data;
	}
	
	
	/**
	 * Returns the list of mailable locations to the given country.
	 * @param country Country to get mailable locations from.
	 * @return Arraylist of cities within the given country.
	 */
	public static ArrayList<City> getAllCitiesFromCountry(String country){
		ArrayList<City> data = new ArrayList<City>();
		try {
			Document doc = getDocElement("countrycitytownlatlong");
			Element root = doc.getDocumentElement();
			NodeList nl = root.getElementsByTagName("country");
			for (int i = 0; i < nl.getLength(); i++)
				if (nl.item(i).getAttributes().getNamedItem("name").getTextContent().equals(country)){
					NodeList cities = ((Element)(nl.item(i))).getElementsByTagName("city");
					
					for (int j = 0; j < cities.getLength(); j++){
						City c = new City();
						c.setId(i);
						c.setName(((Element)(cities.item(j))).getElementsByTagName("name").item(0).getTextContent());
						c.setLatitude(Double.parseDouble(((Element)(cities.item(j))).getElementsByTagName("latitude").item(0).getTextContent()));
						c.setLongitude(Double.parseDouble(((Element)(cities.item(j))).getElementsByTagName("longitude").item(0).getTextContent()));
						data.add(c);
					}
				}
		} 
		catch (Exception e) { e.printStackTrace(); }
		return data;
	}
	
	
	/**
	 * Method which gets all the cities from every country and returns a list of lists of strings <br>
	 * where each list is a country, and each String is the city name. The countries are in the <br>
	 * same order as the list returned from XMLWorker.loadCountries(), sorted alphabetically.
	 * @return
	 */
	public static ArrayList<ArrayList<String>> getAllCities(){
		ArrayList<ArrayList<String>> allCities = new ArrayList<ArrayList<String>>();
		try {
			Element root = getDocElement("countrycitytown").getDocumentElement();
			NodeList nl = root.getElementsByTagName("country");
			
			for (int i = 0; i < nl.getLength(); i++){
				ArrayList<String> cities = new ArrayList<String>();
				NodeList cityList = ((Element)nl.item(i)).getElementsByTagName("city");
				for (int j = 0; j < cityList.getLength(); j++){
					cities.add(cityList.item(j).getTextContent());
				}
				allCities.add(cities);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allCities;
	}
	
	/**
	 * Method which gets all the cities from every country and returns a list of lists of City objects <br>
	 * where each list is a country, and each City belongs to that city. The countries are in the <br>
	 * same order as the list returned from XMLWorker.loadCountries(), sorted alphabetically.
	 * @return
	 */
	public static ArrayList<ArrayList<City>> getAllCitiesAsCity(){
		ArrayList<ArrayList<City>> allCities = new ArrayList<ArrayList<City>>();
		try {
			Document doc = getDocElement("countrycitytownlatlong");
			Element root = doc.getDocumentElement();
			NodeList nl = root.getElementsByTagName("country");
			for (int i = 0; i < nl.getLength(); i++){
					NodeList cities = ((Element)(nl.item(i))).getElementsByTagName("city");
					ArrayList<City> data = new ArrayList<City>();
					for (int j = 0; j < cities.getLength(); j++){
						City c = new City();
						c.setId(i);
						c.setName(((Element)(cities.item(j))).getElementsByTagName("name").item(0).getTextContent());
						c.setLatitude(Double.parseDouble(((Element)(cities.item(j))).getElementsByTagName("latitude").item(0).getTextContent()));
						c.setLongitude(Double.parseDouble(((Element)(cities.item(j))).getElementsByTagName("longitude").item(0).getTextContent()));
						data.add(c);
					}
					allCities.add(data);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allCities;
	}

	
	public static ArrayList<City> getListOfAllCities(){
		ArrayList<City> data = new ArrayList<City>();
		try {
			Document doc = getDocElement("countrycitytownlatlong");
			Element root = doc.getDocumentElement();
			NodeList nl = root.getElementsByTagName("country");
			for (int i = 0; i < nl.getLength(); i++){
					NodeList cities = ((Element)(nl.item(i))).getElementsByTagName("city");
					
					for (int j = 0; j < cities.getLength(); j++){
						City c = new City();
						c.setId(i);
						c.setName(((Element)(cities.item(j))).getElementsByTagName("name").item(0).getTextContent());
						c.setLatitude(Double.parseDouble(((Element)(cities.item(j))).getElementsByTagName("latitude").item(0).getTextContent()));
						c.setLongitude(Double.parseDouble(((Element)(cities.item(j))).getElementsByTagName("longitude").item(0).getTextContent()));
						data.add(c);
					}
				}
		} 
		catch (Exception e) { e.printStackTrace(); }
		return data;
	}
	
	/**
	 * Adds a new mail or parcel event to the mailevents database. Can be as a Mail <br>
	 * or Parcel object.
	 * 
	 * @param mail Object to save data of
	 */
	public static void addMail(Mail mail) {
		Document doc;
		try {
			doc = getDocElement("mailevents");
		Element root = doc.getDocumentElement();
		
		Element newMail = doc.createElement("mail");
		String[] tags = new String[]{"day", "to", "from", "cost", "priority"};
		String[] data = mail.getData();
		
		if (mail instanceof Parcel){
			newMail = doc.createElement("parcel");
			tags = new String[]{"day", "to", "from", "weight", "volume", "cost", "priority"};
			data = ((Parcel)mail).getData();
		}
		
		root.appendChild(newMail);
		for (int i = 0; i < tags.length; i++){
			Element e = doc.createElement(tags[i]);
			e.appendChild(doc.createTextNode(data[i]));
			newMail.appendChild(e);
		}
		
		finishWritingXML(doc, "mailevents");
		}catch(Exception e){e.printStackTrace();}
	}
	

	
	
	
	/**
	 * Method which digs out all parcel events from the xml file excluding mail <br>
	 * with the given conditions. <br> 
	 * -The match length must be 6, and in the order {"day", "to", "from", "weight", "volume", "priority"} 
	 * -If you do not want a condition on the corresponding match index <br>
	 *  make the match string null;<br>
	 *  -Date should be in format dd/mm/yyyy.<br><br>
	 *  i.e.<br>
	 *  match = new String[]{"13/01/2013", null, null, null, null, null} <br><br>
	 * 
	 * 	This would return all parcel events on the 13/01/2013
	 * @param match Data must match at the corresponding tag to be included in list.
	 * @return List of parcels with given parameter
	 */
	public static ArrayList<Parcel> getParcels(String[] match){
		ArrayList<Parcel> parcels = new ArrayList<Parcel>();
		try {
			ArrayList<ArrayList<String>> pdata = readTagsConditional("mailevents", "parcel", new String[]{"day", "to", "from", "weight", "volume", "cost", "priority"}, match);

			for (ArrayList<String> object: pdata)
				parcels.add(new Parcel(object.get(0), object.get(1), object.get(2), object.get(3), object.get(4), object.get(5), object.get(6)));

		} 
		catch (ParserConfigurationException e) {e.printStackTrace();} 
		catch (SAXException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}

		return parcels;
	}

	/**
	 * Method which digs out all parcel events from the xml file excluding mail <br>
	 * with the given conditions. <br> 
	 *  -The match length must be 5, and in the order {"day", "to", "from", "cost", "priority"} 
	 *  -If you do not want a condition on the corresponding match index <br>
	 *   make the match string null;<br>
	 *  -Date should be in format dd/mm/yyyy.<br><br>
	 *  i.e.<br>
	 *  match = new String[]{"13/01/2013", null, null, null} <br><br>
	 * 
	 * 	This would return all parcel events on the 13/01/2013
	 * @param match Data must match at the corresponding tag to be included in list.
	 * @return List of parcels with given parameter
	 */
	public static ArrayList<Mail> getMail(String[] match){
		ArrayList<Mail> mail = new ArrayList<Mail>();
		try {
			ArrayList<ArrayList<String>> pdata = readTagsConditional("mailevents", "mail", new String[]{"day", "to", "from", "cost", "priority"}, match);

			for (ArrayList<String> object: pdata)
				mail.add(new Mail(object.get(0), object.get(1), object.get(2), object.get(3), object.get(4)));

		} 
		catch (ParserConfigurationException e) {e.printStackTrace();} 
		catch (SAXException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}

		return mail;
	}

	/**
	 * Method which returns the amount of sales between 2 given dates.
	 * @param fromDate Earliest date
	 * @param toDate Lates date
	 * @return sales between dates
	 */
	public static double getTotalSalesBetweenDate(String fromDate, String toDate){
		ArrayList<Mail> mail = XMLWorker.getMail(new String[]{null, null, null, null, null});
		ArrayList<Parcel> parcels = XMLWorker.getParcels(new String[]{null, null, null, null, null, null, null});
	

		mail = XMLWorker.getMailBetweenDates(mail, fromDate, toDate);
		ArrayList<Mail> parcelMail = XMLWorker.getMailBetweenDates(parcels, fromDate, toDate);

		
		ArrayList<String> nzCities = XMLWorker.getCitiesFromCountry("New Zealand");
		double total = 0;
		
		for (Mail m: mail)
			if (nzCities.contains(m.getFrom()))
				total += m.getCost();
		for (Mail m: parcelMail)
			if (nzCities.contains(m.getFrom()))
				total += m.getCost();
				
		return total;
	}
	
	/**
	 * Gets the total sales over all mailevents
	 * @return all sales
	 */
	public static double getTotalSales(){
		ArrayList<String> nzCities = XMLWorker.getCitiesFromCountry("New Zealand");
		ArrayList<Mail> mail = XMLWorker.getMail(new String[]{null, null, null, null, null});
		ArrayList<Parcel> parcels = XMLWorker.getParcels(new String[]{null, null, null, null, null, null, null});
		
		double total = 0;
		
		for (Mail m: mail)
			if (nzCities.contains(m.getFrom()))
				total += m.getCost();
		for (Parcel p: parcels)
			if (nzCities.contains(p.getFrom()))
				total += p.getCost();
		
				
		return total;

	}


	
	
	
	/**
	 * Method which takes a list of Mail and returns a new List of Mail where the dates are <br>
	 * between the range specified
	 * @param mail List to filter
	 * @param date1 
	 * @param date2
	 * @return ArrayList<Mail> mail between given date range.
	 */
	public static ArrayList<Mail> getMailBetweenDates(ArrayList<? extends Mail> mail, String date1, String date2){
		ArrayList<Mail> newList = new ArrayList<Mail>();
		if (!XMLWorker.dateFormatOK(date1)){
			System.out.println("date1 is incorrect: " + date1);
			return newList;
		}
		if (!XMLWorker.dateFormatOK(date2)){
			System.out.println("date2 is incorrect: " + date2);
			return newList;
		}
		
		Date d1 = XMLWorker.parseDate(date1);
		Date d2 = XMLWorker.parseDate(date2);

		// Make sure d1 is the older date
		if (d1.compareTo(d2) > 0){
			Date temp = d1;
			d1 = d2;
			d2 = temp;
		}

		for (Mail m: mail){
			Date day = XMLWorker.parseDate(m.getDay());
			// If mail day is between date range, add to new list
			if (d1.compareTo(day) <= 0 && d2.compareTo(day) >= 0){
				newList.add(m);
			}
		}

		return newList;
	}

	
	public static boolean addRoute(Route r){
		return addRouteToNode("routes", r);
	}
	
	private static boolean addRouteToNode(String filename, Route r){
		Document doc;
		try {
			doc = getDocElement(filename);
		Element root = doc.getDocumentElement();
		
		Element newRoute = doc.createElement("route");
		String[] tags = new String[]{"origin", "destination", "companyName", 
				"maxWeight","maxVolume", "weightCost", "volumeCost", "mailcost", 
				"frequency", "estimatedDeliveryTime", "priority"};
		
		String[] data = r.getData();
		
		
		root.appendChild(newRoute);
		for (int i = 0; i < tags.length; i++){
			Element e = doc.createElement(tags[i]);
			e.appendChild(doc.createTextNode(data[i]));
			newRoute.appendChild(e);
		}
		
		finishWritingXML(doc, filename);
		return true;
		}catch(Exception e){e.printStackTrace();}
		return false;
	}
	
	
	public static boolean modifyRoute(Route route){
		Document doc;
		try {
			doc = XMLWorker.getDocElement("routes");
		
		Element root = doc.getDocumentElement();
		NodeList nl = root.getElementsByTagName("origin");
		
		for (int i = 0; i < nl.getLength(); i++){
			
			Element parent = (Element)nl.item(i).getParentNode();
			NodeList list = parent.getElementsByTagName("origin");
			
			if (list.item(0).getTextContent().equals(route.getOrigin())){
				list = parent.getElementsByTagName("destination");
				if (list.item(0).getTextContent().equals(route.getDestination())){
					list = parent.getElementsByTagName("companyName");
					if (list.item(0).getTextContent().equals(route.getCompany())){
						root.removeChild(parent);
						updateXML(doc, "routes");
						addRoute(route);
						addCostEvent(route);
						return true;
					}	
				}
				
			}
			
			
		}
		} 
		catch (ParserConfigurationException e) { e.printStackTrace(); } 
		catch (SAXException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
		return false;
	}
	
	private static boolean addCostEvent(Route route){
		return XMLWorker.addRouteToNode("costevents", route);
	}
	
	
	public static boolean deleteRoute(Route route){
		Document doc;
		try {
			doc = XMLWorker.getDocElement("routes");
		
		Element root = doc.getDocumentElement();
		NodeList nl = root.getElementsByTagName("origin");
		
		for (int i = 0; i < nl.getLength(); i++){
			
			Element parent = (Element)nl.item(i).getParentNode();
			NodeList list = parent.getElementsByTagName("origin");
			
			if (list.item(0).getTextContent().equals(route.getOrigin())){
				list = parent.getElementsByTagName("destination");
				if (list.item(0).getTextContent().equals(route.getDestination())){
					list = parent.getElementsByTagName("companyName");
					if (list.item(0).getTextContent().equals(route.getCompany())){
						root.removeChild(parent);
						updateXML(doc, "routes");
						addDiscontinued(route);
						return true;
					}	
				}
				
			}
			
			
		}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private static void addDiscontinued(Route route){
		addRouteToNode("discontinued", route);
	}
	
	public static ArrayList<Route> getAllRoutes(){
		ArrayList<Route> listORoute = new ArrayList<Route>();
		try {
			ArrayList<ArrayList<String>> routes = XMLWorker.readTagsConditional("routes", "route", 
					new String[]{"origin", "destination", "companyName", 
					"maxWeight","maxVolume", "weightCost", "volumeCost", "mailcost", 
					"frequency", "estimatedDeliveryTime", "priority"}, null);
			
			
			for(ArrayList<String> arr: routes){
				Route r = new Route(arr.get(0), arr.get(1), arr.get(2), Integer.parseInt(arr.get(3)), Integer.parseInt(arr.get(4)),
						Double.parseDouble(arr.get(5)), Double.parseDouble(arr.get(6)), Double.parseDouble(arr.get(7)), 
						Integer.parseInt(arr.get(8)), Integer.parseInt(arr.get(9)),  arr.get(10));
				listORoute.add(r);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listORoute;
	}

	public static Date parseDate(String input){
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
		Date t; 

		try { 
			t = ft.parse(input); 
			return t;
		} catch (ParseException e) { 
			System.out.println("Unparseable using " + ft);
			return null;
		}

	}

	private static boolean dateFormatOK(String date){
		try{
			Date d1 = XMLWorker.parseDate(date);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	private static void updateXML(Document doc, String filename){
		
		try {
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename + ".xml"));
			transformer.transform(source, result);
			
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	private static void finishWritingXML(Document doc, String filename) throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filename + ".xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
	}


	public static void main(String[] args){

		
	    
	    Route route = new Route("Wellington", "Changi", "Air New Zealand", 2000, 40, 2.0,
				4.0, 2.0, 3, 100, "International Air");
		
	    System.out.println("Deleted: " + XMLWorker.deleteRoute(route));
	    
	    
/*		
		XMLWorker.addRoute(new Route("a", "b", "c", 200, 400, 2.0,
				4.0, 2.0, 3, 4, "Air"));
		XMLWorker.addRoute(new Route("f", "asdf", "c", 200, 400, 2.0,
				4.0, 2.0, 3, 4, "Air"));
		XMLWorker.addRoute(new Route("h", "bfds", "c", 200, 400, 2.0,
				4.0, 2.0, 3, 4, "Air"));
		XMLWorker.addRoute(new Route("re", "uyte", "c", 200, 400, 2.0,
				4.0, 2.0, 3, 4, "Air"));
		XMLWorker.addRoute(new Route("tre", "234", "c", 200, 400, 2.0,
				4.0, 2.0, 3, 4, "Air"));
		
		
		ArrayList<Route> routes = XMLWorker.getAllRoutes();
		for (Route r: routes){
			System.out.println(r.toString());
		}*/
/*
 * 
 * 
		ArrayList<Parcel> parcels = XMLWorker.getParcels(new String[]{null, null, null, null, null, null});
		for (Parcel p: parcels){
			System.out.println("Parcel: " + p.toString());
		}

		ArrayList<Mail> mail = XMLWorker.getMail(new String[]{null, null, null, null});
		for (Mail m: mail){
			System.out.println("Mail: " + m.toString());
		}		

		XMLWorker.addMail(new Mail("02/02/2012", "Madrid", "Palmerston North", 1));
		XMLWorker.addMail(new Parcel("02/04/2012", "Ho-Chi Min", "Clive", "2","5", 2));
		*/
		
/*		ArrayList<ArrayList<City>> allcities = XMLWorker.getAllCitiesAsCity();
		for (ArrayList<City> cities: allcities)
		for (City city: cities){
			System.out.println(city.toString());
		}*/
		
		/*
		try {
			ArrayList<ArrayList<String>> arr = 	XMLWorker.readTagsConditional("mailevents", "parcel", 
					new String[]{"day", "to", "from", "priority", "weight", "volume"}, 
						new String[]{null, null, null, null, "3", null});
			for (ArrayList<String> array: arr){
				System.out.println("##### new Object #####");
				System.out.println(array + "\n");
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*		loadCountries();
		ArrayList<Mail> am = getMail();
		for (Mail m: am){
			System.out.println(m.toString());
		}

		Collections.sort(am, Mail.mailDateAscending());
		System.out.println("\n\n");
		for (Mail m: am){
			System.out.println(m.toString());
		}

		Collections.sort(am, Mail.mailDateDescending());
		System.out.println("\n\n");
		for (Mail m: am){
			System.out.println(m.toString());
		}		


		Collections.sort(am, Mail.mailPriorityAscending());
		System.out.println("\n\n");
		for (Mail m: am){
			System.out.println(m.toString());
		}		

		Collections.sort(am, Mail.mailPriorityDescending());
		System.out.println("\n\n");
		for (Mail m: am){
			System.out.println(m.toString());
		}		
		*/
	/*	ArrayList<Mail> mail  = XMLWorker.getMail(new String[]{null,null,null,null,null,null});
		ArrayList<Parcel> parcels  = XMLWorker.getParcels(new String[]{null,null, null, null,null,null,null});
		
		mail = XMLWorker.getMailBetweenDates(mail, "10/05/2013", "09/06/2013");
		ArrayList<Mail> filtered = XMLWorker.getMailBetweenDates(parcels, "10/05/2013", "09/06/2013");
		
		mail.addAll(filtered);
		
		System.out.println("\n\n");
		for (Mail m: mail){
			System.out.println(m.toString() + "\n");
		}	
		
		System.out.println("Total Sales: " + XMLWorker.getTotalSales());
		System.out.println("Total Sales between : 10/05/2013 and 09/06/2013: " + XMLWorker.getTotalSalesBetweenDate("10/05/2013", "09/06/2013"));
	*/
	}

}
