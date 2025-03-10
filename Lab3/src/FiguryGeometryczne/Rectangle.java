package FiguryGeometryczne;

public class Rectangle {
    double a, b;

    public double field() {
        return a * b;
    }

    public double round() {
        return (2 * a) + (2 * b);
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }
}
