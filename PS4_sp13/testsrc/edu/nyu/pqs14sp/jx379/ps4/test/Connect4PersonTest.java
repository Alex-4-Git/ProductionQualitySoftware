package edu.nyu.pqs14sp.jx379.ps4.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nyu.pqs14sp.jx379.ps4.api.IConnect4Player;
import edu.nyu.pqs14sp.jx379.ps4.conf.Connect4PlayerFactory;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4PlayerType;

/**
 * Connect4Person Test
 * 
 * @author jing
 *
 */
public class Connect4PersonTest {

	@Test
	public void testEquals_Symmetric() {
		IConnect4Player x = Connect4PlayerFactory.getPlayer(Connect4PlayerType.PlayerOne);
		IConnect4Player y = Connect4PlayerFactory.getPlayer(Connect4PlayerType.PlayerOne);
		IConnect4Player z = Connect4PlayerFactory.getPlayer(Connect4PlayerType.PlayerTwo);
		IConnect4Player a = Connect4PlayerFactory.getPlayer(Connect4PlayerType.Computer);
		assertTrue(x.equals(x));
	    assertTrue(x.equals(y) && y.equals(x));	    
	    assertTrue(x.hashCode() == y.hashCode());
	    assertFalse(x.equals(null));
	    assertFalse(x.equals(z));
	    assertFalse(x.equals(a));
	    x.getColor();
	    x.toString();
	}

}
