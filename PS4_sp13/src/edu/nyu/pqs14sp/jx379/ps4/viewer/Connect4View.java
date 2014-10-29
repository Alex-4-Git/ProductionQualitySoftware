package edu.nyu.pqs14sp.jx379.ps4.viewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import edu.nyu.pqs14sp.jx379.ps4.api.Connect4Listener;
import edu.nyu.pqs14sp.jx379.ps4.api.IConnect4Player;
import edu.nyu.pqs14sp.jx379.ps4.conf.Connect4Config;
import edu.nyu.pqs14sp.jx379.ps4.model.Connect4Model;
import edu.nyu.pqs14sp.jx379.ps4.type.Connect4PlayerType;
import net.miginfocom.swing.MigLayout;

/**
 * The swing viewer of connect4.
 * 
 * @author jing
 * 
 */
public class Connect4View implements Connect4Listener {
	private JLabel message;
	private Connect4Model model;
	private final int ratio = 100 / Connect4Config.CONNECT_4_BOARD_COLUMN;
	private List<CopyOnWriteArrayList<Tile>> board = new CopyOnWriteArrayList<CopyOnWriteArrayList<Tile>>();
	private List<JButton> buttons = new CopyOnWriteArrayList<JButton>();
	private ButtonGroup group;

	public Connect4View(Connect4Model model) {
		this.model = model;
		JFrame frame = new JFrame("Connect4!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MigLayout layout = new MigLayout("fill");
		JPanel panel = new JPanel(layout);
		JLabel modelabel = new JLabel("Game Mode:");

		JRadioButton pvpButton = new JRadioButton("PvP");
		pvpButton.setSelected(true);

		JRadioButton pvcButton = new JRadioButton("PvC");
		// Group the radio buttons.
		group = new ButtonGroup();
		group.add(pvpButton);
		group.add(pvcButton);

		panel.add(modelabel, "w 20%");
		panel.add(pvpButton, "w 40%");
		panel.add(pvcButton, "w 40%,wrap");

		message = new JLabel(
				"Message: Please press any drop button to begin this game.");
		panel.add(message, "w "
				+ (Connect4Config.CONNECT_4_BOARD_COLUMN * ratio) + "%, span "
				+ (Connect4Config.CONNECT_4_BOARD_COLUMN - 1) + "");
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		panel.add(reset, "w " + ratio + "%, wrap");

		for (int j = 0; j < Connect4Config.CONNECT_4_BOARD_COLUMN; j++) {
			JButton b = new JButton("Drop");
			b.setName("" + j);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String buttonname = getActionEventButtonName(e);
					if (!"".equals(buttonname)) {
						Integer currentButton;
						try {
							currentButton = Integer.parseInt(buttonname);
							drop(currentButton);
						} catch (NumberFormatException nfe) {
							throw new IllegalStateException(
									"Button name is not a number.");
						}
					} else {
						// something wrong
						throw new IllegalStateException(
								"Cannot get button name.");
					}
				}
			});
			if (j == Connect4Config.CONNECT_4_BOARD_COLUMN - 1) {
				panel.add(b, "w " + ratio + "%, wrap");
			} else {
				panel.add(b, "w " + ratio + "%");
			}
			buttons.add(b);
		}
		for (int i = 0; i < Connect4Config.CONNECT_4_BOARD_COLUMN; i++) {
			board.add(new CopyOnWriteArrayList<Tile>());
		}
		for (int i = 0; i < Connect4Config.CONNECT_4_BOARD_COLUMN
				* Connect4Config.CONNECT_4_BOARD_ROW; i++) {
			Tile b = new Tile();
			board.get((i % Connect4Config.CONNECT_4_BOARD_COLUMN)).add(0, b);
			if ((i + 1) % Connect4Config.CONNECT_4_BOARD_COLUMN == 0) {
				panel.add(b, "w " + ratio + "%,wrap");
			} else {
				panel.add(b, "w " + ratio + "%");
			}
		}

		// Arrange the components inside the window
		frame.add(panel);
		frame.pack();

		model.addListener(this);
		// By default, the window is not visible. Make it visible.
		frame.setVisible(true);

	}

	/**
	 * The viewer reset function to call model.reset();
	 */
	private void reset() {
		model.reset();
	}

	/**
	 * 
	 * The drop function to call model.drop().
	 * 
	 * @param button
	 */
	private void drop(int button) {
		model.drop(button);
	}

	@Override
	public void gameReset() {
		message.setText("Message: Please press any drop button to begin this game.");
		for (JButton b : buttons) {
			b.setEnabled(true);
		}
		for (CopyOnWriteArrayList<Tile> list : board) {
			for (Tile t : list) {
				t.setBackground(Connect4Config.DEFAULT_COLOR);
			}
		}
		Enumeration<AbstractButton> bg = group.getElements();
		while (bg.hasMoreElements()) {
			AbstractButton b = bg.nextElement();
			b.setEnabled(true);
		}
	}

	@Override
	public void gameWon(IConnect4Player player) {
		for (JButton b : buttons) {
			b.setEnabled(false);
		}
		if (player.getPlayerType() == Connect4PlayerType.PlayerOne) {
			message.setText("Message: Player 1 Win! Reset for new round.");
		} else if (player.getPlayerType() == Connect4PlayerType.PlayerTwo) {
			message.setText("Message: Player 2 Win! Reset for new round.");
		} else {
			message.setText("Message: Computer Win! Reset for new round.");
		}
	}

	@Override
	public void gameDraw() {
		message.setText("Message: Game Draw. Please reset this game.");
	}

	@Override
	public void gameStarted() {
		Enumeration<AbstractButton> bg = group.getElements();
		while (bg.hasMoreElements()) {
			AbstractButton b = bg.nextElement();
			b.setEnabled(false);
			if (b.isSelected()) {
				model.setMode(b.getText());
			}
		}
		message.setText("Message: Game Started. Player 1's turn.");
	}

	@Override
	public void dropPerformed(int button, IConnect4Player player) {
		int i = 0;
		if (player.getPlayerType() == Connect4PlayerType.PlayerOne) {
			for (Tile t : board.get(button)) {
				i++;
				if (Connect4Config.DEFAULT_COLOR.equals(t.getBackground())) {
					t.setBackground(Connect4Config.PLAYER1_COLOR);
					if (i == Connect4Config.CONNECT_4_BOARD_ROW) {
						buttons.get(button).setEnabled(false);
					}
					break;
				}
			}
			message.setText("Message: Player 1 dropped on  column "
					+ (button + 1) + ". Player 2's turn.");
		} else {
			for (Tile t : board.get(button)) {
				i++;
				if (Connect4Config.DEFAULT_COLOR.equals(t.getBackground())) {
					t.setBackground(player.getColor());
					if (i == Connect4Config.CONNECT_4_BOARD_ROW) {
						buttons.get(button).setEnabled(false);
					}
					break;
				}
			}
			if (player.getPlayerType() == Connect4PlayerType.PlayerTwo) {
				message.setText("Message: Player 2 dropped on column "
						+ (button + 1) + ". Player 1's turn.");
			} else {
				message.setText("Message: Computer dropped on column "
						+ (button + 1) + ". Player 1's turn.");
			}
		}
	}

	/**
	 * @param e
	 *            The action event.
	 * @return The button name.
	 */
	private String getActionEventButtonName(ActionEvent e) {
		Object o = e.getSource();
		JButton b = null;
		String buttonText = "";

		if (o instanceof JButton) {
			b = (JButton) o;
		}

		if (b != null) {
			buttonText = b.getName();
		}
		return buttonText;
	}

	/**
	 * The tiles in the board.
	 * 
	 * @author jing
	 * 
	 */
	private class Tile extends JPanel {

		private static final long serialVersionUID = 1L;

		public Tile() {
			setLayout(new BorderLayout());
			setBorder(new LineBorder(Connect4Config.BOARDER_LINE_COLOR));
			setBackground(Connect4Config.DEFAULT_COLOR);
		}

		@Override
		public Dimension getMinimumSize() {
			return getPreferredSize();
		}

		@Override
		public Dimension getMaximumSize() {
			return getPreferredSize();
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(75, 75);
		}
	}

}
