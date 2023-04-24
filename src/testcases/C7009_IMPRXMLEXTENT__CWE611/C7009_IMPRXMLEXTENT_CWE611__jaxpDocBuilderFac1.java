package testcases.C7009_IMPRXMLEXTENT__CWE611;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;

public class C7009_IMPRXMLEXTENT_CWE611__jaxpDocBuilderFac1 {
    public void bad(File receivedXml) throws JAXBException, ParserConfigurationException, IOException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance( Student.class );
        Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();

        // 입력받은 receivedXml 을 이용하여 Document를 생성한다.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        /* FLAW: CWE-611 */
        Document document = db.parse(receivedXml);
        // document를 이용하여 마샬링을 수행한다.
        Student employee = (Student) jaxUnmarshaller.unmarshal(document);
    }

    public void good(File receivedXml) throws JAXBException, ParserConfigurationException, IOException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance( Student.class );
        Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();

        // 입력받은 receivedXml 을 이용하여 Document를 생성한다.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // 조치 방법
        // XML 파서가 doctype을 정의하지 못하도록 설정
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        // 외부 일반 엔티티를 포함하지 않도록 설정
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        // 외부 파라미터도 포함하지 않도록 설정
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        // 외부 DTD 비활성화
        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        // XIncude를 사용하지 않음
        dbf.setXIncludeAware(false);
        // 생성된 파서가 엔티티 참조 노드를 확장하지 않도록 함
        dbf.setExpandEntityReferences(false);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(receivedXml);
        Student employee = (Student) jaxUnmarshaller.unmarshal(document);
    }

    public class Student {
        private String name;
        private int age;
        private String info;

        public Student(String name, int age, String info){
            this.name = name;
            this.age = age;
            this.info = info;
        }
    }
}
