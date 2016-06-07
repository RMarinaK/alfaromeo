package tehnosila.tehnosila_automation.AppManager;
/**
 * @author RasstriginaMK
 *
 */
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ModifyXMLFile {
	public void editAttributeValue(String filepath, String xpath, String value) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xPath = xPathfactory.newXPath();
		XPathExpression expr = xPath.compile(xpath);
		Node node = ((NodeList) expr.evaluate(doc, XPathConstants.NODESET)).item(0);
		node.setNodeValue(value);		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "windows-1251");
		transformer.transform(new DOMSource(doc), new StreamResult(filepath));
	}
}
