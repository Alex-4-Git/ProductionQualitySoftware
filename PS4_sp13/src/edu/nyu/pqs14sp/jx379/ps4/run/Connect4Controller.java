package edu.nyu.pqs14sp.jx379.ps4.run;

import edu.nyu.pqs14sp.jx379.ps4.model.Connect4Model;
import edu.nyu.pqs14sp.jx379.ps4.viewer.Connect4View;

/**
 * 
 * Controller to run this game.
 * 
 * @author jing
 *
 */
public class Connect4Controller {
	/**
	 * The display game function.
	 */
	private void go() {
		Connect4Model model = new Connect4Model();
		@SuppressWarnings("unused")
		Connect4View view1 = new Connect4View(model);
		Connect4View view2 = new Connect4View(model);
	}

	public static void main(String[] args) {
		Connect4Controller c4c = new Connect4Controller();
		c4c.go();
	}
}
