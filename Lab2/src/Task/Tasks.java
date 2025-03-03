package Task;

import RunInput.Input;

import java.util.Random;

public class Tasks {
    Input input = new Input();

    public int zd1() {
        System.out.println("Podaj liczbe studentów");
        int n = input.InputInt();
        int[] students_points = new int[n];

        if (n > 0) {
            int i = 0;
            while (i < n) {
                System.out.print("Student " + i + ": ");
                int points = input.InputInt();
                students_points[i] = points;
                i++;
            }

            i = 0;
            int sum_points = 0;
            while (i < n) {
                sum_points += students_points[i];
                i++;
            }

            return sum_points / n;
        }
        else {
            System.out.println("Niepoprwana liczba studentów");
            return 0;
        }
    }

    public void zd2() {
        int[] arr = new int[10];
        int ujemnych = 0;
        int dodatnich = 0;
        int suma_ujemnych = 0;
        int suma_dodatnich = 0;
        System.out.println("Wprowadź 10 liczb: ");

        for (int i = 0; i < 10; i++) {
            arr[i] = input.InputInt();
        }

        for (int x : arr) {
            if(x < 0) {
                ujemnych++;
                suma_ujemnych += x;
            }
            else {
                dodatnich++;
                suma_dodatnich += x;
            }
        }

        System.out.println("Liczb ujemnych: " + ujemnych);
        System.out.println("Suma liczb ujemnych: " + suma_ujemnych);
        System.out.println("Liczb dodatnich: " + dodatnich);
        System.out.println("Suma liczb dodatnich: " + suma_dodatnich);
    }

    public int suma_parzystych() {
        int[] n = {5, 1, 2, 1, 10, 22, 122};
        int sum = 0;

        for (int x : n) {
            if (x % 2 == 0) sum += x;
        }

        return sum;
    }

    public int zd4() {
        Input input = new Input();
        Random rand = new Random();
        int losowa_liczba = rand.nextInt(45)-10;


        return 0;
    }
}
