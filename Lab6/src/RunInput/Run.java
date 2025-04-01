package RunInput;

import Tasks.Couples;
import Tasks.InsertNames;
import Tasks.TwoLists;

public class Run {

    private void RunMain() {
        Input input = new Input();

        System.out.println("Wybierz zadanie: ");
        System.out.println("zadanie 1");
        System.out.println("zadanie 2");
        System.out.println("zadanie 5");

        System.out.print(">>> ");
        int choice = input.InputInt();
        switch (choice) {
            case 1:
                new InsertNames();
                break;
            case 2:
                new Couples();
                break;
            case 5:
                new TwoLists();
                break;
            default:
                System.out.println("Wrong!");
        }
    }

    public Run() {
        RunMain();
    }
}
