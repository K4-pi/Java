package FiguryGeometryczne;

public class Circle {
    double r;

    public double field() {
        return Math.PI * r * r;
    }

    public double round() {
        return 2 * r * Math.PI;
    }

    public Circle(double r) {
        this.r = r;
        System.out.println("Field = " + field());
        System.out.println("Round = " + round());
    }
}
