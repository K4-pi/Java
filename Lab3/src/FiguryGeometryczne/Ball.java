package FiguryGeometryczne;

public class Ball {
    double r;

    public double capacity() {
        return 4.0/3.0 * Math.PI * (r * r * r);
    }

    public double round() {
        return 2 * Math.PI * r;
    }

    public Ball(double r) {
        this.r = r;
        System.out.println("Capacity: " + this.capacity());
        System.out.println("Round: " + this.round());
    }
}
