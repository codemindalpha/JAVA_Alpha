package CF00B_RACETHREAD__CWE366;

public class RACETHREAD_GOOD {
    public static class ThreadRaceEx{
        static int foo = 0;

        public static void main(String[] args) {
            new newThread().start();
            foo = 1;
        }

        public static class newThread extends Thread {

            public synchronized void run() {
                System.out.println(foo);
            }
        }
    }
}
