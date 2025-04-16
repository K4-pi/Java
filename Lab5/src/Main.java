class Main
{
	public static void main(String[] arg)
	{
		Prostokat maly_punkt = new Prostokat(3, 6, "czerwony");
		Trojkat rownoboczny = new Trojkat(7, 4, "bia�y");

		System.out.println(maly_punkt.opis());
		System.out.println(rownoboczny.opis());


		
	}
}