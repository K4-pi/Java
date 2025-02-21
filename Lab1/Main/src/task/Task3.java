package task;

import RunInput.Input;

public class Task3 {
    public static boolean EvenOrOdd() {
        System.out.print("Podaj liczbe: ");
        int num = Input.IntInput();

        return num % 2 == 0;
    }

}
