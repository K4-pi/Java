package run;

import dishes.Dishes;
import login.LoginManagment;

public class Run {

    public static void run() {
        Input input = new Input();
        LoginManagment login = new LoginManagment();
        Dishes dishes = new Dishes();

        System.out.println("2 - Login manager\n3 - dishes\n4 - exit");
        while (true) {
            System.out.print("Choose option: ");
            int choice = input.IntInput();

            switch (choice) {
                case 2:
                    login.RunLogin();
                    break;
                case 3:
                    dishes.RunDishes();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Out of scope");
            }
        }

    }
}
