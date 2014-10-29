package edu.nyu.pqs14sp.jx379.ps5.controller;

import java.util.List;

import edu.nyu.pqs14sp.jx379.ps5.api.CanvasAPI;
import edu.nyu.pqs14sp.jx379.ps5.model.CanvasModel;
import edu.nyu.pqs14sp.jx379.ps5.pojo.Dot;

/**
 * @author jing
 * 
 * This is the controller in MVC pattern.
 * 
 * Controller is to communicate with Model and Viewer.
 *
 */
public class CanvasController implements CanvasAPI{
	private CanvasModel model;

	/**
	 * @param model		The model in MVC Pattern.
	 * 
	 * For setting the model into controller.
	 */
	public CanvasController(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void start() {
		model.start();
	}

	@Override
	public void reset() {
		model.reset();
	}

	@Override
	public void addDot(int x, int y) {
		model.addDot(x, y);		
	}

	@Override
	public void paint(List<List<Dot>> draws) {
		// no paint for controller.
	}

	@Override
	public void keyReleased() {
		model.keyReleased();
	}

}
