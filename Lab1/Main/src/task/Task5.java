package task;

import RunInput.Input;

public class Task5 {
    public static double PowerOfThree() {
        System.out.print("Podaj liczbe: ");
        double num = Input.IntInput();

        return Math.pow(num, 3);
    }
}
