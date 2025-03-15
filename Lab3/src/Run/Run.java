package Run;

import Input.Input;
import Calculator.Calculator;

public class Run {

    public void run() {
        Input input = new Input();

        System.out.println("1: Calculator");

        System.out.print("Choose: ");
        int choice = input.getInt();

        switch (choice) {
            case 1:
                new Calculator().display();
                break;

            default: System.out.println("wrong");
        }
    }

}
