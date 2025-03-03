package RunInput;

import Task.Tasks;
import java.util.Scanner;

public class Run {

    public void RunTask(){
        //obiekt klas
        Tasks tasks = new Tasks();
        Input input = new Input();

        System.out.println("Laboratorium 2\n");
        System.out.println("Zadanie 1");
        System.out.println("Zadanie 2");
        System.out.println("Zadanie 3");
        System.out.println("Wybierz zadananie:");

        int wybor = input.InputInt();
        switch (wybor){
            case 1:
                System.out.println("Srednia: " + tasks.zd1()); break;
            case 2:
                tasks.zd2(); break;
            case 3:
                System.out.println("suma parzystych: " + tasks.suma_parzystych()); break;
            default: System.out.println("Nie ma takiego zadania");

        }
    }

}
