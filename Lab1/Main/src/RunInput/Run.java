package RunInput;
import task.*;

public class Run {

    public void RunTask() {
        Task1 task = new Task1();

        System.out.println("Lab 1\n");

        for(int i = 0; i <= 8; i++) {
            System.out.println("Zadanie " + i);
        }

        System.out.print("\nWybierz zadanie: ");

        int choice = Input.IntInput();

        switch(choice) {
            case 1:
                System.out.println(task.UserData());
                break;
            case 2:
                Task2.MathematicalOperation();
                break;
            case 3:
                System.out.println(Task3.EvenOrOdd());
                break;
            case 4:
                System.out.println(Task4.Divide());
                break;
            case 5:
                System.out.println(Task5.PowerOfThree());
                break;
            case 6:
                System.out.println(Task6.Sqrt());
                break;
            case 7:
                System.out.println("NIC");
                break;
            case 8:
                System.out.println("NIC");
                break;
            default:
                System.out.println("BRAK ZADANIA!");
                break;
        }
    }

}
