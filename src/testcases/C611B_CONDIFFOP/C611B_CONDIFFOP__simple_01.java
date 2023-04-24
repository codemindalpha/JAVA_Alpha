package testcases.C611B_CONDIFFOP;

import java.util.List;
import java.util.Arrays;

public class C611B_CONDIFFOP__simple_01 {
    static class Person{
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    public void bad() {
        List<Person> persons = Arrays.asList(new Person("Mark", 26),
                                             new Person("Musk", 17),
                                             new Person("Tom",  32),
                                             new Person("Hana", 24));
        persons.stream().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2);
    }
}
