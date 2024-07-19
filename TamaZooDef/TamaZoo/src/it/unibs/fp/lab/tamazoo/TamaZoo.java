package it.unibs.fp.lab.tamazoo;

import java.util.ArrayList;

import it.unibs.fp.mylib.NumeriCasuali;
//ciao
public class TamaZoo {

	public ArrayList<Tamagotchi> getDaRimuovere() {
		return daRimuovere;
	}

	public void setDaRimuovere(ArrayList<Tamagotchi> daRimuovere) {
		this.daRimuovere = daRimuovere;
	}

	private static final int NUM_TIPI_TAMA = 2;

	private int numCasuale;

	private ArrayList<Tamagotchi> listaTama;
	private ArrayList<Tamagotchi> daRimuovere;

	/**
	 * Costruttore delle liste del TamaZoo
	 * 
	 * @author sahil, brembati, narducci
	 */

	public TamaZoo() {
		listaTama = new ArrayList<>();
		daRimuovere = new ArrayList<>();
	}

	/**
	 * Aggiunge i tamagotchi alla lista (creandoli in base al tipo)
	 * 
	 * @param numTama
	 * @author sahil, brembati, narducci
	 * @return void
	 */

	public void aggiungiTama(int numTama) {
		for (int i = 0; i < numTama; i++) {

			numCasuale = NumeriCasuali.estraiIntero(0, NUM_TIPI_TAMA);

			switch (numCasuale) {
			case 0:
				Tamagotchi tama = IoUtil.creaTamagotchi();
				listaTama.add(tama);
				break;
			case 1:
				TamaGordo tamaGordo = IoUtil.creaTamaGordo();
				listaTama.add(tamaGordo);
				break;
			case 2:
				TamaTriste tamaTriste = IoUtil.creaTamaTriste();
				listaTama.add(tamaTriste);
				break;
			}
		}
	}

	public ArrayList<Tamagotchi> getListaTama() {
		return listaTama;
	}

	public void setListaTama(ArrayList<Tamagotchi> listaTama) {
		this.listaTama = listaTama;
	}

	/**
	 * Controlla se tutti i tamagotchi nella lista sono morti
	 * 
	 * @author sahil, brembati, narducci
	 * @return boolean
	 */

}
