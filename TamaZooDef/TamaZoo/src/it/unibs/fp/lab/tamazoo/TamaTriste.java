package it.unibs.fp.lab.tamazoo;

public class TamaTriste extends Tamagotchi {

	private static final int MAX_SAZIETA = 100;
	private static final double FATTORE_CAREZZE = 2;
	private static final double FATTORE_BISCOTTI_ECCESSO = 0.05;
	private static final double FATTORE_BISCOTTI = 0.1;

	private int counterBiscotti = 0;

	public TamaTriste(String _nome, double _sazieta) {
		super(_nome, 0, _sazieta);
		this.tipo = "triste";
		// TODO Auto-generated constructor stub
	}

	/**
	 * setta il tamaTriste triste
	 * 
	 * @author sahil, brembati, narducci
	 * @return boolean
	 */

	public boolean sonoTriste() {
		return true;
	}

	/**
	 * Controlla se il tamagordo è morto (ossia se ha sazietà <=0 o >=100)
	 * 
	 * @author sahil, brembati, narducci
	 * @return boolean
	 */

	public boolean sonoMorto() {
		if (getSazieta() <= 0 || getSazieta() >= MAX_SAZIETA)
			return true;
		else
			return false;
	}

	/**
	 * Da carezze al tamaTriste influendo sulla sua sazietà
	 * 
	 * @author sahil, brembati, narducci
	 * @return void
	 */

	public void riceviCarezze(int carezze) {
		setSazieta(Math.max(0, getSazieta() - (double) carezze / FATTORE_CAREZZE));
	}

	/**
	 * Da biscotti al tamaTriste influendo sulla sua sazietà
	 * 
	 * @author sahil, brembati, narducci
	 * @return void
	 */

	public void riceviBiscotti(int biscotti) {
		if (counterBiscotti > 2) {
			for (int i = 0; i < biscotti; i++) {
				setSazieta(Math.min(100, getSazieta() + getSazieta() * FATTORE_BISCOTTI_ECCESSO));
			}
		}

		else {
			for (int i = 0; i < biscotti; i++) {
				setSazieta(Math.min(100, getSazieta() + getSazieta() * FATTORE_BISCOTTI));
			}
		}
		counterBiscotti++;
	}

}
