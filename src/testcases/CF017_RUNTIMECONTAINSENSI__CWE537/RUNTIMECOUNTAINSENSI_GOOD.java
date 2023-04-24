package CF017_RUNTIMECONTAINSENSI__CWE537;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class RUNTIMECOUNTAINSENSI_GOOD {
    public class InputFileRead {
        private File readFile = null;
        private FileReader reader = null;
        private String inputFilePath = null;

        private final String DEFAULT_FILE_PATH = "c:\\directory\\";

        public InputFileRead() {
            inputFilePath = DEFAULT_FILE_PATH;
        }

        public void setInputFile(String inputFile) {

            /* Assume appropriate validation / encoding is used and privileges / permissions are preserved */
        }

        public void readInputFile() {
            try {
                reader = new FileReader(readFile);
                        //...
            }
            catch (RuntimeException rex) {
                System.err.println("Error: Cannot open input file in the directory " );
                System.err.println("Input file has not been set, call setInputFile method before calling readInputFile");

            }
            catch (FileNotFoundException ex) {
                //...
            }
        }
    }
}
