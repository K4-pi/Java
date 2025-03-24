package task;

import RunInput.Input;

import java.util.Random;

public class Task7 {
    public static boolean RightTriangle() {
        System.out.println("Podaj przedzial losowania liczb");
        System.out.print("Od: ");
        int first = Input.IntInput();

        System.out.print("Do: ");
        int last = Input.IntInput();

        //Losowanie z przedziału podanego prze użytkownika
        Random rand = new Random();
        int[] x = { 0, 0, 0 };
        x[0] = rand.nextInt(first, last);
        x[1] = rand.nextInt(first, last);
        x[2] = rand.nextInt(first, last);

        System.out.println("A = " + x[0]);
        System.out.println("B = " + x[1]);
        System.out.println("C = " + x[2]);

        //Ktróy bok najdłuższy?
        int longestSide = Math.max(x[0], x[1]);
        longestSide = Math.max(longestSide, x[2]);

        System.out.println("Najdłuzszy bok " + longestSide);

        if(x[0] == longestSide) return (x[1] * x[1]) + (x[2] * x[2]) == longestSide;
        else if(x[1] == longestSide) return (x[0] * x[0]) + (x[2] * x[2]) == longestSide;
        return (x[0] * x[0]) + (x[1] * x[1]) == longestSide;
    }
}
