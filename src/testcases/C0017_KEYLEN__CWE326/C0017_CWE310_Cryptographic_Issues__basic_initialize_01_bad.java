package testcases.C0017_KEYLEN__CWE326;/*
Filename : CWE310_Cryptographic_Issues__basic_initialize_01_bad.java
*/



import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class C0017_CWE310_Cryptographic_Issues__basic_initialize_01_bad
{

    public void bad() throws Throwable
    {
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        /* FLAW */
        kpGen.initialize(512);
        KeyPair  pair = kpGen.generateKeyPair();

    }

}

