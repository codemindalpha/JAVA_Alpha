import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/*

by juho 17.12.11

## the EJB uses the Class Loader for the EJB class to obtain the XML document from the local file system as an input stream.
## This use of the Java Class Loader class within any kind of Enterprise JavaBean violates the restriction of the EJB specification against obtaining the current class loader as this could compromise the security of the application using the EJB.
*/
public class C0074_CWE578_Use_of_Class_Loader__bad_01 {
	private String INTEREST_RATE_FILE = "file";
	private String interestRateFile = "file";

	@Stateless
	public class Bad implements InterestRateRemote {
		private Document interestRateXMLDocument = null;

		public Bad() throws SAXException, ParserConfigurationException {
			try {
				// get XML document from the local filesystem as an input stream
				// using the ClassLoader for this class
				// FLAW:
				ClassLoader loader = this.getClass().getClassLoader();
				InputStream in = loader.getResourceAsStream(INTEREST_RATE_FILE);

				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				interestRateXMLDocument = db.parse(interestRateFile);
			} catch (FileNotFoundException ex) {
				int action;
			} catch (IOException ex) {
				int action;
			}
		}

		public BigDecimal getInterestRate(Integer points) {
			return getInterestRateFromXML(points);
		}

		/*
		 * member function to retrieve interest rate from XML document on the local file
		 * system
		 */
		private BigDecimal getInterestRateFromXML(Integer points) {
			return new BigDecimal("1.0");
		}
	}
	public interface InterestRateRemote{ }
}
