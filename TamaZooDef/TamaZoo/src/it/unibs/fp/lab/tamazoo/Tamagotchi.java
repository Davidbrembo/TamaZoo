package it.unibs.fp.lab.tamazoo;

public class Tamagotchi {

	private static final int MIN_AFFETTO_TRISTEZZA = 30;
	private static final int MAX_SAZIETA_TRISTEZZA = 90;
	private static final int MIN_SAZIETA_TRISTEZZA = 30;
	private static final int MAX_SAZIETA = 100;
	private static final int BISCOTTI_ECCESSO = 2;
	private static final int CAREZZE_ECCESSO = 2;
	private static final double FATTORE_GRADO_BENESSERE = 2.0;
	private static final double PESO_BENESSERE_AFFETTO = 1.3;
	private static final double PESO_BENESSERE_SAZIETA = 0.7;

	private static final double FATTORE_BISCOTTI_ECCESSO = 0.05;
	private static final double FATTORE_CAREZZE_ECCESSO = 0.5;

	private static final double NUMERO_PER_ARROTONDARE = 100.0;

	private static final int FATTORE_BISCOTTI_AFFETTO = 4;
	private static final double FATTORE_BISCOTTI = 0.1;
	private static final int FATTORE_CAREZZE = 2;

	private String nome;
	private double sazieta, affetto;
	protected String tipo;

	private int counterBiscotti = 0;
	private int counterCarezze = 0;

	public Tamagotchi(String _nome, double _affetto, double _sazieta) {
		this.nome = _nome;
		this.sazieta = _sazieta;
		this.affetto = _affetto;
		this.tipo = "base";
	}

	/**
	 * Questo metodo guarda attraverso la variabile counterCarezze quante volte è
	 * stato chiamato il metodo stesso, se superiore ad una certa soglia, allora le
	 * carezzze avranno un effetto minore. Inoltre il metodo va a reimpostare il
	 * counterBiscotti, per far si che il conteggio riparta da 0 per il metodo
	 * riceviBiscotti perchè riceviCarezzze è stato chiamato. Per ultima cosa
	 * diminuisce sazieta a seconda di quante carezze ha ricevuto.
	 * 
	 * @author sahil
	 * @param carezze, cioè il numero di carezze date al tamagotchi
	 * @return void
	 */

	public void riceviCarezze(int carezze) {
		if (counterCarezze > CAREZZE_ECCESSO)
			affetto = affetto + FATTORE_CAREZZE_ECCESSO * carezze;
		else
			affetto = affetto + carezze;
		if (affetto > 100)
			affetto = 100;

		counterCarezze = counterCarezze + 1;
		counterBiscotti = 0;
		sazieta = Math.max(0, sazieta - (double) carezze / FATTORE_CAREZZE);
	}

	/**
	 * Questo metodo guarda attraverso la variabile counterBiscotti quante volte è
	 * stato chiamato il metodo stesso, se superiore ad una certa soglia, allora i
	 * biscotti avranno un effetto minore. Inoltre il metodo va a reimpostare il
	 * counterCarezze, per far si che il conteggio riparta da 0 per il metodo
	 * riceviCarezze perchè riceviBiscotti è stato chiamato. Per ultima cosa
	 * diminuisce affetto a seconda di quanti biscotti ha ricevuto.
	 * 
	 * @author sahil
	 * @param carezze, cioè il numero di carezze date al tamagotchi
	 * @return void
	 */

	public void riceviBiscotti(int biscotti) {
		if (counterBiscotti > BISCOTTI_ECCESSO) {
			for (int i = 0; i < biscotti; i++) {
				sazieta = Math.min(100, sazieta + sazieta * FATTORE_BISCOTTI_ECCESSO);
			}
		}

		else {
			for (int i = 0; i < biscotti; i++) {
				sazieta = Math.min(100, sazieta + sazieta * FATTORE_BISCOTTI);
			}
		}

		affetto = Math.max(0, affetto - (double) biscotti / FATTORE_BISCOTTI_AFFETTO);
		if (affetto > 100)
			affetto = 100;
		counterBiscotti = counterBiscotti + 1;
		counterCarezze = 0;
	}

	public String getNome() {
		return nome;
	}

	public double getSazieta() {
		return sazieta;
	}

	public double getAffetto() {
		return affetto;
	}

	public void setSazieta(double sazieta) {
		this.sazieta = sazieta;
	}

	public void setAffetto(double affetto) {
		this.affetto = affetto;
	}

	/**
	 * Controlla se il tamagotchi è morto (ossia se ha sazietà <=0, >=100, oppure
	 * affetto<=0)
	 * 
	 * @author sahil, brembati, narducci
	 * @return boolean
	 */

	public boolean sonoMorto() {
		if (sazieta <= 0 || affetto <= 0 || sazieta >= MAX_SAZIETA)
			return true;
		else
			return false;
	}

	/**
	 * Controlla se il tamagotchi è triste (ossia se ha sazietà <30, >90, oppure
	 * affetto<30)
	 * 
	 * @author sahil, brembati, narducci
	 * @return boolean
	 */

	public boolean sonoTriste() {
		if (sazieta < MIN_SAZIETA_TRISTEZZA || affetto < MIN_AFFETTO_TRISTEZZA || sazieta > MAX_SAZIETA_TRISTEZZA)
			return true;
		else
			return false;
	}

	/**
	 * calcola il benessere del tamagotchi basandosi su sazieta ed affetto
	 * 
	 * @author sahil, brembati, narducci
	 * @return double
	 */

	public double gradoBenessere() {
		return Math
				.round(((sazieta * PESO_BENESSERE_SAZIETA + affetto * PESO_BENESSERE_AFFETTO) / FATTORE_GRADO_BENESSERE)
						* NUMERO_PER_ARROTONDARE)
				/ NUMERO_PER_ARROTONDARE;
	}

	public String toString() {
		StringBuffer daStampare = new StringBuffer();
		daStampare.append("\nNome: " + nome);
		daStampare.append("\nTipo: " + tipo);
		daStampare.append("\nSazieta: " + Math.round(sazieta * NUMERO_PER_ARROTONDARE) / NUMERO_PER_ARROTONDARE + "\n");
		daStampare.append("Affetto: " + Math.round(affetto * NUMERO_PER_ARROTONDARE) / NUMERO_PER_ARROTONDARE + "\n");
		if (sonoTriste())
			daStampare.append("Sono triste, grado di benessere: basso");
		else
			daStampare.append("Grado benessere: " + gradoBenessere() + "/100\n");

		return daStampare.toString();
	}

}
