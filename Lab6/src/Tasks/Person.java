package Tasks;

import java.util.ArrayList;

public class Person {
    /*Zadanie 3.
    Napisz klasę przechowującą informacje o uczestnikach wydarzenia (ID, imię oraz jego wiek).
    Zaimplementować metodę toString(), aby wyświetlać informację o uczestniku oraz metody equals()
    oraz hashCode() (metody do porównywania obiektów). Do przechowywania uczestników należy użyć
    listy. Ponadto zaproponować metodę pozwalającą na filtrowanie osób niepełnoletnich. Zaproponować
    rozwiązanie z użyciem LinkedList oraz ArrayList*/

    int id;
    String name;
    int age;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && id == person.id && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31 + age + id;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nAge: " + age + "\n";
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
