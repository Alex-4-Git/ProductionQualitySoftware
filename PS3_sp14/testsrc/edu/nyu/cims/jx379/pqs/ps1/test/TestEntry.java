package edu.nyu.cims.jx379.pqs.ps1.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.nyu.cims.am4993.pqs.problemset1.Entry;
import edu.nyu.cims.am4993.pqs.problemset1.Properties;

/**
 * @author Jing.Xia
 * 
 */
public class TestEntry {

	@BeforeClass
	public static void testSetup() {
	}

	@AfterClass
	public static void testCleanup() {
		// Teardown for data used by the unit tests
	}

	@Test
	public void testHashCode() {
		Entry e1 = new Entry.Builder("Indore", "Pratik").build();
		Entry e2 = new Entry.Builder("Pratik", "Indore").build();
		// assertEquals(e1.hashCode(),e2.hashCode());
		// This should be not equal based on hashCode .
		assertNotEquals(e1.hashCode(), e2.hashCode());
	}

	@Test
	public void testGetName() {
		Entry e1 = new Entry.Builder("Indore", "Pratik").build();
		assertEquals(e1.getName(), "Indore");
	}

	@Test
	public void testGetAddress() {
		Entry e1 = new Entry.Builder("Indore", "Pratik").build();
		assertEquals(e1.getAddress(), "Pratik");
	}

	@Test
	public void testGetPhoneNum() {
		Entry e1 = new Entry.Builder("Indore", "Pratik").build();
		assertNull(e1.getPhoneNum());
		Entry e2 = new Entry.Builder("Indore", "Pratik").phoneNum("test123")
				.build();
		assertEquals(e2.getPhoneNum(), "test123");
	}

	@Test
	public void testGetEmail() {
		Entry e1 = new Entry.Builder("Indore", "Pratik").build();
		assertNull(e1.getEmail());
		Entry e2 = new Entry.Builder("Indore", "Pratik").email("test123")
				.build();
		assertEquals(e2.getEmail(), "test123");
	}

	@Test
	public void testGetNote() {
		Entry e1 = new Entry.Builder("Indore", "Pratik").build();
		assertNull(e1.getNote());
		Entry e2 = new Entry.Builder("Indore", "Pratik").note("test123")
				.build();
		assertEquals(e2.getNote(), "test123");
	}

	@Test
	public void testEditField() {
		Entry e1 = new Entry.Builder("Indore", "Pratik").build();
		Properties property = Properties.NOTE;
		try {
			e1.editField(property, null);
		} catch (Exception e) {
			// InvalidUpdateStringException Cannot be cought.
		}
		try {
			e1.editField(null, "hello");
			fail("Exception is not threw");
		} catch (Exception e) {
		}
		property = Properties.NAME;
		try {
			e1.editField(property, "hello");
			assertEquals(e1.getName(), "hello");
		} catch (Exception e) {
		}
		property = Properties.ADDRESS;
		try {
			e1.editField(property, "hello");
			assertEquals(e1.getAddress(), "hello");
		} catch (Exception e) {
		}
		property = Properties.EMAIL;
		try {
			e1.editField(property, "hello");
			assertEquals(e1.getEmail(), "hello");
		} catch (Exception e) {
		}
		property = Properties.NOTE;
		try {
			e1.editField(property, "hello");
			assertEquals(e1.getNote(), "hello");
		} catch (Exception e) {
		}
		property = Properties.PHONENUM;
		try {
			e1.editField(property, "hello");
			assertEquals(e1.getPhoneNum(), "hello");
		} catch (Exception e) {
		}
	}

	@Test
	public void testEqualsObject() {
		Entry e1 = new Entry.Builder("Indore", "Pratik").build();
		Entry e2 = new Entry.Builder("Indore", "Pratik").build();
		assertTrue(e1.equals(e2));

		Entry e3 = new Entry.Builder("Pratik", "Indore").build();
		assertFalse(e3.equals(e2));

		Entry e4 = new Entry.Builder("", "").build();
		Entry e5 = new Entry.Builder(" ", " ").build();
		assertFalse(e4.equals(e5));
	}

	@Test
	public void testToString() {
		Entry e1 = new Entry.Builder("Indore", "Pratik").build();
		Entry e2 = new Entry.Builder("Indore", "Pratik").build();
		assertEquals(e1.toString(), e2.toString());
	}

}
