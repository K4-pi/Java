public class Okrag extends Figura implements RuchFigury{
    int x = 0;
    int y = 0;

    private float r;
    private String kolor;

    public Okrag(float r, String kolor) {
        this.r = r;
        this.kolor = kolor;
    }

    @Override
    String opis() {
        return "";
    }

    @Override
    void skaluj(int skala) {
        r *= skala;
    }

    @Override
    public float getPowierzchnia() {
        return r * r * (float)Math.PI;
    }

    @Override
    public boolean wPolu(Punkt p) {
        return false;
    }

    @Override
    public void przesun(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void getPunkty() {
        System.out.println("x: " + this.x + "\ny: " + this.y);
    }
}
