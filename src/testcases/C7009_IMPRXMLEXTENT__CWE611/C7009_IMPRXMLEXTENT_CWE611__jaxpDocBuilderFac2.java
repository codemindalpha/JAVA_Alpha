package testcases.C7009_IMPRXMLEXTENT__CWE611;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class C7009_IMPRXMLEXTENT_CWE611__jaxpDocBuilderFac2 extends HttpServlet {
    private ServletFileUpload uploader = null;

    protected void bad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
                File xmlFile = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileItem.getName());
                fileItem.write(xmlFile);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                /* FLAW: CWE-611 */
                Document document = db.parse(xmlFile);
            }
        } catch (Exception e) {
            System.err.println("Exception : " + e.toString());
        }
    }

    protected void good(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
                File xmlFile = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileItem.getName());
                fileItem.write(xmlFile);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

                // 외부 엔티티 참조 제한
                dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
                dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.parse(xmlFile);
            }
        } catch (Exception e) {
            System.err.println("Exception : " + e.toString());
        }
    }
}
