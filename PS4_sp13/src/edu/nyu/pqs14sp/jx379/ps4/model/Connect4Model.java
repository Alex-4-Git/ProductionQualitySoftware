package edu.nyu.pqs14sp.jx379.ps4.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.pqs14sp.jx379.ps4.api.Connect4Listener;
import edu.nyu.pqs14sp.jx379.ps4.api.IConnect4Player;
import edu.nyu.pqs14sp.jx379.ps4.conf.Connect4Config;
import edu.nyu.pqs14sp.jx379.ps4.conf.Connect4PlayerFactory;
import edu.nyu.pqs14sp.jx379.ps4.conf.Connect4Util;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4Computer;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4GameMode;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4PlayerType;

/**
 * The model of connect 4 game.
 * 
 * @author jing
 *
 */
public class Connect4Model {

	private boolean gameStarted;
	private IConnect4Player player;
	private Connect4GameMode mode;
	private List<Connect4Listener> listeners;
	private List<CopyOnWriteArrayList<IConnect4Player>> board;
	private int steps;

	/**
	 * The constructor for the connect 4 model.
	 */
	public Connect4Model() {
		gameStarted = false;
		player = Connect4PlayerFactory.getPlayer(Connect4PlayerType.PlayerOne);
		listeners = new CopyOnWriteArrayList<Connect4Listener>();
		board = new CopyOnWriteArrayList<CopyOnWriteArrayList<IConnect4Player>>();
		steps = 0;
		mode = Connect4GameMode.PvP;
		for (int i = 0; i < Connect4Config.CONNECT_4_BOARD_COLUMN; i++) {
			board.add(new CopyOnWriteArrayList<IConnect4Player>());
		}
	}

	/**
	 * Observer pattern for adding listener.
	 * 
	 * @param listener
	 */
	public void addListener(Connect4Listener listener) {
		listeners.add(listener);
	}

	/**
	 * Observer pattern for removing listener.
	 * 
	 * @param listener
	 */
	public void removeListener(Connect4Listener listener) {
		listeners.remove(listener);
	}

	/**
	 * The drop action.
	 * 
	 * @param buttonNum	The column to drop
	 */
	public void drop(int buttonNum) {
		if (gameStarted) {
			List<IConnect4Player> current = board.get(buttonNum);
			if (current.size() < Connect4Config.CONNECT_4_BOARD_ROW) {
				current.add(player);
			} else {
				// something wrong
				throw new IllegalStateException("Cannot drop in full column.");
			}
			fireDropPerformedEvent(buttonNum, player);
			steps++;
			if (Connect4Util.checkConnect4Won(board)) {
				fireGameWonEvent(player);
			} else {
				if (steps == Connect4Config.CONNECT_4_BOARD_ROW
						* Connect4Config.CONNECT_4_BOARD_COLUMN) {
					fireGameDrawEvent();
				} else {
					// switch player.
					if (player.getPlayerType() == Connect4PlayerType.PlayerOne) {
						if (mode == Connect4GameMode.PvP) {
							player = Connect4PlayerFactory
									.getPlayer(Connect4PlayerType.PlayerTwo);
						} else {
							player = Connect4PlayerFactory
									.getPlayer(Connect4PlayerType.Computer);
							if (player instanceof Connect4Computer) {
								Connect4Computer computer = (Connect4Computer) player;
								int drop = computer.getNextStep(board);
								drop(drop);
							}
						}
					} else {
						player = Connect4PlayerFactory
								.getPlayer(Connect4PlayerType.PlayerOne);
					}
				}
			}
		}
		if (!gameStarted) {
			gameStarted = true;
			fireGameStartEvent();
		}
	}
	

	/**
	 * 
	 * Set the game mode, PvP or PvC.
	 * @param text The mode text
	 */
	public void setMode(String text) {
		if ("PvP".equals(text)) {
			mode = Connect4GameMode.PvP;
		} else {
			mode = Connect4GameMode.PvC;
		}
	}

	/**
	 * The reset action.
	 */
	public void reset() {
		gameStarted = false;
		player = Connect4PlayerFactory.getPlayer(Connect4PlayerType.PlayerOne);
		board = new CopyOnWriteArrayList<CopyOnWriteArrayList<IConnect4Player>>();
		steps = 0;
		for (int i = 0; i < Connect4Config.CONNECT_4_BOARD_COLUMN; i++) {
			board.add(new CopyOnWriteArrayList<IConnect4Player>());
		}
		fireGameResetEvent();
	}

	/**
	 * Notify the reset event.
	 */
	private void fireGameResetEvent() {
		for (Connect4Listener listener : listeners) {
			listener.gameReset();
		}
	}

	/**
	 * Notify the start event.
	 */
	private void fireGameStartEvent() {
		for (Connect4Listener listener : listeners) {
			listener.gameStarted();
		}
	}

	/**
	 * Notify the draw event.
	 */
	private void fireGameDrawEvent() {
		for (Connect4Listener listener : listeners) {
			listener.gameDraw();
		}
	}

	/**
	 * Notify the win event.
	 */
	private void fireGameWonEvent(IConnect4Player player) {
		for (Connect4Listener listener : listeners) {
			listener.gameWon(player);
		}
	}

	/**
	 * Notify the drop event.
	 */
	private void fireDropPerformedEvent(int buttonNum, IConnect4Player player2) {
		for (Connect4Listener listener : listeners) {
			listener.dropPerformed(buttonNum, player2);
		}
	}

}
