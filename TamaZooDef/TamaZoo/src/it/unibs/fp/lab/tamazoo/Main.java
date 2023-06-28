package it.unibs.fp.lab.tamazoo;

public class Main {

	private static final String SALUTO = "Buongiorno!";
	private static final String SALUTO_FINE = "Arrivederci!";

	public static void main(String[] args) {

		TamaZoo zoo = new TamaZoo();

		System.out.println(SALUTO + " ");

		IoUtil.quantiTamaInZoo(zoo);

		IoUtil.interazioneConUtente(zoo);

		System.out.println(SALUTO_FINE);

	}

}
