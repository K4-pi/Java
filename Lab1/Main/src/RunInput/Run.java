package RunInput;
import task.Tasks;

public class Run {

    public void RunTask() {
        Tasks task = new Tasks();
        Input input = new Input();

        System.out.println("Lab 1\n");

        for(int i = 0; i <= 8; i++) {
            System.out.println("Zadanie " + i);
        }

        System.out.print("\nWybierz zadanie: ");

        int choice = input.IntInput();

        switch(choice) {
            case 1:
                System.out.println(task.UserData());
                break;
            case 2:
                System.out.println("NIC");
                break;
            case 3:
                System.out.println("NIC");
                break;
            case 4:
                System.out.println("NIC");
                break;
            case 5:
                System.out.println("NIC");
                break;
            case 6:
                System.out.println("NIC");
                break;
            case 7:
                System.out.println("NIC");
                break;
            case 8:
                System.out.println("NIC");
                break;
            default:
                System.out.println("Zły wybór");
                break;
        }
    }

}
