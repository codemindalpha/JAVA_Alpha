package testcases.C7009_IMPRXMLEXTENT__CWE611;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class C7009_IMPRXMLEXTENT_CWE611__saxParserFac2 {

    @RequestMapping(value = "/xmlupload", method = RequestMethod.POST)
    public void bad(@RequestParam("file") MultipartFile multipartFile) throws ParserConfigurationException, SAXException, IOException {
        File xmlFile = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(xmlFile);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        /* FLAW: CWE-611 */
        saxParser.parse(new FileInputStream(xmlFile),new DefaultHandler());
    }

    @RequestMapping(value = "/xmlupload2", method = RequestMethod.POST)
    public void good(@RequestParam("file") MultipartFile multipartFile) throws ParserConfigurationException, SAXException, IOException {
        File xmlFile = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(xmlFile);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(new FileInputStream(xmlFile),new DefaultHandler());
    }
}
