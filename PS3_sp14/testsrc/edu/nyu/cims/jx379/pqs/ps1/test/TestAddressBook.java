package edu.nyu.cims.jx379.pqs.ps1.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.nyu.cims.am4993.pqs.problemset1.AddressBook;
import edu.nyu.cims.am4993.pqs.problemset1.Entry;
import edu.nyu.cims.am4993.pqs.problemset1.Properties;

/**
 * @author Jing.Xia
 *
 */
public class TestAddressBook {

	@Test
	public void testHashCode() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");
			Entry e1 = new Entry.Builder("Indore", "Pratik").build();
			Entry e2 = new Entry.Builder("Indore", "").build();

			AddressBook ab2 = AddressBook.newInstance("hello");
			ab1.addEntry(e1);
			ab1.addEntry(e2);

			assertNotEquals(ab1.hashCode(), ab2.hashCode());
		} catch (Exception e) {
		}
	}

	@Test
	public void testNewInstance() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");
			assertNotNull(ab1);
		} catch (Exception e) {
		}
	}

	@Test
	public void testGetEntries() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");
			Entry e1 = new Entry.Builder("Indore", "Pratik").build();
			Entry e2 = new Entry.Builder("Indore", "").build();

			ab1.addEntry(e1);
			ab1.addEntry(e2);

			assertEquals(ab1.getEntries().size(), 2);

		} catch (Exception e) {
		}
	}

	@Test
	public void testGetUserName() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");
			assertEquals(ab1.getUserName(), "hello");

			ab1 = AddressBook.newInstance("");
			assertEquals(ab1.getUserName(), "");

			ab1 = AddressBook.newInstance(null);
			fail("Cannot allow null as username");
			// assertEquals(ab1.getUserName(),null);
		} catch (Exception e) {
		}
	}

	@Test
	public void testGetId() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");
			AddressBook ab2 = AddressBook.newInstance("hello");
			assertNotNull(ab1.getId());
			assertNotEquals(ab1.getId(), ab2.getId());
		} catch (Exception e) {
		}
	}

	@Test
	public void testAddEntry() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");

			Entry e1 = new Entry.Builder("Indore", "Pratik").build();

			assertEquals(ab1.getEntries(), new ArrayList<Entry>());
			ab1.addEntry(e1);
			assertEquals(ab1.getEntries().get(0), e1);
		} catch (Exception e) {
		}
	}

	@Test
	public void testRemoveEntry() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");

			Entry e1 = new Entry.Builder("Indore", "Pratik").build();
			Entry e2 = new Entry.Builder("Indore", "Pratik").build();
			ab1.addEntry(e1);
			assertEquals(ab1.getEntries().get(0), e1);
			ab1.removeEntry(e2);
			assertEquals(ab1.getEntries().get(0), e1);
			ab1.removeEntry(e1);
			assertEquals(ab1.getEntries(), new ArrayList<Entry>());
		} catch (Exception e) {
		}
	}

	@Test
	public void testSearch() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");

			Entry e1 = new Entry.Builder("Indore", "Pratik").build();
			Entry e2 = new Entry.Builder("Indore", "Pratik").build();
			ab1.addEntry(e1);
			ab1.addEntry(e2);

			Properties property = Properties.NAME;
			List result = ab1.search(property, "Indore");
			assertTrue(result.contains(e1));
			assertTrue(result.contains(e2));

			List result2 = ab1.search(property, "Indore1");
			assertFalse(result2.contains(e1));
			assertFalse(result2.contains(e2));

			property = Properties.ADDRESS;
			List result3 = ab1.search(property, "Pratik");
			assertTrue(result3.contains(e1));
			assertTrue(result3.contains(e2));

			List result4 = ab1.search(property, "Pratik1");
			assertFalse(result4.contains(e1));
			assertFalse(result4.contains(e2));

			Entry e3 = new Entry.Builder("Indore", "Pratik").email("email")
					.note("note").phoneNum("phonenum").build();
			Entry e4 = new Entry.Builder("Indore", "Pratik").email("email1")
					.note("note1").phoneNum("phonenum1").build();
			ab1.addEntry(e3);
			ab1.addEntry(e4);

			property = Properties.EMAIL;
			List result5 = ab1.search(property, "email");
			assertTrue(result5.contains(e3));
			assertTrue(result5.contains(e4));
			assertFalse(result5.contains(e1));
			assertFalse(result5.contains(e2));

			property = Properties.EMAIL;
			List result6 = ab1.search(property, "email1");
			assertFalse(result6.contains(e3));
			assertTrue(result6.contains(e4));
			assertFalse(result6.contains(e1));
			assertFalse(result6.contains(e2));

			property = Properties.NOTE;
			List result7 = ab1.search(property, "note");
			assertTrue(result7.contains(e3));
			assertTrue(result7.contains(e4));
			assertFalse(result7.contains(e1));
			assertFalse(result7.contains(e2));

			property = Properties.NOTE;
			List result8 = ab1.search(property, "note1");
			assertFalse(result8.contains(e3));
			assertTrue(result8.contains(e4));
			assertFalse(result8.contains(e1));
			assertFalse(result8.contains(e2));

			property = Properties.PHONENUM;
			List result9 = ab1.search(property, "phonenum");
			assertTrue(result9.contains(e3));
			assertTrue(result9.contains(e4));
			assertFalse(result9.contains(e1));
			assertFalse(result9.contains(e2));

			property = Properties.PHONENUM;
			List result10 = ab1.search(property, "phonenum1");
			assertFalse(result10.contains(e3));
			assertTrue(result10.contains(e4));
			assertFalse(result10.contains(e1));
			assertFalse(result10.contains(e2));

		} catch (Exception e) {
		}
	}

	@Test
	public void testSave() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");

			Entry e1 = new Entry.Builder("Indore", "Pratik").build();
			Entry e2 = new Entry.Builder("Indore", "Pratik").build();
			ab1.addEntry(e1);
			ab1.addEntry(e2);

			AddressBook.save(ab1);

			File f = new File(ab1.getUserName() + "_" + ab1.getId() + ".ser");
			assertTrue(f.exists() && f.isFile());

			AddressBook ab2 = AddressBook.read(ab1.getUserName() + "_"
					+ ab1.getId() + ".ser");
			assertEquals(ab1, ab2);
		} catch (Exception e) {

		}

	}

	@Test
	public void testRead() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");

			Entry e1 = new Entry.Builder("Indore", "Pratik").build();
			Entry e2 = new Entry.Builder("Indore", "Pratik").build();
			ab1.addEntry(e1);
			ab1.addEntry(e2);
			AddressBook.save(ab1);
			AddressBook ab2 = AddressBook.read(ab1.getUserName() + "_"
					+ ab1.getId() + ".ser");
			assertEquals(ab1, ab2);
		} catch (Exception e) {

		}
	}

	@Test
	public void testEqualsObject() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");

			Entry e1 = new Entry.Builder("Indore", "Pratik").build();
			Entry e2 = new Entry.Builder("Indore", "Pratik").build();
			AddressBook ab2 = AddressBook.newInstance("hello");
			ab1.addEntry(e1);
			ab1.addEntry(e2);
			ab2.addEntry(e1);
			ab2.addEntry(e2);

			assertTrue(ab1.equals(ab2));

		} catch (Exception e) {

		}
	}

	@Test
	public void testToString() {
		try {
			AddressBook ab1 = AddressBook.newInstance("hello");

			Entry e1 = new Entry.Builder("Indore", "Pratik").build();
			Entry e2 = new Entry.Builder("Indore", "Pratik").build();
			AddressBook ab2 = AddressBook.newInstance("hello");
			ab1.addEntry(e1);
			ab1.addEntry(e2);
			ab2.addEntry(e1);
			ab2.addEntry(e2);

			assertTrue(ab1.toString().equals(ab2.toString()));
		} catch (Exception e) {

		}
	}

}
