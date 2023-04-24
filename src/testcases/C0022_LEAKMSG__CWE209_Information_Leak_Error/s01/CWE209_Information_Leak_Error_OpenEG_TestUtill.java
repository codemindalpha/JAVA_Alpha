/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE209_Information_Leak_Error__printStackTrace_01.java
Label Definition File: CWE209_Information_Leak_Error.label.xml
Template File: point-flaw-01.tmpl.java
*/
/*
* @description
* CWE: 209 Information exposure through error message
* Sinks: printStackTrace
*    GoodSink: Print generic error message to console
*    BadSink : Print stack trace to console
* Flow Variant: 01 Baseline
*
* */

package testcases.C0022_LEAKMSG__CWE209_Information_Leak_Error.s01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CWE209_Information_Leak_Error_OpenEG_TestUtill 
{
   
	public String bad(String name)  {

		 StringBuffer buffer=new StringBuffer();
	     
		 try {
		   InputStream is =
				   this.getClass().getClassLoader().getResourceAsStream("config/address.xml");
	       DocumentBuilderFactory builderFactory = 
	      DocumentBuilderFactory.newInstance();
		   DocumentBuilder builder =  builderFactory.newDocumentBuilder();
		   Document xmlDocument = builder.parse(is);
		   XPath xPath =  XPathFactory.newInstance().newXPath();
		 
		   System.out.println("ccard 출력");
		   String expression = "/addresses/address[@name='"+name+"']/ccard";

		   NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
		   for (int i = 0; i < nodeList.getLength(); i++) {
			   buffer.append("CCARD[ "+i+ " ]  "+nodeList.item(i).getTextContent()+"<br/>");
		   }

		 
		        } catch (FileNotFoundException e) {
		        	/* FLAW */
		            e.printStackTrace();
		        } catch (SAXException e) {
		        	/* FLAW */
		            e.printStackTrace();
		        } catch (IOException e) {
		        	/* FLAW */
		            e.printStackTrace();
		        } catch (ParserConfigurationException e) {
		        	/* FLAW */
		            e.printStackTrace();
		        } catch (XPathExpressionException e) {
		        	/* FLAW */
		            e.printStackTrace();
		        } catch (Exception e) {
		        	/* FLAW */
		        	e.printStackTrace();
		        }
		        if ( buffer.length() == 0) {
		        	buffer.append("검색된 결과가 없습니다.");
		        }
	        	return buffer.toString();
		}
	
	  public String good(String name)  {
		  
		  return null;
	
	  }
	

}

