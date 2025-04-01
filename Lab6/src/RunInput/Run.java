package RunInput;

import Tasks.InsertNames;

public class Run {

    private void RunMain() {
        Input input = new Input();

        System.out.println("Wybierz zadanie: ");
        System.out.println("zadanie 1");
        System.out.println("zadanie 2");

        System.out.print(">>> ");
        int choice = input.InputInt();
        switch (choice) {
            case 1:
                new InsertNames().Insert();
                break;
            case 2:
                System.out.println("wybrales 2");
                break;
            default:
                System.out.println("Wrong!");
        }
    }

    public Run() {
        RunMain();
    }
}
