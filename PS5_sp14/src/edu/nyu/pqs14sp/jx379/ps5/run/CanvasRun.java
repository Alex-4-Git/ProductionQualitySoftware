package edu.nyu.pqs14sp.jx379.ps5.run;

import edu.nyu.pqs14sp.jx379.ps5.model.CanvasModel;
import edu.nyu.pqs14sp.jx379.ps5.view.CanvasView;

/**
 * @author jing
 * 
 * This is the main class for running this application.
 *
 */
public class CanvasRun {
	/**
	 * The display Canvas in front.
	 */
	private void go() {
		CanvasModel model = new CanvasModel();
		//To run two instance and change same time.
		for (int i = 0; i < 2; i++) {
			@SuppressWarnings("unused")
			CanvasView view1 = new CanvasView(model);
		}
	}

	public static void main(String[] args) {
		CanvasRun cr = new CanvasRun();
		cr.go();
	}
}
