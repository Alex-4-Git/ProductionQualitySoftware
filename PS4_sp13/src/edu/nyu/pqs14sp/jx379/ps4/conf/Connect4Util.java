package edu.nyu.pqs14sp.jx379.ps4.conf;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.pqs14sp.jx379.ps4.api.IConnect4Player;

/**
 * Connect4Util used to check the board win or not.
 * 
 * @author jing
 *
 */
public class Connect4Util {
	/**
	 * Prevent constructs a new Connect4Util.
	 * 
	 * @throws InstantiationException
	 */
	private Connect4Util() throws InstantiationException {
		throw new InstantiationException(
				"Instances of this type are forbidden.");
	}

	/**
	 * Check the board has win or not.
	 * 
	 * @param board
	 * @return	Is this board win or not.
	 */
	public static boolean checkConnect4Won(
			List<CopyOnWriteArrayList<IConnect4Player>> board) {
		// check vertical
		for (int i = 0; i < board.size(); i++) {
			CopyOnWriteArrayList<IConnect4Player> list = board.get(i);
			if (list.size() >= 4) {
				for (int j = 3; j < list.size(); j++) {
					if ((list.get(j).getPlayerType() == list.get(j - 1)
							.getPlayerType())
							&& (list.get(j).getPlayerType() == list.get(j - 2)
									.getPlayerType())
							&& (list.get(j).getPlayerType() == list.get(j - 3)
									.getPlayerType())) {
						return true;
					}
				}
			}
		}
		// check horizon
		for (int i = 0; i < board.size() - 3; i++) {
			CopyOnWriteArrayList<IConnect4Player> list1 = board.get(i);
			CopyOnWriteArrayList<IConnect4Player> list2 = board.get(i + 1);
			CopyOnWriteArrayList<IConnect4Player> list3 = board.get(i + 2);
			CopyOnWriteArrayList<IConnect4Player> list4 = board.get(i + 3);
			for (int j = 0; j < list1.size(); j++) {
				if (list2.size() > j
						&& (list1.get(j).getPlayerType() == list2.get(j)
								.getPlayerType())
						&& list3.size() > j
						&& (list1.get(j).getPlayerType() == list3.get(j)
								.getPlayerType())
						&& list4.size() > j
						&& (list1.get(j).getPlayerType() == list4.get(j)
								.getPlayerType())) {
					return true;
				}
			}
		}
		// check diagonal
		for (int i = 0; i < board.size() - 3; i++) {
			CopyOnWriteArrayList<IConnect4Player> list1 = board.get(i);
			CopyOnWriteArrayList<IConnect4Player> list2 = board.get(i + 1);
			CopyOnWriteArrayList<IConnect4Player> list3 = board.get(i + 2);
			CopyOnWriteArrayList<IConnect4Player> list4 = board.get(i + 3);
			for (int j = 0; j < list1.size(); j++) {
				if ((j + 3) < Connect4Config.CONNECT_4_BOARD_COLUMN) {
					if (list2.size() > j + 1
							&& (list1.get(j).getPlayerType() == list2
									.get(j + 1).getPlayerType())
							&& list3.size() > j + 2
							&& (list1.get(j).getPlayerType() == list3
									.get(j + 2).getPlayerType())
							&& list4.size() > j + 3
							&& (list1.get(j).getPlayerType() == list4
									.get(j + 3).getPlayerType())) {
						return true;
					}
				}
				if ((j - 3) >= 0) {
					if (list2.size() > j - 1
							&& (list1.get(j).getPlayerType() == list2
									.get(j - 1).getPlayerType())
							&& list3.size() > j - 2
							&& (list1.get(j).getPlayerType() == list3
									.get(j - 2).getPlayerType())
							&& list4.size() > j - 3
							&& (list1.get(j).getPlayerType() == list4
									.get(j - 3).getPlayerType())) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
