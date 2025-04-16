class Main
{
	public static void main(String[] arg)
	{
		Prostokat maly_punkt = new Prostokat(3, 6, "czerwony");
		Trojkat rownoboczny = new Trojkat(7, 4, "bia�y");
		Kwadrat kwadrat = new Kwadrat(12, "czarny");
		Punkt p = new Punkt(4, 4);

		System.out.println(maly_punkt.opis());
		System.out.println(rownoboczny.opis());
		System.out.println(kwadrat.getKolor());

		Figura[] tablicaFigur = new Figura[10];

		tablicaFigur[0] = maly_punkt;
		tablicaFigur[1] = rownoboczny;
		tablicaFigur[2] = kwadrat;
		tablicaFigur[3] = new Kwadrat(4, "pomarańczowy");
		tablicaFigur[4] = new Trojkat(2, 2, "biały");
		tablicaFigur[5] = new Prostokat(5, 15, "kolorowy");
		tablicaFigur[6] = new Kwadrat(5, "niebieskie");
		tablicaFigur[7] = new Kwadrat(1, "turkusowy");
		tablicaFigur[9] = new Kwadrat(3, "błękitny");
		tablicaFigur[8] = new Kwadrat(8, "zielony");
		System.out.println("---------------------");

		for (Figura f : tablicaFigur) {
			System.out.println(f.opis());
		}
		System.out.println("---------------------");
		for (Figura f : tablicaFigur) {
			f.skaluj(3);
			System.out.println(f.opis());
		}
		System.out.println("---------------------");
		IFigura[] tablicaIFigur = new IFigura[3];
		tablicaIFigur[0] = new Kwadrat(2, "czerwony");
		tablicaIFigur[1] = maly_punkt;
		tablicaIFigur[2] = kwadrat;

		System.out.println("---------------------");
		Okrag o = new Okrag(4, "niebieksi");
		o.getPunkty();
		o.przesun(4, 2);
		System.out.println("---");
		o.getPunkty();
	}
}