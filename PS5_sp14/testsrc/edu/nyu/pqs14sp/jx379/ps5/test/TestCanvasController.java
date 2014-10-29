package edu.nyu.pqs14sp.jx379.ps5.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nyu.pqs14sp.jx379.ps5.controller.CanvasController;
import edu.nyu.pqs14sp.jx379.ps5.model.CanvasModel;

public class TestCanvasController {

	@Test
	public void testCanvasController() {
		CanvasModel m = new CanvasModel();
		CanvasController cc = new CanvasController(m);
	}

	@Test
	public void testStart() {
		CanvasModel m = new CanvasModel();
		CanvasController cc = new CanvasController(m);
		cc.start();
	}

	@Test
	public void testReset() {
		CanvasModel m = new CanvasModel();
		CanvasController cc = new CanvasController(m);
		cc.reset();
	}

	@Test
	public void testAddDot() {
		CanvasModel m = new CanvasModel();
		CanvasController cc = new CanvasController(m);
		cc.addDot(0, 0);
	}

	@Test
	public void testPaint() {
		CanvasModel m = new CanvasModel();
		CanvasController cc = new CanvasController(m);
		cc.paint(null);
	}

	@Test
	public void testKeyReleased() {
		CanvasModel m = new CanvasModel();
		CanvasController cc = new CanvasController(m);
		cc.keyReleased();
	}

}
