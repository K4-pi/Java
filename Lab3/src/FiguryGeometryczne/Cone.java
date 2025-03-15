package FiguryGeometryczne;

public class Cone {
    double r;
    double h;

    private double capacity() {
        return 1.0/3.0 * (2 * Math.PI * r) * h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void display() {
        System.out.println("Capacity: " + this.capacity());
    }
}
