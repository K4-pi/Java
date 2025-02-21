package task;

import RunInput.Input;

public class Task6 {
    public static double Sqrt() {
        System.out.print("Podaj liczbe: ");
        double num = Input.IntInput();

        return Math.sqrt(num);
    }
}
