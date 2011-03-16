/**
 * 
 */
package ms.heinemann.konsolenradio;

/**
 * @author adrian
 * @version 0.1 Um die Basisfunktionen des Konsolenradios zunächst zu
 *          implementieren, sind wie im Bashskript die URLs der Radiostreams
 *          fest einprogrammiert.
 */
public class Frequenz {

	/**
	 * @param args
	 * @return Gibt die URL zum Stream des Radiosender zurück.
	 */
	private static String Sender;
	private static String StreamUrl;
	private static String StreamCodec;
	private static String Frequenzen;

	// TODO Umschreiben der Frequenzclasse in einen XML-Leser

	/**
	 * Es wird der Wiedergabethread gestopt und die Senderauswahl gestartet.
	 */
	public static void drehnippel(String eingabe) {
		drehen();
		if (eingabe.equals("WDR1") | eingabe.equals("1")) {
			WDR1();
		} else if (eingabe.equals("WDR2MS") | eingabe.equals("2")) {
			WDR2MS();
		} else if (eingabe.equals("WDR3") | eingabe.equals("3")) {
			WDR3();
		} else if (eingabe.equals("WDR4") | eingabe.equals("4")) {
			WDR4();
		} else if (eingabe.equals("NDR1") | eingabe.equals("5")) {
			NDR1();
		} else if (eingabe.equals("RadioQ") | eingabe.equals("6")) {
			RadioQ();
		} else if (eingabe.equals("AntenneMS") | eingabe.equals("7")) {
			AntenneMS();
		} else if (eingabe.equals("HRA") | eingabe.equals("8")) {
			HRA();
		} else if (eingabe.equals("Rocklandradio") | eingabe.equals("9")) {
			Rocklandradio();
		} else if (eingabe.equals("BBC1") | eingabe.equals("10")) {
			BBC1();
		} else {
			System.out.println("Drehnippel ist fehlgeschlagen");
		}

	}

	/**
	 * Stoppen des Wiedergabethreads
	 */
	private static void drehen() {
		// TODO: Thread stoppen
	}

	public static String BBC1() {
		Frequenz.setSender("BBC1");
		Frequenz.setStreamUrl("http://www.bbc.co.uk/radio/listen/live/r1.asx");
		Frequenz.setStreamCodec("ASX");
		return getStreamUrl();
	}

	public static String WDR1() {
		Frequenz.setSender("Radio 1live");
		Frequenz.setStreamUrl("http://gffstream.ic.llnwd.net/stream/gffstream_stream_wdr_einslive_b");
		Frequenz.setStreamCodec("MP3");
		return getStreamUrl();
	}

	public static String WDR2MS() {
		Frequenz.setSender("WDR 2 Münsterland");
		Frequenz.setStreamUrl("http://gffstream.ic.llnwd.net/stream/gffstream_mp3_w97a");
		Frequenz.setStreamCodec("MP3");
		return getStreamUrl();
	}

	public static String WDR3() {
		Frequenz.setSender("WDR 3");
		Frequenz.setStreamUrl("http://gffstream.ic.llnwd.net/stream/gffstream_w21a");
		Frequenz.setStreamCodec("MP3");
		return getStreamUrl();
	}

	public static String WDR4() {
		Frequenz.setSender("WDR 4");
		Frequenz.setStreamUrl("http://gffstream.ic.llnwd.net/stream/gffstream_w18a");
		Frequenz.setStreamCodec("MP3");
		return getStreamUrl();
	}

	public static String NDR1() {
		Frequenz.setSender("N-Joy");
		Frequenz.setStreamUrl("http://sc20.frf.llnw.net:80/stream/ndrstream_n-joy_hi_mp3");
		Frequenz.setStreamCodec("MP3");
		return getStreamUrl();
	}

	public static String RadioQ() {
		Frequenz.setSender("RadioQ - Das Campusradio");
		Frequenz.setStreamUrl("http://radioq.uni-muenster.de:8000/beta");
		Frequenz.setStreamCodec("MP3");
		return getStreamUrl();
	}

	public static String AntenneMS() {
		Frequenz.setSender("Radio Antenne Münster");
		Frequenz.setStreamUrl("http://stream.antennemuenster.de:8000/am128k");
		Frequenz.setStreamCodec("MP3");
		return getStreamUrl();
	}

	public static String HRA() {
		Frequenz.setSender("Hochschulradio Aachen");
		Frequenz.setStreamUrl("http://evans.hochschulradio.rwth-aachen.de:8000/radio.mp3");
		Frequenz.setStreamCodec("MP3");
		return getStreamUrl();
	}

	public static String Rocklandradio() {
		Frequenz.setSender("Rocklandradio");
		Frequenz.setStreamUrl("http://ice.streaming.spacenet.de:80/rockland");
		Frequenz.setStreamCodec("MP3");
		return getStreamUrl();
	}

	/**
	 * Volgend kommen die Getter und Setter.
	 * 
	 */
	public static void setSender(String sender) {
		Sender = sender;
	}

	public static String getSender() {
		return Sender;
	}

	public static void setStreamUrl(String streamUrl) {
		StreamUrl = streamUrl;
	}

	public static String getStreamUrl() {
		return StreamUrl;
	}

	public static void setStreamCodec(String streamCodec) {
		StreamCodec = streamCodec;
	}

	public static String getStreamCodec() {
		return StreamCodec;
	}

	public static void leseFrequenzen() {
		Frequenzen = "\n 1 - WDR1 	: Radio 1live "
				+ "\n 2 - WDR2MS 	: Westdeutscher Rundfunk Radio 2 Münsterland "
				+ "\n 3 - WDR3   	: Westdeutscher Rundfunk Radio 3"
				+ "\n 4 - WDR4   	: Westdeutscher Rundfunk Radio 4"
				+ "\n 5 - NDR1   	: N-Joy Radio"
				+ "\n 6 - RadioQ	: RadioQ - Das Campusradio"
				+ "\n 7 - AntenneMS	: Lokalradio Antenne Münster"
				+ "\n 8 - HRA	: Hochschulradio Aachen"
				+ "\n 9 - Rocklandradio: Rocklandradio"
				+ "\n10 - BBC1	: British Broadcasting Radio 1";
	}

	public static String getFrequenzen() {
		return Frequenzen;
	}

}
