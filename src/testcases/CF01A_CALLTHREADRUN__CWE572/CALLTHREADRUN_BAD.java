package CF01A_CALLTHREADRUN__CWE572;

public class CALLTHREADRUN_BAD {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            public void run() {
                //...
            }
        };
        thread.run();
    }
}
