package task;

import RunInput.Input;

import java.util.Random;

public class Task7 {
    public static boolean RightTriangle() {
        System.out.print("Podaj A: ");
        int a = Input.IntInput();

        System.out.print("Podaj B: ");
        int b = Input.IntInput();

        Random rand = new Random();
        int c = rand.nextInt();
        System.out.println(c);

        return true;
    }
}
