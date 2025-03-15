package FiguryGeometryczne;

public class Cube {
    double a;

    public double capacity() {
        return a * a * a;
    }

    public double round() {
        return a * 12;
    }

    public Cube(double a) {
        this.a = a;
        System.out.println("Capacity: " + this.capacity());
        System.out.println("Round: " + this.round());
    }

}
