package FiguryGeometryczne;

public class Square {
    double a;

    public Square(double a) {
        this.a = a;
        System.out.println("Field = " + field());
        System.out.println("Round = " + round());
    }

    public double field() {
        return a * a;
    }

    public double round() {
        return 4 * a;
    }
}
