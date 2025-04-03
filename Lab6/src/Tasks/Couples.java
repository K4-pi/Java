package Tasks;

import RunInput.Input;

import java.util.ArrayList;
import java.util.List;

public class Couples {
    public void InsertCouples() {
        Input input = new Input();

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        while (true) {
            System.out.print("Pierwsze imie: ");
            String name1 = input.InputString();
            if(name1.equals("-")) break;

            System.out.print("Drugie imie: ");
            String name2 = input.InputString();
            if(name2.equals("-")) break;

            list1.add(name1);
            list2.add(name2);
        }
        System.out.println("Check couples: ");

        while (true) {
            String name = input.InputString();
            if (name.equals("-")) break;

            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).equals(name) || list2.get(i).equals(name))
                    System.out.println(list1.get(i) + " | " + list2.get(i));
            }
        }
    }
}
