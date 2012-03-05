/**
 * 
 */
package ms.heinemann.konsolenradio;

import java.io.IOException;
import java.net.URI;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

//TODO: Alle möglichen Execptions abfangen. Es ist weiterhin die Frage zu klären was passiert, wenn der Datenstrom abreisst. 

public class Wiedergabe extends Thread {

	boolean running = true;
	static boolean senderWechsel = false;

	@Override
	public void run() {
		if (Radio.Käfer == true) {
			System.out.println("Empfang des Datenstroms wird vorbereitet.");
		}

		// while (!isInterrupted()) {
		// while (running) {
		Wiedergabe.senderWechsel = false;
		Empfangen();
		// }
		if (Radio.Käfer == true) {
			System.out.println("Das Ende des Datenstroms");
		}

	}

	@Override
	public void interrupt() {
		senderWechsel = true;
		running = false;
	}

	public static void Empfangen() {
		if (Radio.Käfer == true) {
			System.out.println("URL wird geholt." + Radio.frequenz.getUrl());
		}
		playStream(Radio.frequenz.getUrl());
	}

	private static void playStream(String streamUrl) {
		try {

			URI fileurl = new URI(streamUrl);
			AudioInputStream in = AudioSystem.getAudioInputStream(fileurl
					.toURL());
			if (Radio.Käfer == true) {
				System.out.println("Datenstrom ist angefordert");
			}
			AudioInputStream din = null;
			AudioFormat baseFormat = in.getFormat();
			if (Radio.Käfer == true) {
				System.out.println("Dekodierung wird gestartet");
			}
			AudioFormat decodedFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
					baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
					false);
			din = AudioSystem.getAudioInputStream(decodedFormat, in);
			// Play now.
			System.out.println("\n On Air - " + Radio.frequenz.getName());
			// notify();
			rawplay(decodedFormat, din);
			in.close();
		} catch (Exception e) {
			// Handle exception.
			System.out.println(e);
		}
	}

	private static void rawplay(AudioFormat targetFormat, AudioInputStream din)
			throws IOException, LineUnavailableException {
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat);
		if (line != null) {
			// Start
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			while (nBytesRead != -1) {
				nBytesRead = din.read(data, 0, data.length);
				if (nBytesRead != -1)
					nBytesWritten = line.write(data, 0, nBytesRead);

				if (senderWechsel) {
					if (Radio.Käfer == true) {
						System.out.println("Rawplay - Senderwechsel");
					}
					nBytesRead = -1;
				}

			}
			// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
			if (Radio.Käfer == true) {
				System.out.println("Rawplay - close");
			}

		}

	}

	private static SourceDataLine getLine(AudioFormat audioFormat)
			throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}

}