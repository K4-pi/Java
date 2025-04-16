package dishes;

import run.Input;
import run.Run;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dishes {
    Map<String, Double> dishesList = new HashMap<>();

    private void AddDish() {
        Input input = new Input();

        System.out.print("choose dish name: ");
        String dishName = input.StringInput();
        System.out.print("choose price: ");
        Double price = input.DoubleInput();

        if (!dishesList.containsKey(dishName)) dishesList.put(dishName, price);
        else System.out.println("Dish: " + dishName + " already exist");
    }

    private void ListDishes() {
        Set<String> keys = dishesList.keySet();

        System.out.println("-----MENU-----");
        for (String s : keys) {
            System.out.println(s + " - " + dishesList.get(s) + " $");
        }
        System.out.println("--------------");
    }

    private void RemoveDish() {
        Input input = new Input();

        System.out.print("choose dish to delete: ");
        String dishName = input.StringInput();

        dishesList.remove(dishName);
    }

    private double CalculateFullPrice() {
        Set<String> keys = dishesList.keySet();
        double sum = 0;

        for (String s : keys) {
            sum += dishesList.get(s);
        }
        return sum;
    }

    public void RunDishes() {
        Input input = new Input();
        System.out.println("1 - add dish\n2 - list dishes\n3 - remove dish\n4 - full price\n5 - exit");

        while (true) {
            System.out.print("Choose option: ");
            int choice = input.IntInput();

            switch (choice) {
                case 1:
                    AddDish();
                    break;
                case 2:
                    ListDishes();
                    break;
                case 3:
                    RemoveDish();
                    break;
                case 4:
                    System.out.println("Full price of meal: " + CalculateFullPrice());
                    break;
                case 5:
                    Run.run();
                    break;
                default:
                    System.out.println("Out of scope");
            }
        }
    }
}
