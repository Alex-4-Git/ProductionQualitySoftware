package edu.nyu.pqs14sp.jx379.ps4.conf;

import java.awt.Color;

import javax.swing.UIManager;

/**
 * 
 * This is the config class for setting the values.
 * 
 * @author jing
 * 
 */
public class Connect4Config {

	/**
	 * Prevent constructs a new Connect4Config.
	 * 
	 * @throws InstantiationException
	 */
	private Connect4Config() throws InstantiationException {
		throw new InstantiationException(
				"Instances of this type are forbidden.");
	}

	public static final int CONNECT_4_BOARD_ROW = 6;
	public static final int CONNECT_4_BOARD_COLUMN = 7;
	public static final Color DEFAULT_COLOR = UIManager
			.getColor("Panel.background");
	public static final Color BOARDER_LINE_COLOR = Color.BLACK;
	public static final Color PLAYER1_COLOR = Color.RED;
	public static final Color PLAYER2_COLOR = Color.BLUE;
	public static final Color COMPUTER_COLOR = Color.YELLOW;;

}
