package testcases.C0018_RANDOM__CWE330_Insufficiently_Random_Values.s01;

import testcasesupport.IO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.security.SecureRandom;
import java.util.logging.Level;

public class C0018_RANDOM__simple_01 {

    public void bad(){
        int maxValue = 199;
        int randomValue = bad1(maxValue);
        String authValue = bad2();
    }

    private int bad1(int maxValue) {
        //고정된 시드값을 사용하여 동일한 난수값이 생성되어 안전하지 않다.
        Random random = new Random(100);
        /* FLAW : CWE-330 */
        return random.nextInt(maxValue);
    }
    private String bad2() {
        //매번 변경되는 시드값을 사용하여 다른 난수값이 생성되나 보안 결정을 위한 난수로는 안전하지 않다.
        Random random = new Random();
        /* FLAW : CWE-330 */
        String authKey = Integer.toString(random.nextInt());
        return  authKey;
    }

    public void good(){
        int maxValue = 199;
        int randomValue = good1(maxValue);
        String authValue = good2();
    }

    private int good1(int maxValue) {
        //setSeed를 통해 매번 변경되는 시드값을 설정하거나, 기본값인 현재 시간 기반으로 매번 변경되는 시드값을 사용하도록 한다.
        Random random = new Random();
        return random.nextInt(maxValue);
    }

    private String good2() {
        //보안 결정을 위한 난수로는 예측이 거의 불가능하게 암호학적으로 보호된 Secure Random을 사용한다.
        String authKey = "";
        try{
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            secureRandom.setSeed(secureRandom.generateSeed(128));
            authKey = new String(digest.digest((secureRandom.nextLong()+ "").getBytes()));
        } catch (NoSuchAlgorithmException e){
            IO.logger.log(Level.WARNING, e.toString());
        }
        return authKey;
    }

}
