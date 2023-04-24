package CF00F_IMPROPCLEAN__CWE460;

public class IMPROPCLEAN_BAD {
    public static class TestSource {
        public static final void main(String args[]) {

            boolean returnValue;
            returnValue = doStuff();
        }
        public static final boolean doStuff() {

            boolean threadLock;
            boolean value = true;
            boolean flag = false;
            try {

                while (flag == false) {

                    threadLock = true; //do some stuff to truthvalue
                    flag = true;
                    threadLock = false;
                }
            }
            catch (Exception e) {

                System.err.println("You did something bad");
                if (flag == false) return value;
            }
            return value;
        }
    }
}
