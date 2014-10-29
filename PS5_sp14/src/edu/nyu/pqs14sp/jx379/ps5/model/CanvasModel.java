package edu.nyu.pqs14sp.jx379.ps5.model;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs14sp.jx379.ps5.api.CanvasAPI;
import edu.nyu.pqs14sp.jx379.ps5.pojo.Dot;

/**
 * @author jing
 * 
 * This is the model in MVC Pattern.
 * 
 * For doing the storage and sending out event command.
 *
 */
public class CanvasModel implements CanvasAPI {
	private boolean drawStarted;
	private boolean keyReleased;
	private List<CanvasAPI> listeners;
	private List<List<Dot>> draws;

	public CanvasModel() {
		drawStarted = false;
		keyReleased = true;
		listeners = new ArrayList<CanvasAPI>();
		draws = new ArrayList<List<Dot>>();
	}

	/**
	 * Observer pattern for adding listener.
	 * 
	 * @param listener
	 */
	public void addListener(CanvasAPI listener) {
		listeners.add(listener);
	}

	/**
	 * Observer pattern for removing listener.
	 * 
	 * @param listener
	 */
	public void removeListener(CanvasAPI listener) {
		if(listeners!=null&&!listeners.isEmpty()){
			listeners.remove(listener);
		}
	}

	/**
	 * Notify the start event.
	 */
	private void fireDrawStartEvent() {
		for (CanvasAPI listener : listeners) {
			listener.start();
		}
	}

	/**
	 * Notify the reset event.
	 */
	private void fireDrawResetEvent() {
		if (drawStarted) {
			for (CanvasAPI listener : listeners) {
				listener.reset();
			}
		}
	}

	/**
	 * Notify the paint event.
	 */
	private void firePaintEvent() {
		for (CanvasAPI listener : listeners) {
			listener.paint(draws);
		}
	}

	@Override
	public void start() {
		drawStarted = true;
		fireDrawStartEvent();
	}

	@Override
	public void reset() {
		if (drawStarted) {
			draws = new ArrayList<List<Dot>>();
			fireDrawResetEvent();
		}
	}

	@Override
	public void addDot(int x, int y) {
		if (drawStarted) {
			if (keyReleased) {
				keyReleased = false;
				List<Dot> list = new ArrayList<Dot>();
				list.add(new Dot.Builder(x, y).build());
				draws.add(list);
			} else {
				List<Dot> list = draws.get(draws.size() - 1);
				list.add(new Dot.Builder(x, y).build());
			}
			firePaintEvent();
		}
	}

	@Override
	public void paint(List<List<Dot>> draws) {
		// no paint for model.
	}

	@Override
	public void keyReleased() {
		if(drawStarted){
			keyReleased = true;
		}
	}

}
