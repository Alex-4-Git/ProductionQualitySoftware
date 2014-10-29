package edu.nyu.pqs14sp.jx379.ps4.test;

import org.junit.Test;

import edu.nyu.pqs14sp.jx379.ps4.model.Connect4Model;

/**
 * 
 * Connect4Model Test class
 * 
 * @author jing
 *
 */
public class Connect4ModelTest {

	@SuppressWarnings("unused")
	@Test
	public void testConnect4Model() {
		Connect4Model c4m = new Connect4Model();
	}

	@Test
	public void testAddListener() {
		Connect4Model c4m = new Connect4Model();
		c4m.addListener(null);
	}

	@Test
	public void testRemoveListener() {
		Connect4Model c4m = new Connect4Model();
		c4m.removeListener(null);
	}

	@Test
	public void testDrop() {
		Connect4Model c4m = new Connect4Model();

		// Test horizon win 1
		c4m.drop(0);
		c4m.drop(0);
		c4m.drop(0);
		c4m.drop(1);
		c4m.drop(1);
		c4m.drop(2);
		c4m.drop(2);
		c4m.drop(3);

		// Test vertical win 1
		c4m.reset();
		c4m.drop(0);
		c4m.drop(0);
		c4m.drop(1);
		c4m.drop(0);
		c4m.drop(2);
		c4m.drop(0);
		c4m.drop(3);
		c4m.drop(0);

		// Test vertical win 2
		c4m.reset();
		c4m.drop(0);

		c4m.drop(0);
		c4m.drop(0);
		c4m.drop(0);
		c4m.drop(1);
		c4m.drop(0);
		c4m.drop(1);
		c4m.drop(0);

		// Test diagonal win 1
		c4m.reset();
		c4m.drop(0);
		c4m.drop(0);
		c4m.drop(1);
		c4m.drop(1);
		c4m.drop(2);
		c4m.drop(2);
		c4m.drop(3);
		c4m.drop(2);
		c4m.drop(3);
		c4m.drop(3);
		c4m.drop(3);

		// Test diagonal win 2
		c4m.reset();
		c4m.drop(0);
		c4m.drop(3);
		c4m.drop(2);
		c4m.drop(2);
		c4m.drop(1);
		c4m.drop(1);
		c4m.drop(0);
		c4m.drop(1);
		c4m.drop(0);
		c4m.drop(5);
		c4m.drop(0);
		c4m.drop(0);

		// Test Draw Game
		c4m.reset();
		c4m.drop(0);

		c4m.drop(0);
		c4m.drop(0);
		c4m.drop(0);
		c4m.drop(0);
		c4m.drop(0);
		c4m.drop(0);

		c4m.drop(1);
		c4m.drop(1);
		c4m.drop(1);
		c4m.drop(1);
		c4m.drop(1);
		c4m.drop(1);

		c4m.drop(2);
		c4m.drop(2);
		c4m.drop(2);
		c4m.drop(2);
		c4m.drop(2);
		c4m.drop(2);

		c4m.drop(6);
		c4m.drop(6);
		c4m.drop(6);
		c4m.drop(6);
		c4m.drop(6);
		c4m.drop(6);

		c4m.drop(5);
		c4m.drop(5);
		c4m.drop(5);
		c4m.drop(5);
		c4m.drop(5);
		c4m.drop(5);

		c4m.drop(4);
		c4m.drop(4);
		c4m.drop(4);
		c4m.drop(4);
		c4m.drop(4);

		c4m.drop(3);
		c4m.drop(3);
		c4m.drop(3);
		c4m.drop(3);
		c4m.drop(3);
		c4m.drop(3);
		c4m.drop(4);

		// Test overdrop
		c4m.reset();
		c4m.drop(0);
		c4m.drop(6);
		c4m.drop(6);
		c4m.drop(6);
		c4m.drop(6);
		c4m.drop(6);
		c4m.drop(6);
		try {			
			c4m.drop(6);
		} catch (IllegalStateException ise) {

		}
		
		c4m.reset();
		c4m.setMode("PvC");
		c4m.drop(0);
		c4m.drop(6);
		c4m.drop(6);
		c4m.drop(6);
	}

	@Test
	public void testReset() {
		Connect4Model c4m = new Connect4Model();
		c4m.reset();
	}
	
	@Test
	public void testSetMode() {
		Connect4Model c4m = new Connect4Model();
		c4m.setMode("PvP");
		c4m.setMode("PvC");
	}

}
