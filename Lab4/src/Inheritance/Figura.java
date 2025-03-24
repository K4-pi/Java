package Inheritance;

public class Figura {
	
	//pola metody i kontruktory
	Point punkt;
	String kolor = "bialy";


	Figura(String kolor){
		this.kolor=kolor;
	}
	public Figura(Point punkt){
		this.punkt=punkt;
	}
	String getKolor(){
		return kolor;
	}
	String opis(){
		return "Klasa Figura. Kolor obiektu: "+kolor;
	}

	public Point getPunkt() {
		return punkt;
	}

}
