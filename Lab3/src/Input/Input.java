package Input;

import java.util.Scanner;

public class Input {
    Scanner scan = new Scanner(System.in);

    public Double getDouble() {
        return scan.nextDouble();
    }

    public Integer getInt() {
        return scan.nextInt();
    }

}
