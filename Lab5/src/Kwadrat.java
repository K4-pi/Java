class Kwadrat extends Prostokat{
	Kwadrat(int bok,String kolor){
		super(bok,bok,kolor);
	}

	IFigura ikwadrat = new IFigura() {
		@Override
		public float getPowierzchnia() {
			return 0;
		}

		@Override
		public boolean wPolu(Punkt p) {
			return false;
		}
	};
}