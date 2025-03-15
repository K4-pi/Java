package Building;

public class Building {
    final int current_year = 2025;
    String name;
    int year;
    int floors;

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
