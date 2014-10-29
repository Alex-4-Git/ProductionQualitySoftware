package edu.nyu.pqs14sp.jx379.ps4.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nyu.pqs14sp.jx379.ps4.api.IConnect4Player;
import edu.nyu.pqs14sp.jx379.ps4.conf.Connect4PlayerFactory;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4Computer;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4PlayerType;

/**
 * The Connect4Computer test class.
 * 
 * @author jing
 *
 */
public class Connect4ComputerTest {

	@Test
	public void test() {
		IConnect4Player x = Connect4PlayerFactory
				.getPlayer(Connect4PlayerType.PlayerOne);
		IConnect4Player y = Connect4PlayerFactory
				.getPlayer(Connect4PlayerType.PlayerOne);
		IConnect4Player z = Connect4PlayerFactory
				.getPlayer(Connect4PlayerType.PlayerTwo);
		IConnect4Player a = Connect4PlayerFactory
				.getPlayer(Connect4PlayerType.Computer);
		IConnect4Player b = Connect4PlayerFactory
				.getPlayer(Connect4PlayerType.Computer);
		assertTrue(a.equals(a));
		assertTrue(a.equals(b) && b.equals(a));
		assertTrue(b.hashCode() == a.hashCode());
		assertFalse(a.equals(null));
		assertFalse(a.equals(z));
		assertFalse(a.equals(x));
		a.getColor();
		a.toString();

		try {
			if (a instanceof Connect4Computer) {
				((Connect4Computer) a).getNextStep(null);
			}
		} catch (IllegalStateException e) {
		}
	}

}
