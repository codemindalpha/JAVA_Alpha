package testcases.C6120_CLASSVM;

public class C6120_CLASSVM__simple_01 {
    static class Person{
        String name;
        int age;
        static String phoneNum;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        Person(String name, int age, String phoneNum) {
            this.name = name;
            this.age = age;
            this.phoneNum = phoneNum;
        }
    }
    public void bad() {
        Person p1 = new Person("Mark", 20, "1234");
        Person p2 = new Person("Nick", 23);

        p2.phoneNum = "5678";
    }
}
