package edu.nyu.pqs14sp.jx379.ps4.api;

/**
 * @author jing
 * 
 * The interface of listener
 *
 */
public interface Connect4Listener {

	/**
	 * @param player The win player.
	 */
	void gameWon(IConnect4Player player);

	/**
	 * Draw game.
	 */
	void gameDraw();
	
	/**
	 * Reset to a new game.
	 */
	void gameReset();

	/**
	 * Start a new game.
	 */
	void gameStarted();
	
	/**
	 * @param buttonNum The column to drop
	 * @param player	The player play this step.
	 */
	void dropPerformed(int buttonNum, IConnect4Player player);
}
