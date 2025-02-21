package task;

import RunInput.Input;

public class Task2 {

    public static void MathematicalOperation() {
        System.out.print("\nWpisz pierwsza liczbe: ");
        int first_num = Input.IntInput();

        System.out.print("Wpisz druga liczbe: ");
        int second_num = Input.IntInput();

        System.out.println("suma: " + (first_num + second_num));
        System.out.println("iloczyn: " + (first_num * second_num));
        System.out.println("iloraz: " + (first_num / second_num));
    }
}
