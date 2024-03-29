package mars_rover;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestsToXML {

	private static Logger logger = Logger.getLogger("XML Exporter");

	public static void main(String[] args)
	{
		String xmlFilePath = "xml/MarsRover_ListOfTest.xml";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		TransformerFactory tFactory = TransformerFactory.newInstance();

		DocumentBuilder dBuilder;
		Transformer transformer;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement("Mars_Rover_Tests");

			doc.appendChild(rootElement);

			rootElement.appendChild(addNewTest(doc,1,"Turn Right","3,3","2,2", "R","0,0,W","0,0,N"));
			rootElement.appendChild(addNewTest(doc,2,"Turn Left","4,4","1,3", "L","0,0,N","0,0,W"));
			rootElement.appendChild(addNewTest(doc,3,"Move Forward","3,3","3,3", "F","0,0,N","0,1,N"));
			rootElement.appendChild(addNewTest(doc,4,"Move Backward","3,3","3,2", "B","1,2,S","1,3,S"));
			rootElement.appendChild(addNewTest(doc,5,"Turn And Move","3,3","3,2/-1,1", "FRFL","0,0,N","1,1,N"));
			rootElement.appendChild(addNewTest(doc,6,"Jump Forward Over The Worlds Edge", "5,5", "4,4","FFFF","4,0,E","-3,0,E"));
			rootElement.appendChild(addNewTest(doc,7,"Jump Backward Over The Worlds Edge", "5,5", "4,4","BBBB","0,4,S","0,-3,S"));
			rootElement.appendChild(addNewTest(doc,8,"Hit An Obstacle","5,5","1,1", "FRFFFF","0,0,N","0,1,E"));

			transformer = tFactory.newTransformer();
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			StreamResult file = new StreamResult(new File(xmlFilePath));
			transformer.transform(source, file);
		} catch (Exception e) {
			logger.log(Level.WARNING, "Something happens", e);
		}
	}

	private static Node addNewTest(Document doc, int testID, String testName,
								  String worldSize, String obstacles, String commands,
								  String initialCoordinates, String expectedCoordinates)
	{
		Element element = doc.createElement("Test");
		element.setAttribute("Name",testName);
		element.setAttribute("ID",Integer.toString(testID));

		element.appendChild(addSingleElement(doc, "World_Size", worldSize));
		element.appendChild(addSingleElement(doc, "Obstacles", obstacles));
		element.appendChild(addSingleElement(doc, "Commands", commands));
		element.appendChild(addSingleElement(doc, "Initial_Rover_Coordinates", initialCoordinates));
		element.appendChild(addSingleElement(doc, "Expected_Rover_Coordinates", expectedCoordinates));

		return element;
	}

	private static Node addSingleElement(Document doc, String type, String value)
	{
		Element element = doc.createElement("Option");
		element.setAttribute(type,value);
		return element;
	}
}