package edu.nyu.pqs14sp.jx379.ps4.test;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import edu.nyu.pqs14sp.jx379.ps4.conf.Connect4PlayerFactory;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4PlayerType;

/**
 * 
 * Connect4PlayerFactory Test
 * 
 * @author jing
 *
 */
public class Connect4PlayerFactoryTest {

	@SuppressWarnings("unused")
	@Test
	public void test() throws InstantiationException, IllegalAccessException,
			IllegalArgumentException {
		final Class<?> cls = Connect4PlayerFactory.class;
		final Constructor<?> c = cls.getDeclaredConstructors()[0];
		c.setAccessible(true);

		Throwable targetException = null;
		try {
			c.newInstance((Object[]) null);
		} catch (InvocationTargetException ite) {
			targetException = ite.getTargetException();
			return;
		}

		assertNotNull(targetException);
		assertEquals(targetException.getClass(), InstantiationException.class);
	}

	@Test
	public void test2() {
		Connect4PlayerFactory.getPlayer(Connect4PlayerType.PlayerOne);
		Connect4PlayerFactory.getPlayer(Connect4PlayerType.PlayerTwo);
		Connect4PlayerFactory.getPlayer(Connect4PlayerType.Computer);
		try {
			Connect4PlayerFactory.getPlayer(null);
		} catch (IllegalArgumentException e) {
		}
		
	}

}
