package edu.nyu.pqs14sp.jx379.ps5.api;

import java.util.List;

import edu.nyu.pqs14sp.jx379.ps5.pojo.Dot;

/**
 * @author Jing.Xia
 * 
 * This is the interface for all MVC modules.
 *
 */
public interface CanvasAPI {
	
	/**
	 * To start the draw.
	 */
	public void start();

	/**
	 * To clear the whole paint.
	 */
	public void reset();
	
	/**
	 * To detect start draw.
	 */
	public void keyReleased();

	/**
	 * @param x 	The x position in paint.
	 * @param y		The y position in paint.
	 * 
	 * To save current mouse move position into model.
	 */
	public void addDot(int x, int y);
	
	/**
	 * @param draws		The whole mouse movement saved.
	 * To save the whole mouse movement into model.  
	 */
	public void paint(List<List<Dot>> draws);
}
