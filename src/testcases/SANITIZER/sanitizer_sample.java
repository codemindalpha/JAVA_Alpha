package testcases.SANITIZER;

import testcasesupport.IO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.logging.Level;

public class sanitizer_sample {

    public void bad(HttpServletRequest request, HttpServletResponse response) {

        String direct = request.getParameter("");
        String path = request.getParameter("");
        File file = new File(path);
        File[] files = file.listFiles();

        try {
            for (int i = 0; i < files.length; i++) {
                direct = files[i].getCanonicalPath();

            if(direct != null) {
                direct = direct.replaceAll("", "");
                direct = direct.replaceAll("", "");
                direct = direct.replaceAll("\"", "\"\"").replaceAll("\"\",\"\"", "\"\" ,\"\"");
            } else {
                direct = "";
            }

                File eof = new File(direct + File.separator + "eof.inf");
                File eof2 = new File(direct);
                File eof3 = new File(File.separator + "eof.inf");
                File eof4 = new File(File.separator);
                File eof5 = new File("eof.inf");
                File eof6 = new File(murph.checker.g.FileIOUtil.setFilePathReplaceAll(direct + File.separator + "eof.inf"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class FileIOUtil {
    public static String setFilePathReplaceAll(String ... strs) {
        String path = "";
        for (String str: strs) {
            path += str.replaceAll("", "");
        }
        return path;
    }
}
