/**
 * 
 */
package ms.heinemann.konsolenradio.test;

import static org.junit.Assert.assertTrue;
import ms.heinemann.konsolenradio.Dienst;

import org.junit.Test;

/**
 * @author adrian
 * 
 */
public class RadioTest {

	Dienst dienst = new Dienst();

	public void setup() {

	}

	@Test
	public void test() {
		// fail("Not yet implemented");type filter text
		assertTrue(dienst.getEnergie());
	}

}
