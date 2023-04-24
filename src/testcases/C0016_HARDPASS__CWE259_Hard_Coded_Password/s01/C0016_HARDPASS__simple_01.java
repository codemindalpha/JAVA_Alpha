package testcases.C0016_HARDPASS__CWE259_Hard_Coded_Password.s01;

import testcasesupport.IO;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;

public class C0016_HARDPASS__simple_01 {

    public class Bad{
        private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
        private static final String URL = "jdbc:oracle:thin:@192.168.0.3:1521:ORCL";
        private static final String USER = "SCOTT"; // DB ID;

        /* FLAW : CWE-259 */
        private static final String PASS = "SCOTT"; // DB PW;

        public void bad(){
            Connection con = getConn();
        }

        public Connection getConn() {
            Connection con = null;
            try {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, USER, PASS);
            } catch (Exception e) {
                IO.logger.log(Level.WARNING, e.getMessage());
            }
            return con;
        }
    }

    public class Good{
        private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
        private static final String URL = "jdbc:oracle:thin:@192.168.0.3:1521:ORCL";
        private static final String USER = "SCOTT"; // DB ID;

        public void good(String encryptionKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(),"AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            Connection con = getConn2(cipher);
        }

        private Connection getConn2(Cipher cipher) {
            Connection con = null;
            Properties props = new Properties();


            try {
                Class.forName(DRIVER);

                String PASS = props.getProperty("EncryptedPswd");
                byte[] decryptedPswd = cipher.doFinal(PASS.getBytes());
                PASS = new String(decryptedPswd);

                con = DriverManager.getConnection(URL, USER, PASS);
            } catch (Exception e) {
                IO.logger.log(Level.WARNING, e.getMessage());
            }
            return con;
        }
    }
}
