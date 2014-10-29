package edu.nyu.pqs14sp.jx379.ps4.test;

import org.junit.Test;

import edu.nyu.pqs14sp.jx379.ps4.type.Connect4GameMode;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4PlayerType;

/**
 * Connect4GameMode Test class
 * 
 * @author jing
 *
 */
public class Connect4GameModeTest {

	@Test
	public void test() {
		for (Connect4GameMode b : Connect4GameMode.values()) {
			switch (b) {
			case PvP:
				break;
			case PvC:
				break;
			default:
				throw new IllegalArgumentException(b.toString());
			}

			for (String s : new String[] { "PvP", "PvC" }) {
				Connect4GameMode.valueOf(s);
			}

		}
	}

}
