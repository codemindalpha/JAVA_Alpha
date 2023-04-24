package CF00F_IMPROPCLEAN__CWE460;

public class IMPROPCLEAN_GOOD {
    public static class TestSource {
        static boolean threadLock;
        public static final void main(String args[]) {

            boolean returnValue;
            try {
                returnValue = doStuff();
            }catch(Exception e){
                if(returnValue = true){
                    threadLock = false;
                }
            }
        }
        public static final boolean doStuff() throws Exception{


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
