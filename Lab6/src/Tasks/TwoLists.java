package Tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TwoLists {
    /*Zadanie 5.
    Utwórz i zapełnij listę List<Integer>. Utwórz drugą listę List<Integer>. Użyj ListIterator do przejrzenia
    elementów pierwszej listy i wstawienia ich do listy drugiej, ale w odwrotnej kolejności*/

    void Lists() {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        l1.add(0);
        l1.add(2);
        l1.add(4);
        l1.add(6);

        ListIterator<Integer> it = l1.listIterator();

        System.out.println("---list 1--------------------");
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("---list 2--------------------");

        while (it.hasPrevious()) {
            l2.add(it.previous());
        }

        for (Integer i : l2) {
            System.out.println(i);
        }
    }

    public TwoLists(){
        Lists();
    }
}
