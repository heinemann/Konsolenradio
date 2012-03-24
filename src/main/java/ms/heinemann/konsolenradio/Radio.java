package ms.heinemann.konsolenradio;

import java.io.IOException;

import ms.heinemann.konsolenradio.Alt.Frequenz;

public class Radio {

	public static final Object wiedergabeThread = null;
	/**
	 * @author Adrian
	 * @version 0.2
	 * @param args
	 */

	static boolean Käfer = true;
	static int run = 0;
	static Frequenzen frequenz;

	public static void main(String[] args) throws InterruptedException {
		Dienst dienst = new Dienst();
		UI ui = new UI();
		Wiedergabe wiedergabe = new Wiedergabe();
		initialisiereProgramm();
		// Dienst.setEnergie(true);
		dienst.setEnergie(true);
		ui.Senderliste();
		// Dienst.leseKatalog();
		// Frequenz.leseFrequenzen();static
		Thread.currentThread();
		Plattform.SystemInfo();
		while (dienst.getEnergie()) {
			Thread wiedergabeThread = new Thread(new Wiedergabe());
			if (run == 0) {
				ui.Moinsen();
			} else {
				ui.Senderliste();
				ui.Mahlzeit();
			}

			try {
				ui.Senderwahl();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out
						.println("Es ist ein Fehler bei der Eingabe aufgetreten!");
				e.printStackTrace();
			}

			if (wiedergabeThread.isAlive()) {
				wiedergabeThread.join();
			}

			wiedergabeThread.start();

			System.out.println("Der Radiosender " + Frequenz.getSender()
					+ " wurde ausgewählt.");

			try {
				ui.Dienstwahl();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out
						.println("Es ist ein Fehler bei der Eingabe aufgetreten!");
				e.printStackTrace();
			}
			// Das schlafen legen des MainThreads gibt dem alten
			// WiedergabeThread die Möglichkeit sich zu beenden.

			Thread.sleep(5);
			if (!dienst.getEnergie()) {
				wiedergabeThread.join();
			}
			run++;
		}
		/**
		 * TODO Der Thrad kann nicht sampft gestoppt werden, da die //
		 * Weidergabe // einer Entlosschleife entspricht. TODO -Das stoppen des
		 * Thread // wurde in // die Klasse Frequenz ausgelagert-.
		 */
		// wiedergabeThread.stop();
		// wiedergabeThread.interrupt();
		System.out.println("Auf Wiederhören!");
	}

	private static void initialisiereProgramm() {

	}

}
