package edu.nyu.pqs14sp.jx379.ps5.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nyu.pqs14sp.jx379.ps5.pojo.Dot;

public class TestDot {

	@Test
	public void testHashCode() {
		Dot x = new Dot.Builder(0, 0).build();  // equals and hashCode check name field value
		Dot y = new Dot.Builder(0, 0).build();
		assertNotSame(x, y); 
		assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testGetX() {
		Dot x = new Dot.Builder(0, 0).build();
		x.getX();
	}

	@Test
	public void testGetY() {
		Dot x = new Dot.Builder(0, 0).build();
		x.getY();
	}

	@Test
	public void testEqualsObject() {
		Dot x = new Dot.Builder(0, 0).build();  // equals and hashCode check name field value
		Dot y = new Dot.Builder(0, 0).build();
	    assertTrue(x.equals(y) && y.equals(x));
	    assertFalse(x.equals(null));
	    assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testToString() {
		assertFalse(new Dot.Builder(0, 0).build().toString().contains("@"));
	}

}
