package ms.heinemann.konsolenradio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		Dienst.setEnergie(true);
		Senderliste();
		// Dienst.leseKatalog();
		// Frequenz.leseFrequenzen();
		Thread.currentThread();
		Plattform.SystemInfo();
		while (Dienst.getEnergie()) {
			Thread wiedergabeThread = new Thread(new Wiedergabe());
			if (run == 0) {
				Moinsen();
			} else {
				Senderliste();
				Mahlzeit();
			}

			try {
				Senderwahl();
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
				Dienstwahl();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out
						.println("Es ist ein Fehler bei der Eingabe aufgetreten!");
				e.printStackTrace();
			}
			// Das schlafen legen des MainThreads gibt dem alten
			// WiedergabeThread die Möglichkeit sich zu beenden.

			Thread.sleep(5);
			if (!Dienst.getEnergie()) {
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

	public static void Moinsen() {
		Plattform.clearScreen();
		System.out.println("Das Konsolenradio(JVM-Edition)\n");
		System.out.printf("Hallo %s, welchen Sender möchtest du hören?\n",
				Plattform.Hörer);
	}

	public static void Mahlzeit() {
		Plattform.clearScreen();
		System.out.println("Das Konsolenradio(JVM-Edition)\n");
		System.out.printf("Hallo %s, du hörst %s \n", Plattform.Hörer,
				frequenz.getName());
		System.out.println("Zu welchem Sender möchtest du wechseln?");
	}

	public static void Senderwahl() throws IOException {

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println(Frequenz.getFrequenzen());
		System.out
				.printf("Treffe nun Deine Wahl und gebe die Sendernummer ein: ");
		String eingabe = br.readLine();
		if (Radio.Käfer == true) {
			System.out.println("Du hast " + eingabe + " eingegeben.");
		}

		frequenz = Frequenzen.suchMittelsNummer(eingabe);

		if (frequenz.isStream()) {
			frequenz.getUrl();
		}

		if (Radio.Käfer == true) {
			System.out.println(frequenz.name());
			// System.out.println(Frequenz.getSender());
		}

	}

	public static void Senderliste() {
		int index = 1;
		for (final Frequenzen frequenz : Frequenzen.values()) {
			final StringBuilder frequenzAusgabe = new StringBuilder();

			frequenzAusgabe.append(index++);
			frequenzAusgabe.append(":"); //$NON-NLS-1$
			frequenzAusgabe.append("\t"); //$NON-NLS-1$
			frequenzAusgabe.append(frequenz.getName());
			frequenzAusgabe.append("\t("); //$NON-NLS-1$
			frequenzAusgabe.append(frequenz.getUrl());
			frequenzAusgabe.append(")"); //$NON-NLS-1$
			frequenzAusgabe.append(" im Format: "); //$NON-NLS-1$
			frequenzAusgabe.append(frequenz.getFormat());

			System.out.println(frequenzAusgabe.toString());
		}
	}

	public static void Dienstwahl() throws IOException {
		Plattform.clearScreen();
		System.out.println("Das Konsolenradio(JVM-Edition)\n");
		System.out.printf("Hallo %s, du hörst %s.\n\n", Plattform.Hörer,
				frequenz.getName());
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.printf("Folgende Dienste stehen ihnen zur Verfügung: %s",
				Dienst.getKatalog());
		System.out.print("\n \n Stets zu diensten: ");
		String eingabe = br.readLine();
		if (Radio.Käfer == true) {
			System.out.println("Du hast " + eingabe + " eingegeben.");
		}
		Dienst.pult(eingabe);
		if (Radio.Käfer == true) {
			System.out.println(Dienst.getDienst());
		}

	}

}
