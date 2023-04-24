package testcases.C7009_IMPRXMLEXTENT__CWE611;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileReader;
import java.io.IOException;

public class C7009_IMPRXMLEXTENT_CWE611__xmlInputFac {
    public void bad() throws IOException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();

        /* FLAW: CWE-611 */
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("xxe.xml"));
    }

    public void good() throws IOException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();

        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("xxe.xml"));
    }
}
