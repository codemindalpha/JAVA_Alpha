package CF01A_CALLTHREADRUN__CWE572;

public class CALLTHREADRUN_GOOD {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            //...
        };

        thread.start();
    }
}
