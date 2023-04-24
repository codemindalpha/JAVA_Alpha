package testcases.C0017_KEYLEN__CWE326;/*
Filename : CWE310_Cryptographic_Issues__basic_initialize_01_bad.java
*/



import testcasesupport.IO;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

public class C0017_KEYLEN__simple_01
{
    public static final String ALGORITHM = "RSA";
    public static final String PRIVATE_KEY_FILE = "C:/keys/private.key";
    public static final String PUBLIC_KEY_FILE = "C:/keys/public.key";

    public static void bad() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);

            /* FLAW : CWE-326 */
            keyGen.initialize(1024);
            final KeyPair key = keyGen.generateKeyPair();
            File privateKeyFile = new File(PRIVATE_KEY_FILE);
            File publicKeyFile = new File(PUBLIC_KEY_FILE);
        } catch (NoSuchAlgorithmException e) {
            IO.logger.log(Level.WARNING, e.toString());
        }
    }

    public static void good() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(2048);
            final KeyPair key = keyGen.generateKeyPair();
            File privateKeyFile = new File(PRIVATE_KEY_FILE);
            File publicKeyFile = new File(PUBLIC_KEY_FILE);
        } catch (NoSuchAlgorithmException e) {
            IO.logger.log(Level.WARNING, e.toString());
        }
    }

}

