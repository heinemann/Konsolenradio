package ms.heinemann.konsolenradio;

import java.io.IOException;

public class Plattform {

	/**
	 * Varialblen, die vom Betriebssystem ausgelesen werden
	 */
	static String Hörer; // Name des angemeldeten Benutzers.
	static String Betriebssystem; // Name des Betriebssystems.

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
				String clear = "clear";
				Runtime.getRuntime().exec(clear);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out
						.printf("Ein Unix-System (UNIX|LINUX|BSD|MAC-OS) wurde ermittel, ist aber wohl nicht richtig.");
				e.printStackTrace();
			}
		} else if (os.indexOf("win") >= 0) {
			try {
				String cls = "cls";
				Runtime.getRuntime().exec(cls);
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
