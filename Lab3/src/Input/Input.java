package Input;

import java.util.Scanner;

public class Input {
    Scanner scan = new Scanner(System.in);

    public double getDouble() {
        return scan.nextDouble();
    }

    public int getInt() {
        return scan.nextInt();
    }

}
