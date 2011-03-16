package ms.heinemann.konsolenradio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Radio {

	public static final Object wiedergabeThread = null;
	/**
	 * @param args
	 */

	static String Hörer;
	static String Betriebssystem;
	static boolean Käfer = false;
	static int run = 0;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// String datei = "/home/adrian/Musik/freude.mp3";
		Dienst.setEnergie(true);
		Dienst.leseKatalog();
		Frequenz.leseFrequenzen();
		Thread.currentThread();
		SystemInfo();
		while (Dienst.getEnergie()) {
			Thread wiedergabeThread = new Thread(new Wiedergabe());
			if (run == 0) {
				Moinsen();
				try {
					Senderwahl();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out
							.println("Es ist ein Fehler bei der Eingabe aufgetreten!");
					e.printStackTrace();
				}
				wiedergabeThread.start();

			} else {
				Wiedergabe.senderWechsel = false;
				Mahlzeit();
				try {
					Senderwahl();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out
							.println("Es ist ein Fehler bei der Eingabe aufgetreten!");
					e.printStackTrace();
				}
				wiedergabeThread.start();
			}

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
				wiedergabeThread.stop();
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
		clearScreen();
		System.out.println("Das Konsolenradio(JVM-Edition)\n");
		System.out.printf("Hallo %s, welchen Sender möchtest du hören?\n",
				Hörer);
	}

	public static void Mahlzeit() {
		clearScreen();
		System.out.println("Das Konsolenradio(JVM-Edition)\n");
		System.out.printf("Hallo %s, du hörst %s \n", Hörer,
				Frequenz.getSender());
		System.out.println("Zu welchem Sender möchtest du wechseln?");
	}

	public static void Senderwahl() throws IOException {

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println(Frequenz.getFrequenzen());
		System.out.printf("Treffe nun Deine Wahl: ");
		String eingabe = br.readLine();
		if (Radio.Käfer == true) {
			System.out.println("Du hast " + eingabe + " eingegeben.");
		}
		Frequenz.drehnippel(eingabe);
		if (Radio.Käfer == true) {
			System.out.println(Frequenz.getSender());
		}

	}

	public static void Dienstwahl() throws IOException {
		clearScreen();
		System.out.println("Das Konsolenradio(JVM-Edition)\n");
		System.out.printf("Hallo %s, du hörst %s.\n\n", Hörer,
				Frequenz.getSender());
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.printf("Folgende Dienste stehen ihnen zur Verfügung: %s",
				Dienst.getKatalog());
		System.out.print("Stets zu diensten: ");
		String eingabe = br.readLine();
		if (Radio.Käfer == true) {
			System.out.println("Du hast " + eingabe + " eingegeben.");
		}
		Dienst.pult(eingabe);
		if (Radio.Käfer == true) {
			System.out.println(Dienst.getDienst());
		}

	}

	/**
	 * Die Methode SystemInfo ermittelt den Benutzernamen des System und
	 * schreibt ihn groß.
	 */
	public static void SystemInfo() {
		// Benutzername
		String Benutzer = System.getProperty("user.name");
		char c = Benutzer.charAt(0);
		String s2 = Character.toString(Character.toUpperCase(c));
		String s3 = Benutzer.substring(1);
		setHörer(s2 + s3);
		// Betriebssystem Auslesen
		setBetriebssystem(System.getProperty("os.name"));
	}

	/**
	 * Die Methode clearScreen greift auf die Systemabhängige Konsolenbefehle
	 * zurück
	 */
	public static void clearScreen() {
		SystemInfo();
		String os = getBetriebssystem().toLowerCase();
		if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0
				|| os.indexOf("mac") >= 0 || os.indexOf("bsd") >= 0) {
			try {
				System.out.println("\u001b[H\u001b[2J");
				Runtime.getRuntime().exec("clear");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out
						.printf("Ein Unix-System (UNIX|LINUX|BSD|MAC-OS) wurde ermittel, ist aber wohl nicht richtig.");
				e.printStackTrace();
			}
		} else if (os.indexOf("win") >= 0) {
			try {
				System.out.println("\u001b[H\u001b[2J");
				Runtime.getRuntime().exec("cls");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out
						.printf("Windows Betriebssystem wurde ermittel, ist aber wohl nicht richtig.");
				e.printStackTrace();
			}
		} else {
			System.out.println("\u001b[H\u001b[2J");
			System.out
					.println("Schreibe an konsolenradio@heinemann.ms damit dein Betriebssystem unterstützt wird.");
		}
	}

	/**
	 * Hier folgen die Getter und Setter
	 */
	public static void setHörer(String hörer) {
		Hörer = hörer;
	}

	public static String getHörer() {
		return Hörer;
	}

	public static void setBetriebssystem(String betriebssystem) {
		Betriebssystem = betriebssystem;
	}

	public static String getBetriebssystem() {
		return Betriebssystem;
	}

}
