import Inheritance.Okrag;
import Inheritance.Point;

public class Main {
    public static void main(String[] args) {

        /*Point p1 = new Point(2.10, 5.5);
        Point p2 = new Point(9.99, 1.35);
        Point p3 = new Point(1.0, 1.0);

        p1.opis();
        p1.przesun(2, 10);
        p1.opis();

        p2.opis();
        p2.przesun(4, 3);
        p2.opis();

        p3.opis();
        p3.przesun(5, 1);
        p3.opis();*/

        Okrag k = new Okrag(new Point(5.0, 3.0), 5.0);
        System.out.println(k);

        Point wOkragu = new Point(5.0, 3.0);

        if(k.wSrodku(wOkragu)) {
            System.out.println("Jest w okręgu");
        }
        else {
            System.out.println("Nie jest");
        }

    }

}
