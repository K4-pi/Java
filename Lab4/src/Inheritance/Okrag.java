package Inheritance;

public class Okrag extends Figura{

    double r;
    double powierzchnia;

//    public Kolo(Point punkt) {
//        super(punkt);
//    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getPowierzchnia() {
        return powierzchnia;
    }

    public boolean wSrodku(Point point) {
        return Math.pow(this.getPunkt().getX() - point.getX(),2)
                - Math.pow(this.getPunkt().getY() - point.getY(), 2) < Math.pow(r, 2);
    }

    @Override
    public String toString() {
        return "x = " + this.getPunkt().getX() +
                "\ny = " + this.getPunkt().getY() +
                "\npromien = " + this.getR() +
                "\npowierzchnia = " + this.getPowierzchnia();
    }

    public Okrag(Point punkt, double r) {
        super(punkt);
        this.r = r;
        this.powierzchnia = Math.PI * Math.pow(r, 2);
    }
}
