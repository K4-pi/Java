package FiguryGeometryczne;

public class Square {
    double a;

    public Square(double a) {
        this.a = a;
    }

    public double field() {
        return a * a;
    }

    public double round() {
        return 4 * a;
    }
}
