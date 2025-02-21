package task;

import RunInput.Input;

public class Task4 {
    public static boolean Divide() {
        System.out.print("Podaj liczbe: ");
        int num = Input.IntInput();

        return num % 3 == 0 && num % 5 == 0;
    }
}
