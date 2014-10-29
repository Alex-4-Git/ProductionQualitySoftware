package edu.nyu.pqs14sp.jx379.ps4.conf;

import edu.nyu.pqs14sp.jx379.ps4.api.IConnect4Player;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4Computer;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4Person;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4PlayerType;

/**
 * The factory for getting the player instance
 * 
 * @author jing
 * 
 */
public class Connect4PlayerFactory {
	/**
	 * Prevent constructs a new Connect4PlayerFactory.
	 * 
	 * @throws InstantiationException
	 */
	private Connect4PlayerFactory() throws InstantiationException {
		throw new InstantiationException(
				"Instances of this type are forbidden.");
	}

	/**
	 * Get the player by type.
	 * 
	 * @param type
	 * @return The player.
	 */
	public static IConnect4Player getPlayer(Connect4PlayerType type) {
		if (type == null) {
			throw new IllegalArgumentException(
					"Player type should not be null!");
		} else if (type == Connect4PlayerType.PlayerOne) {
			return new Connect4Person.Builder(type,
					Connect4Config.PLAYER1_COLOR).build();
		} else if (type == Connect4PlayerType.PlayerTwo) {
			return new Connect4Person.Builder(type,
					Connect4Config.PLAYER2_COLOR).build();
		} else {
			return new Connect4Computer.Builder(Connect4Config.COMPUTER_COLOR)
					.build();
		}

	}
}
