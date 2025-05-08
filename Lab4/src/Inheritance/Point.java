package Inheritance;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        x = 0.0;
        y = 0.0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    private void zeruj() {
        x = 0.0;
        y = 0.0;
    }

    public String opis() {
        return "Punkt o współzędnych x = " + x + " , y = " + y;
    }

    public void przesun(int x_, int y_) {
        x += x_;
        y += y_;
    }

}
