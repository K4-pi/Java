package RunInput;

import Task.Tasks;

public class Run {

    public void RunTask(){
        //obiekt klas
        Tasks tasks = new Tasks();
        Input input = new Input();

        System.out.println("Laboratorium 2\n");
        System.out.println("Zadanie 1");
        System.out.println("Zadanie 2");
        System.out.println("Zadanie 3");
        System.out.println("Zadanie 4");
        System.out.println("Zadanie 5");
        System.out.print("Wybierz zadananie:");

        int choice = input.InputInt();
        switch (choice){
            case 1:
                System.out.println("Srednia: " + tasks.Students());
                break;
            case 2:
                tasks.Ten_numbers();
                break;
            case 3:
                int[] n = {1, 4, 2, 10, 2, 12}; //tabela do testów
                System.out.println("suma parzystych: " + tasks.Even_num_sum(n));
                break;
            case 4:
                System.out.println("Suma losowych liczb parzystych wynosi: " + tasks.Roll_numbers());
                break;
            case 5:
                if (tasks.Palindrom()) System.out.println("Jest palindromem");
                else System.out.println("Nie jest palindromem");
                break;
            default:
                System.out.println("Nie ma takiego zadania");
        }
    }

}
