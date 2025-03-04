package Task;

import RunInput.Input;

import java.util.Random;

public class Tasks {
    Input input = new Input();

    public int Students() {
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

    public void Ten_numbers() {
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

    public int Even_num_sum(int[] arr) {
        int sum = 0;

        for (int x : arr) {
            if (x % 2 == 0) sum += x;
        }

        return sum;
    }

    public int Roll_numbers() {
        Random rand = new Random();
        Input input = new Input();

        System.out.print("Ile liczb wylosować: ");
        int how_many_nums = input.InputInt();
        int[] rand_nums = new int[how_many_nums];

        for (int i = 0; i < rand_nums.length; i++) rand_nums[i] = rand.nextInt(45)-10;

        return Even_num_sum(rand_nums);
    }

    public boolean Palindrom() {
        Input input = new Input();
        System.out.print("Podaj słowo do sprawdzenia: ");
        String text = input.InputString();
        char[] arr = text.toCharArray();

        for (int i = 0; i < text.length(); i++) {
            if (arr[i] != arr[text.length() - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}
