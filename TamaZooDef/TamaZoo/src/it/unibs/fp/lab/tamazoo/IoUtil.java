package it.unibs.fp.lab.tamazoo;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.fp.mylib.NumeriCasuali;

public class IoUtil {

	private static final int MASSIMO_AFFETTO_PARTENZA_TAMABASE = 90;
	private static final int MASSIMO_SAZIETA_PARTENZA_TAMABASE = 90;
	private static final int MINIMO_AFFETTO_PARTENZA_TAMABASE = 10;
	private static final int MINIMO_SAZIETA_PARTENZA_TAMABASE = 10;
	private static final int MAX_SAZIETA = 100;
	private static final String NOME = "inserire il nome del tamagotchi: ";
	private static final String MSG_UN_MORTO = "  morto, condoglianze.";
	private static final int NUM_MAX_BISCOTTI = 5;
	private static final int NUM_MIN_BISCOTTI = 1;
	private static final int NUM_MAX_CAREZZE = 5;
	private static final int NUM_MIN_CAREZZE = 1;
	private static final int NUM_MAX_TAMA_IN_LISTA = 5;
	private static final int NUM_MIN_TAMA_IN_LISTA = 1;
	private static final String MSG_DI_DEFAULT = "Seleziona un'opzione valida!";
	private static final String RICHIESTA_NUM_TAMA = "Quanti tamagotchi vuoi avere nello zoo? ";
	private static final String[] VOCI_MENU = new String[] { "Dai carezze", "Dai biscotti" };
	private static final String NOME_MENU = "Menu Tamagotchi";
	private static final String MESSAGGIO_DI_MORTE = "I tamagotchi sono tutti morti, condoglianze";
	private static int numTama;

	/**
	 * costruttore tamagotchi base
	 * 
	 * @author sahil, brembati, narducci
	 * @return Tamagotchi
	 */
	public static Tamagotchi creaTamagotchi() {
		String nome = InputDati.leggiStringa(NOME);
		int sazieta = NumeriCasuali.estraiIntero(MINIMO_SAZIETA_PARTENZA_TAMABASE, MASSIMO_SAZIETA_PARTENZA_TAMABASE);
		int affetto = NumeriCasuali.estraiIntero(MINIMO_AFFETTO_PARTENZA_TAMABASE, MASSIMO_AFFETTO_PARTENZA_TAMABASE);
		return new Tamagotchi(nome, affetto, sazieta);
	}

	/**
	 * costruttore tamaGordo
	 * 
	 * @author sahil, brembati, narducci
	 * @return TamaGordo
	 */
	public static TamaGordo creaTamaGordo() {
		String nome = InputDati.leggiStringa(NOME);
		int sazieta = NumeriCasuali.estraiIntero(0, MAX_SAZIETA);
		return new TamaGordo(nome, sazieta);
	}

	/**
	 * costruttore tamaTriste
	 * 
	 * @author sahil, brembati, narducci
	 * @return TamaTriste
	 */
	public static TamaTriste creaTamaTriste() {
		String nome = InputDati.leggiStringa(NOME);
		int sazieta = NumeriCasuali.estraiIntero(0, MAX_SAZIETA);
		return new TamaTriste(nome, sazieta);
	}

	/**
	 * Il metodo, come suggerisce il nome, stampa le caratteristiche del tamagotchi.
	 * 
	 * @author sahil, brembati, narducci
	 * @param tama cioè il tamagotchi
	 * @return void
	 */
	public static void stampaCaratteristicheTama(Tamagotchi tama) {
		System.out.println(tama.toString());
	}

	/**
	 * Il metodo, come suggerisce il nome, interagisce con l'utente domandandogli
	 * quanti tamagotchi di tipi diversi desideri inserire nello zoo. Il metodo
	 * procede poi ad inserire i vari tamagotchi nello zoo e a stamparne le
	 * caratteristiche a video.
	 * 
	 * @author sahil, brembati, narducci
	 * @param zoo
	 * @return void
	 */
	public static void quantiTamaInZoo(TamaZoo zoo) {
		numTama = InputDati.leggiIntero(RICHIESTA_NUM_TAMA, NUM_MIN_TAMA_IN_LISTA, NUM_MAX_TAMA_IN_LISTA);

		zoo.aggiungiTama(numTama);

		for (Tamagotchi tama : zoo.getListaTama()) {
			IoUtil.stampaCaratteristicheTama(tama);
		}
	}

	/**
	 * Il metodo va ad interagire con l'utente come suggerisce il nome. In
	 * particolare, dopo aver creato un oggetto di tipo MyMenu, l'utente viene
	 * "inserito" in un ciclo do-while fintantoche o tutti i tamagotchi non muoiono
	 * oppure fintantoche l'utente non decide di uscire dal programma digitando 0
	 * (il tutto implementato in uno switch). La condizione di uscita è quando
	 * finito assume il valore booleano true
	 * 
	 * @author sahil, brembati, narducci
	 * @param tama, ovvero il riferimento ad un oggetto di tipo Tamagotchi
	 * @return void
	 */

	public static void interazioneConUtente(TamaZoo zoo) {
		MyMenu menu = new MyMenu(NOME_MENU, VOCI_MENU);

		boolean finito = false;
		do {
			int scelta = menu.scegli();

			for (int i = 0; i < zoo.getListaTama().size(); i++) {

				switch (scelta) {
				case 1:
					daiCarezze(zoo, i);
					break;

				case 2:
					daiBiscotti(zoo, i);
					break;

				case 0:
					finito = true;
					IoUtil.stampaCaratteristicheTama(zoo.getListaTama().get(i));
					break;
				default:
					System.out.println(MSG_DI_DEFAULT);
				}

				i = stampaStatoTama(zoo, i);

				rimuoviTamaMorti(zoo);

				if (zoo.getListaTama().size() == 0) {
					finito = true;
					System.out.println(MESSAGGIO_DI_MORTE);
				}
			}

		} while (finito == false);
	}

	/**
	 * Il metodo da carezze all'i-esimo tamagotchi presente nello zoo.
	 * 
	 * @author sahil, brembati, narducci
	 * @param zoo, i cioè l'indice del tamagotchi
	 * @return void
	 */
	private static void daiCarezze(TamaZoo zoo, int i) {
		zoo.getListaTama().get(i).riceviCarezze(NumeriCasuali.estraiIntero(NUM_MIN_CAREZZE, NUM_MAX_CAREZZE));
	}

	/**
	 * Il metodo da biscotti all'i-esimo tamagotchi presente nello zoo.
	 * 
	 * @author sahil, brembati, narducci
	 * @param zoo, i cioè l'indice del tamagotchi
	 * @return void
	 */
	private static void daiBiscotti(TamaZoo zoo, int i) {
		zoo.getListaTama().get(i).riceviBiscotti(NumeriCasuali.estraiIntero(NUM_MIN_BISCOTTI, NUM_MAX_BISCOTTI));
	}

	/**
	 * Il metodo, come suggerisce il nome, verifica e stamapa lo stato corrente
	 * dell'i-esimo tamagotchi presente nello zoo.
	 * 
	 * @author sahil, brembati, narducci
	 * @param zoo, i cioè l'indice del tamagotchi
	 * @return i
	 */
	private static int stampaStatoTama(TamaZoo zoo, int i) {
		if (zoo.getListaTama().get(i).sonoMorto()) {
			System.out.println("\n" + zoo.getListaTama().get(i).getNome() + MSG_UN_MORTO);
			zoo.getDaRimuovere().add(zoo.getListaTama().get(i));
			i--;
		} else {
			IoUtil.stampaCaratteristicheTama(zoo.getListaTama().get(i));
		}
		return i;
	}

	/**
	 * Il metodo, come suggerisce il nome, rimuove i tamagotchi morti presente nello
	 * zoo.
	 * 
	 * @author sahil, brembati, narducci
	 * @param zoo
	 * @return void
	 */

	private static void rimuoviTamaMorti(TamaZoo zoo) {
		zoo.getListaTama().removeAll(zoo.getDaRimuovere());
	}

}