package edu.nyu.pqs14sp.jx379.ps4.test;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import edu.nyu.pqs14sp.jx379.ps4.conf.Connect4Config;

/**
 * The Connect4Config test class.
 * @author jing
 *
 */
public class Connect4ConfigTest {

	@SuppressWarnings("unused")
	@Test
	public void test() throws InstantiationException, IllegalAccessException, IllegalArgumentException {
		final Class<?> cls = Connect4Config.class;
	    final Constructor<?> c = cls.getDeclaredConstructors()[0];
	    c.setAccessible(true);

	    Throwable targetException = null;
	    try {
	        c.newInstance((Object[])null);
	    } catch (InvocationTargetException ite) {
	        targetException = ite.getTargetException();
	        return;
	    }

	    assertNotNull(targetException);
	    assertEquals(targetException.getClass(), InstantiationException.class);
	}

}
