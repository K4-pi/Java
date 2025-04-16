class Trojkat extends Figura{
	int wys=0;
	int podst=0;
	Trojkat(int wys,int podst,String kolor){
		this.wys = wys;
		this.podst = podst;
		super.kolor = kolor;
	}

	@Override
	public String opis(){
		return "Trojkat o wymairach: "+wys+" x "+podst;
	}

	@Override
	public void skaluj(int skala) {
		wys *= skala;
		podst *= skala;
	}

}