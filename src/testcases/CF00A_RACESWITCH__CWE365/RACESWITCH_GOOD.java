package CF00A_RACESWITCH__CWE365;

import java.time.LocalTime;

public class RACESWITCH_GOOD {
    public static void main(String[] args) {

        LocalTime cuttentTime = LocalTime.now();
        int m = cuttentTime.getMinute();
            switch (m % 2) {
                case 0:
                    System.out.print("One option\n");
                    break;
                case 1:
                    System.out.print("another option\n");
                    break;
                default:
                    System.out.print("default\n");
                    break;
        }
    }
}
