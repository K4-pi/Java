package run;

import java.util.Scanner;

public class Input {
    private final Scanner sc = new Scanner(System.in);

    public  int IntInput() {
        return sc.nextInt();
    }

    public double DoubleInput() {
        return sc.nextDouble();
    }

    public String StringInput() {
        return sc.nextLine();
    }
}
