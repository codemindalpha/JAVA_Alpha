package testcases.C7007_IMPRCRYTSIG__CWE347;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

public class C7007_IMPRCRYTSIG_CWE347__simple {
    public void bad(String downloadedFilePath) throws IOException {
        File f = new File(downloadedFilePath);
        /* FLAW: CWE-347 */
        JarFile jf = new JarFile(f);
    }

    public void good(String downloadedFilePath) throws IOException {
        File f = new File(downloadedFilePath);
        JarFile jf = new JarFile(f, true);
    }
}
