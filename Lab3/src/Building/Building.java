package Building;

import java.time.LocalDate;

public class Building {
    private final int current_year = LocalDate.now().getYear();
    private String name;
    private int year;
    private int floors;

    @Override
    public String toString() {
        return "Building name: " + name +
                "\nYear: " + year +
                "\nFloors: " + floors;
    }

    public int HowManyYears() {
        return current_year - year;
    }

    public Building(String name, int year, int floors) {
        this.name = name;
        this.year = year;
        this.floors = floors;
    }

}
