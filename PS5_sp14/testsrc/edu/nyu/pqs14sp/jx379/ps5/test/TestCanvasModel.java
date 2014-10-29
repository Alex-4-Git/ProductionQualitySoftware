package edu.nyu.pqs14sp.jx379.ps5.test;

import org.junit.Test;

import edu.nyu.pqs14sp.jx379.ps5.controller.CanvasController;
import edu.nyu.pqs14sp.jx379.ps5.model.CanvasModel;

public class TestCanvasModel {

	@Test
	public void testCanvasModel() {
		CanvasModel cm = new CanvasModel();
	}

	@Test
	public void testAddListener() {
		CanvasModel cm = new CanvasModel();
		cm.addListener(null);

		CanvasController cc = new CanvasController(cm);
		cm.addListener(cc);
	}

	@Test
	public void testRemoveListener() {
		CanvasModel cm = new CanvasModel();
		cm.addListener(null);
		cm.removeListener(null);
		
		CanvasController cc = new CanvasController(cm);
		cm.addListener(cc);
		cm.removeListener(cc);
	}
		
	@Test
	public void testTogger(){
		
		CanvasModel cm = new CanvasModel();
		cm.addListener(null);
		cm.removeListener(null);
		
		CanvasController cc = new CanvasController(cm);
		cm.addListener(cc);
		cm.removeListener(cc);
		
	}

	@Test
	public void testStart() {
		CanvasModel cm = new CanvasModel();
		cm.start();
	}

	@Test
	public void testReset() {
		CanvasModel cm = new CanvasModel();
		cm.reset();
		cm.start();
		cm.reset();
	}

	@Test
	public void testAddDot() {
		CanvasModel cm = new CanvasModel();
		cm.addDot(0, 0);
		cm.start();
		cm.addDot(0, 0);
		cm.keyReleased();
		cm.addDot(0, 0);
		cm.addDot(0, 0);
		cm.addDot(0, 0);
	}

	@Test
	public void testPaint() {
		CanvasModel cm = new CanvasModel();
		cm.paint(null);
		cm.start();
		cm.paint(null);
	}

	@Test
	public void testKeyReleased() {
		CanvasModel cm = new CanvasModel();
		cm.keyReleased();
		cm.start();
		cm.keyReleased();
	}

}
