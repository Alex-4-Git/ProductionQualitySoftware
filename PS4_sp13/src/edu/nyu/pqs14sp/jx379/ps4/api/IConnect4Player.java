package edu.nyu.pqs14sp.jx379.ps4.api;

import java.awt.Color;

import edu.nyu.pqs14sp.jx379.ps4.type.Connect4PlayerType;

/**
 * @author jing The interface for the player.
 */
public interface IConnect4Player {
	/**
	 * @return The player type
	 */
	Connect4PlayerType getPlayerType();

	/**
	 * @return The color of this player.
	 */
	Color getColor();
}
