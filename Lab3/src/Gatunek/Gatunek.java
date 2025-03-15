package Gatunek;

public class Gatunek {
    /*Stwórz program w którym będzie utworzonych kilka obiektów typu Gatunek. Klasa Gatunek
    powinna zawierać następujące pola: nazwę rodzaju, nazwę gatunkową, liczbę chromosomów 2n,
    podstawową liczbę chromosomów x, opis oraz metody: podającą pełną nazwę (Rodzaj + gatunek),
    podającą haploidalną liczbę chromosomów n, wypisującą wszystkie dane, klonującą obiekt –
    metoda powinna zwracać odnośnik do nowego obiektu typu Gatunek o wartościach pól takich
    samych jak w obiekcie, w którym została wywołana. W programie powinny być użyte wszystkie
    metody*/

    private String rodzaj;
    private String gatunek;
    private int chromosomy2n;
    private int chromosomyX;
    private String opis;

    public Gatunek(String rodzaj, String gatunek, int chromosomy2n, int chromosomyX, String opis) {
        this.rodzaj = rodzaj;
        this.gatunek = gatunek;
        this.chromosomy2n = chromosomy2n;
        this.chromosomyX = chromosomyX;
        this.opis = opis;
    }

    public String pelnaNazwa() {
        return rodzaj + " " + gatunek;
    }

    public int haploidalnaLiczbaChromosomow() {
        return chromosomy2n / 2;
    }

    public void wypiszDane() {
        System.out.println("Pełna nazwa: " + pelnaNazwa());
        System.out.println("Liczba chromosomów (2n): " + chromosomy2n);
        System.out.println("Podstawowa liczba chromosomów (x): " + chromosomyX);
        System.out.println("Haploidalna liczba chromosomów (n): " + haploidalnaLiczbaChromosomow());
        System.out.println("Opis: " + opis + "\n");
    }

    /*@Override
    public Gatunek clone() {
        return (Gatunek) super.clone();
    }*/
}
