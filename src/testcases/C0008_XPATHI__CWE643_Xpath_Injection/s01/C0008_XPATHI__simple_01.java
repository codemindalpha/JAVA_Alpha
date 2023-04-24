package testcases.C0008_XPATHI__CWE643_Xpath_Injection.s01;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class C0008_XPATHI__simple_01 {

    public void bad(String[] args) {
        String name = args[0];
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docBuilder.parse("http://www.w3schools.com/xml/simple.xml");
            XPath xpath = XPathFactory.newInstance().newXPath();
            /* FLAW : CWE-643 */
            NodeList nodes = (NodeList) xpath.evaluate("//food[name='" + name + "']/price",
                    doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println(nodes.item(i).getTextContent());
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void good(String[] args) {
        String name = args[0];
        if (name != null) {
            name = name.replaceAll("[()\\-'\\[\\]:,*/]", "");
        }
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docBuilder.parse("http://www.w3schools.com/xml/simple.xml");
            XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList nodes = (NodeList) xpath.evaluate("//food[name='" + name + "']/price", doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++){
                System.out.println(nodes.item(i).getTextContent());
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
