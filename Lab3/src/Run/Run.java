package Run;

import FiguryGeometryczne.Circle;
import FiguryGeometryczne.Rectangle;
import FiguryGeometryczne.Square;
import Input.Input;

public class Run {

    public void run() {
        Input input = new Input();

        System.out.println("1: Circle");
        System.out.println("2: Rectangle");
        System.out.println("3: Square");

        System.out.print("Choose figure: ");
        int choice = input.getInt();

        switch (choice) {
            case 1:
                System.out.print("R: ");
                new Circle(input.getDouble());
                break;

            case 2:
                Rectangle rect = new Rectangle();
                System.out.print("A: ");
                rect.setA(input.getDouble());
                System.out.print("B: ");
                rect.setB(input.getDouble());
                System.out.println("Field = " + rect.field());
                System.out.println("Round = " + rect.round());
                break;

            case 3:
                System.out.print("A: ");
                new Square(input.getDouble());
                break;

            default: System.out.println("wrong");
        }
    }

}
