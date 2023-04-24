package CF00B_RACETHREAD__CWE366;

public class RACETHREAD_BAD {
    public static class ThreadRaceEx{

        static int foo = 0;

        public static void main() {
            new newThread().start();
            foo = 1;
        }

        public static class newThread extends Thread {

            public void run() {
                System.out.println(foo);
            }
        }
    }
}
