package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects.
 * It maintains references to all created IStopwatch objects and provides a
 * convenient method for getting a list of those objects.
 * 
 */
public class StopwatchFactory {
	private static Map<String, IStopwatch> map;
	
	/**
	 * Suppress default constructor for noninstantiability
	 */
	private StopwatchFactory() {
		throw new AssertionError();
	}
	

	/**
	 * Creates and returns a new IStopwatch object
	 * 
	 * @param id
	 *            The identifier of the new object
	 * @return The new IStopwatch object
	 * @throws IllegalArgumentException
	 *             if <code>id</code> is empty, null, or already taken.
	 */
	public static IStopwatch getStopwatch(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException();
		}
		if (map == null) {
			//1st time
			map = new ConcurrentHashMap<String, IStopwatch>();
			IStopwatch newsw = new MyStopwatch(id);
			map.put(id, newsw);
			return newsw;
		} else {
			if (map.containsKey(id)) {
				throw new IllegalArgumentException();
			} else {
				//after 1st time
				IStopwatch newsw = new MyStopwatch(id);
				map.put(id, newsw);
				return newsw;
			}
		}
	}

	/**
	 * Returns a list of all created stopwatches
	 * 
	 * @return a List of al creates IStopwatch objects. Returns an empty list if
	 *         no IStopwatches have been created.
	 */
	public static List<IStopwatch> getStopwatches() {
		if(map==null||map.isEmpty()){
			return new ArrayList<IStopwatch>();
		}else{
			return new ArrayList<IStopwatch>(map.values());
		}
	}
}
