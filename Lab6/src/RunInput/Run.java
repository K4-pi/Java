package RunInput;

import Tasks.*;

public class Run {

    private void RunMain() {
        Input input = new Input();

        System.out.println("Wybierz zadanie: ");
        System.out.println("zadanie 1");
        System.out.println("zadanie 2");
        System.out.println("zadanie 3");
        System.out.println("zadanie 5");

        System.out.print(">>> ");
        int choice = input.InputInt();
        switch (choice) {
            case 1:
                new InsertNames().Insert();
                break;
            case 2:
                new Couples().InsertCouples();
                break;
            case 3:
                new Event().Run();
                break;
            case 5:
                new TwoLists().Lists();
                break;
            default:
                System.out.println("Wrong!");
        }
    }

    public Run() {
        RunMain();
    }
}
