package Calculator;

import FiguryGeometryczne.*;
import Input.Input;

public class Calculator {

    public void display() {
        Input input = new Input();

        System.out.println("1: Circle");
        System.out.println("2: Rectangle");
        System.out.println("3: Square");
        System.out.println("4: Cube");
        System.out.println("5: Ball");
        System.out.println("6: Cone");

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
                Square s = new Square(input.getDouble());
                System.out.println(s.field());
                System.out.println(s.round());
                break;

            case 4:
                System.out.print("A: ");
                new Cube(input.getDouble());
                break;

            case 5:
                System.out.print("A: ");
                new Ball(input.getDouble());
                break;

            case 6:
                Cone cone = new Cone();
                System.out.print("R: ");
                cone.setR(input.getDouble());
                System.out.print("H: ");
                cone.setH(input.getDouble());
                cone.display();
                break;

            default: System.out.println("wrong");
        }

    }
}
