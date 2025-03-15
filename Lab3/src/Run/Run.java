package Run;

import Building.Building;
import Input.Input;
import Calculator.Calculator;

public class Run {

    public void run() {
        Input input = new Input();

        System.out.println("1: Calculator");
        System.out.println("2: Building");

        System.out.print("Choose: ");
        int choice = input.getInt();

        switch (choice) {
            case 1:
                new Calculator().display();
                break;

            case 2:
                Building b = new Building("Good Building", 1999, 5);
                System.out.println(b);
                System.out.println("Has " + b.HowManyYears() + " years\n");

                Building b2 = new Building("Bad Building", 2005, 2);
                System.out.println(b2);
                System.out.println("Has " + b2.HowManyYears() + " years\n");

                Building b3 = new Building("Tent", 1960, 1);
                System.out.println(b3);
                System.out.println("Has " + b3.HowManyYears() + " years\n");
                break;

            default: System.out.println("wrong");
        }
    }

}
