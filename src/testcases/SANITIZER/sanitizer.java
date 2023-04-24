package testcases.SANITIZER;

import testcasesupport.IO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.logging.Level;

public class sanitizer {

    public void bad(HttpServletRequest request, HttpServletResponse response){

        String direct = null;
        File[] files = new File[];

        for(int i = 0; i < files.length; i++) {
            direct = files[i].getCanonicalPath();

            if(direct != null) {
                direct = direct.replaceAll("", "");
                direct = direct.replaceAll("", "");
                direct = direct.replaceAll("\"", "\"\"").replaceAll("\"\",\"\"", "\"\" ,\"\"")
            } else {
                direct = "";
            }

            File eof = new File(direct + File.separator + "eof.inf");
        }

        String fileName = request.getParameter("P");
        if (fileName==null || "".equals(fileName)) fileName = "dummy.txt";

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileInputStream fis = null;

        try {
//            fileName = fileName.replaceAll("\n", "").replaceAll("\r", "");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName+";");

            byte[] buffer = new byte[1024];

            // 외부입력값에서 경로순회(directory traversal) 문자열을 제거하지 않고 사용
            /* FLAW : CWE-99, CWE-22 */
            fis = new FileInputStream("C:/datas/" + fileName);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(response.getOutputStream());
            int read;
            while((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer,0,read);
            }
        } catch (FileNotFoundException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } catch (IOException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (bos != null) bos.close();
                if (bis != null) bis.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                IO.logger.log(Level.WARNING, e.toString());
            }

        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) {

        String fileName = request.getParameter("P");
        if (fileName==null || "".equals(fileName)) fileName = "dummy.txt";

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileInputStream fis = null;
        try {
            fileName = fileName.replaceAll("\n", "").replaceAll("\r", "");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName+";");

            byte[] buffer = new byte[1024];

            // 외부입력값에서 경로순회(directory traversal) 문자열을 제거하고 사용
            fileName = fileName.replaceAll("\\.\\.", "").replaceAll("/", "").replaceAll("\\\\", "");
            fis = new FileInputStream("C:/datas/" + fileName);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(response.getOutputStream());
            int read;
            while((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer,0,read);
            }
        } catch (FileNotFoundException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } catch (IOException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } finally {
            try {
                if (bos != null) bos.close();
                if (bis != null) bis.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                IO.logger.log(Level.WARNING, e.toString());
            }

        }
    }
}
