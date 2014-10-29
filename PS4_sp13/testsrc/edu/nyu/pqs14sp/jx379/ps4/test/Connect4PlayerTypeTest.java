package edu.nyu.pqs14sp.jx379.ps4.test;

import org.junit.Test;

import edu.nyu.pqs14sp.jx379.ps4.type.Connect4PlayerType;

/**
 * Connect4PlayerType Test
 * 
 * @author jing
 *
 */
public class Connect4PlayerTypeTest {

	@Test
	public void test() {
		for (Connect4PlayerType b : Connect4PlayerType.values()) {
			switch (b) {
			case PlayerOne:
				break;
			case PlayerTwo:
				break;
			case Computer:
				break;
			default:
				throw new IllegalArgumentException(b.toString());
			}

			for (String s : new String[] { "PlayerOne", "PlayerTwo" }) {
				Connect4PlayerType.valueOf(s);
			}

		}
	}

}
