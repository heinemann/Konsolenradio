/**
 * 
 */
package ms.heinemann.konsolenradio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ms.heinemann.konsolenradio.Alt.Frequenz;

/**
 * @author adrian
 * 
 */
public class UI {

	Dienst dienst = new Dienst();
	Frequenzen frequenz;

	public void Moinsen() {
		Plattform.clearScreen();
		System.out.println("Das Konsolenradio(JVM-Edition)\n");
		System.out.printf("Hallo %s, welchen Sender möchtest du hören?\n",
				Plattform.Hörer);
	}

	public void Mahlzeit() {
		Plattform.clearScreen();
		System.out.println("Das Konsolenradio(JVM-Edition)\n");
		System.out.printf("Hallo %s, du hörst %s \n", Plattform.Hörer,
				frequenz.getName());
		System.out.println("Zu welchem Sender möchtest du wechseln?");
	}

	public void Senderwahl() throws IOException {

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

	public void Senderliste() {
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

	public void Dienstwahl() throws IOException {
		Plattform.clearScreen();
		System.out.println("Das Konsolenradio(JVM-Edition)\n");
		System.out.printf("Hallo %s, du hörst %s.\n\n", Plattform.Hörer,
				frequenz.getName());
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.printf("Folgende Dienste stehen ihnen zur Verfügung: %s",
				dienst.getKatalog());
		System.out.print("\n \n Stets zu diensten: ");
		String eingabe = br.readLine();
		if (Radio.Käfer == true) {
			System.out.println("Du hast " + eingabe + " eingegeben.");
		}
		dienst.pult(eingabe);
		if (Radio.Käfer == true) {
			System.out.println(dienst.getDienst());
		}

	}

}
