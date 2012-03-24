/**
 * 
 */
package ms.heinemann.konsolenradio;

import java.io.IOException;

/**
 * @author adrian Die Dienstklasse kann später Aufnahmefunktionen oder oder
 *         implementieren oder aufrufen.
 */
public class Dienst {

	private static String Dienst;
	private static Boolean Energie = true;
	private static String Katalog;

	// UI uid = new UI();

	// ui = Radio.ui;

	public void pult(String eingabe) {
		if (eingabe.equals("Dienst0") | eingabe.equals(("Aus"))
				| eingabe.equals("0")) {
			Dienst0();
		} else if (eingabe.equals("Dienst1") | eingabe.equals(("Pause"))
				| eingabe.equals("1")) {
			Dienst1();
		} else if (eingabe.equals("")) {
			System.out.println("Es geht zurück zur Senderauswahl");
			Wiedergabe.senderWechsel = true;
		} else {
			System.out.printf("Dienst %s ist fehlgeschlagen", getDienst());
			try {
				// uid.Dienstwahl();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out
						.println("Es ist ein Fehler bei der Eingabe aufgetreten!");
				e.printStackTrace();
			}
		}
	}

	public Boolean Dienst0() {
		Wiedergabe.senderWechsel = true;
		setEnergie(false);
		return getEnergie();
	}

	public String Dienst1() {
		setDienst("Pause");
		Wiedergabe.senderWechsel = true;
		if (Radio.Käfer == true) {
			System.out.println("Dienst beendet Thread");
		}
		Wiedergabe.senderWechsel = false;
		try {
			uid.Dienstwahl();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out
					.println("Es ist ein Fehler bei der Eingabe aufgetreten!");
			e.printStackTrace();
		}
		return getDienst();
	}

	/**
	 * Hier sind die Getter und Setter
	 * 
	 * @param dienst
	 */
	public void setDienst(String dienst) {
		Dienst = dienst;
	}

	public String getDienst() {
		return Dienst;
	}

	public void setEnergie(Boolean energie) {
		Energie = energie;
	}

	public Boolean getEnergie() {
		return Energie;
	}

	public void leseKatalog() {
		Katalog = "\n 0 - Aus   : Schaltet das Radio aus \n 1 - Pause : Beendet die Wiedergabe und verbleibt in der Dienstauswahl \n Enter     : Es geht zur Senderauswahl \n";

	}

	public String getKatalog() {
		leseKatalog();
		return Katalog;
	}
}
