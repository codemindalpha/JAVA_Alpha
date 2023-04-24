package testcases.C0073_USEJIO;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class C0073_CWE576_Use_of_JAVA_IO__bad_01 {
	/*

	by juho 17.12.11

	https://cwe.mitre.org/data/definitions/576.html

	## In this example, the interest rates for various points are retrieved from an XML document on the local file system, and the EJB uses the Java I/O API to retrieve the XML document from the local file system.
	*/
	@Stateless
	public class InterestRateBeanBad implements C0073_CWE576_Use_of_JAVA_IO__good_01.InterestRateRemote {
		private static final String INTEREST_RATE_FILE = "path";
		private BigDecimal bd = new BigDecimal("1.0");
		private Document interestRateXMLDocument = null;
		private File interestRateFile = null;
		public InterestRateBeanBad() throws ParserConfigurationException, SAXException {
			try {
				/* get XML document from the local filesystem */
				// FLAW:
				interestRateFile = new File(INTEREST_RATE_FILE);
				if (interestRateFile.exists()) {
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					interestRateXMLDocument = db.parse(interestRateFile);
				}
			} catch (IOException ex) { int action; }
		}
		public BigDecimal getInterestRate(Integer points) {  return getInterestRateFromXML(points);  }
		/* member function to retrieve interest rate from XML document on the local file system */
		private BigDecimal getInterestRateFromXML(Integer points) { return bd; }

		public BigDecimal getInterestRateGood(Integer points) {  return getInterestRateFromXMLParser(points);  }
		/* member function to retrieve interest rate from XML document using an XML parser API */
		private BigDecimal getInterestRateFromXMLParser(Integer points) { return bd; }
	}

	/*
	## An Enterprise JavaBean should use a resource manager API for storing and accessing data. In the following example, the private member function getInterestRateFromXMLParser uses an XML parser API to retrieve the interest rates.
	## This use of the Java I/O API within any kind of Enterprise JavaBean violates the EJB specification by using the java.io package for accessing files within the local filesystem.
	*/

	/*
	@Stateless
	public class InterestRateBeanGood implements InterestRateRemote {
		public InterestRateBeanGood() {	}

		public BigDecimal getInterestRate(Integer points) {
			return getInterestRateFromXMLParser(points);
		}
		// member function to retrieve interest rate from XML document using an XML parser API

		private BigDecimal getInterestRateFromXMLParser(Integer points) { int action; }
	}


	public interface InterestRateRemote{ }
	*/
}
