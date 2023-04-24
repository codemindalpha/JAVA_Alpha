package CF018_USESINGLETON__CWE543;

public class USESIGLETON_GOOD {
    private static Singleton single;

    public static Singleton get_singleton() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }
    public static class Singleton{

    }
}
