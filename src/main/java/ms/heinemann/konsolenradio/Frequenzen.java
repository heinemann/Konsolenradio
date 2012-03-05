package ms.heinemann.konsolenradio;

public enum Frequenzen implements Comparable<Frequenzen> {

	WDR_1(
			"1",
			"1live",
			"http://gffstream.ic.llnwd.net/stream/gffstream_stream_wdr_einslive_b",
			"MP3", true),

	WDR_2MS("2", "WDR 2 Münster",
			"http://gffstream.ic.llnwd.net/stream/gffstream_mp3_w97a", "MP3",
			true),

	WDR_3("3", "WDR 3", "http://gffstream.ic.llnwd.net/stream/gffstream_w21a",
			"MP3", true),

	WDR_4("4", "WDR 4", "http://gffstream.ic.llnwd.net/stream/gffstream_w18a",
			"MP3", true),

	NDR_1("5", "N-Joy",
			"http://sc20.frf.llnw.net:80/stream/ndrstream_n-joy_hi_mp3", "MP3",
			true),

	NDR_2("6", "NDR 2", "", "MP3", true),

	RADDIOQ("7", "Radio Q", "http://radioq.uni-muenster.de:8000/beta", "MP3",
			true),

	AM("8", "Antenne Münster", "http://stream.antennemuenster.de:8000/am128k",
			"MP3", true),

	HRA("9", "Hochschulradio Aachen",
			"http://evans.hochschulradio.rwth-aachen.de:8000/radio.mp3", "MP3",
			true),

	BBC("10", "BBC1", "http://www.bbc.co.uk/radio/listen/live/r1.asx", "ASX",
			true),

	OGG("11", "OGGTest", "http://live.tlis.sk:8000/live.ogg", "OGG", true);

	private final String nummer;
	private final String name;
	private final String url;
	private final String format;
	private final boolean stream;

	private Frequenzen(final String nummer, final String name,
			final String url, final String format, final boolean stream) {
		this.nummer = nummer;
		this.name = name;
		this.url = url;
		this.format = format;
		this.stream = stream;
	}

	public static Frequenzen suchMittelsName(final String name) {
		for (Frequenzen frequenz : Frequenzen.values()) {
			if (frequenz.name.equalsIgnoreCase(name)) {
				return frequenz;
			}

		}

		throw new IllegalArgumentException(
				"Keine Frequenz für den angegebenen Namen gefunden");
	}

	public static Frequenzen suchMittelsNummer(final String eingabe) {
		// throws IOException {
		try {
			if (eingabe.isEmpty()) {

				// Radio.Dienstwahl();
				System.out
						.println("Keine Auswahl getroffen, es wird WDR 4 für sie ausgewählt. /n");
				return Frequenzen.WDR_4;

			}
			for (Frequenzen frequenz : Frequenzen.values()) {
				if (frequenz.nummer.equalsIgnoreCase(eingabe)) {
					return frequenz;
				}
			}
			for (Frequenzen frequenz : Frequenzen.values()) {
				if (frequenz.name.equalsIgnoreCase(eingabe)) {
					return frequenz;
				}
			}

		} catch (IllegalArgumentException e) {

			// throw new IllegalArgumentException(
			// "Keine Frequenz für den angegebenen Nummer gefunden");
		}
		System.out
				.println("Fehlerhafte Auswahl getroffen, es wird WDR 4 für sie ausgewählt. /n");
		return Frequenzen.WDR_4;

	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the url
	 */
	public final String getUrl() {
		return url;
	}

	/**
	 * @return the format
	 */
	public final String getFormat() {
		return format;
	}

	/**
	 * @return the stream
	 */
	public final boolean isStream() {
		return stream;
	}

	//
	// public static String WDR2MS() {
	// Frequenz.setSender("WDR 2 Münsterland");
	// Frequenz.setStreamUrl("http://gffstream.ic.llnwd.net/stream/gffstream_mp3_w97a");
	// Frequenz.setStreamCodec("MP3");
	// return getStreamUrl();
	// }
	//
	// public static String WDR3() {
	// Frequenz.setSender("WDR 3");
	// Frequenz.setStreamUrl("http://gffstream.ic.llnwd.net/stream/gffstream_w21a");
	// Frequenz.setStreamCodec("MP3");
	// return getStreamUrl();
	// }
	//
	// public static String WDR4() {
	// Frequenz.setSender("WDR 4");
	// Frequenz.setStreamUrl("http://gffstream.ic.llnwd.net/stream/gffstream_w18a");
	// Frequenz.setStreamCodec("MP3");
	// return getStreamUrl();
	// }
	//
	// public static String NDR1() {
	// Frequenz.setSender("N-Joy");
	// Frequenz.setStreamUrl("http://sc20.frf.llnw.net:80/stream/ndrstream_n-joy_hi_mp3");
	// Frequenz.setStreamCodec("MP3");
	// return getStreamUrl();
	// }
	//
	// public static String RadioQ() {
	// Frequenz.setSender("RadioQ - Das Campusradio");
	// Frequenz.setStreamUrl("http://radioq.uni-muenster.de:8000/beta");
	// Frequenz.setStreamCodec("MP3");
	// return getStreamUrl();
	// }
	//
	// public static String AntenneMS() {
	// Frequenz.setSender("Radio Antenne Münster");
	// Frequenz.setStreamUrl("http://stream.antennemuenster.de:8000/am128k");
	// Frequenz.setStreamCodec("MP3");
	// return getStreamUrl();
	// }
	//
	// public static String HRA() {
	// Frequenz.setSender("Hochschulradio Aachen");
	// Frequenz.setStreamUrl("http://evans.hochschulradio.rwth-aachen.de:8000/radio.mp3");
	// Frequenz.setStreamCodec("MP3");
	// return getStreamUrl();
	// }
	//
	// public static String Rocklandradio() {
	// Frequenz.setSender("Rocklandradio");
	// Frequenz.setStreamUrl("http://ice.streaming.spacenet.de:80/rockland");
	// Frequenz.setStreamCodec("MP3");
	// return getStreamUrl();
	// }

}
