package it.unibs.fp.lab.tamazoo;

public class TamaGordo extends Tamagotchi {
	private static final int BISCOTTI_ECCESSO = 2;
	private static final int FATTORE_MOLT_SAZIETA = 2;
	private static final int MIN_SAZIETA_TRISTEZZA = 30;
	private static final int MAX_AFFETTO = 100;
	private static final int FATTORE_CAREZZE = 2;
	private static final double FATTORE_BISCOTTI_ECCESSO = 0.05;
	private static final double FATTORE_BISCOTTI = 0.1;

	private int counterBiscotti = 0;

	public TamaGordo(String _nome, double _sazieta) {
		super(_nome, MAX_AFFETTO, _sazieta);
		this.tipo = "gordo";
	}

	/**
	 * Controlla se il tamagordo è morto (ossia se ha sazietà <=0)
	 * 
	 * @author sahil, brembati, narducci
	 * @return boolean
	 */

	public boolean sonoMorto() {
		if (getSazieta() <= 0)
			return true;
		else
			return false;
	}

	/**
	 * Controlla se il tamagordo è triste (ossia se ha sazietà <30)
	 * 
	 * @author sahil, brembati, narducci
	 * @return boolean
	 */

	public boolean sonoTriste() {
		if (getSazieta() < MIN_SAZIETA_TRISTEZZA)
			return true;
		else
			return false;
	}

	/**
	 * Da carezze al tamaGordo influendo sulla sua sazietà
	 * 
	 * @author sahil, brembati, narducci
	 * @return void
	 */

	public void riceviCarezze(int carezze) {
		setSazieta(Math.max(0, getSazieta() - FATTORE_MOLT_SAZIETA * ((double) carezze / FATTORE_CAREZZE)));
	}

	/**
	 * Da biscotti al tamaGordo influendo sulla sua sazietà
	 * 
	 * @author sahil, brembati, narducci
	 * @return void
	 */

	public void riceviBiscotti(int biscotti) {
		if (counterBiscotti > BISCOTTI_ECCESSO) {
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
