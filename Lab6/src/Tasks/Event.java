package Tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Event {
    ArrayList<Person> persons = new ArrayList<>();
    LinkedList<Person> linkedPersons = new LinkedList<>();

    private String niePelnoletnie(LinkedList<Person> persons) {
        for (Person p : persons) {
            if (p.age < 18) return p.toString();
        }
        return "Null";
    }

    private String niePelnoletnie(ArrayList<Person> persons) {
        Iterator<Person> it = persons.iterator();
        while (it.hasNext()) {
            Person next = it.next();
            if (next.age < 18) return next.toString();
        }
        return "Null";
    }

    public void Run() {
        persons.add(new Person(421, "Tomek", 18));
        persons.add(new Person(632, "Anna", 16));
        persons.add(new Person(321, "Krzysiek", 21));
        persons.add(new Person(421, "Tomek", 18));

        linkedPersons.add(new Person(421, "Tomek", 18));
        linkedPersons.add(new Person(632, "Anna", 16));
        linkedPersons.add(new Person(321, "Krzysiek", 21));
        linkedPersons.add(new Person(421, "Tomek", 18));

        // toString / All informations
        for (Person p : persons) {
            System.out.println(p);
            System.out.println("Hashcode: " + p.hashCode());
        }

        System.out.println("\n----------------------- OSOBY NIE PELNO LETNIE (ArrayList) -----------------------\n");
        System.out.println(niePelnoletnie(persons));

        System.out.println("\n----------------------- OSOBY NIE PELNO LETNIE (LinkedList) -----------------------\n");
        System.out.println(niePelnoletnie(linkedPersons));
    }

}
