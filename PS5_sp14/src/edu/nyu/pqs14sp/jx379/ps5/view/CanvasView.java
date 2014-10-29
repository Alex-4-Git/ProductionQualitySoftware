package edu.nyu.pqs14sp.jx379.ps5.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import net.miginfocom.swing.MigLayout;
import edu.nyu.pqs14sp.jx379.ps5.api.CanvasAPI;
import edu.nyu.pqs14sp.jx379.ps5.controller.CanvasController;
import edu.nyu.pqs14sp.jx379.ps5.model.CanvasModel;
import edu.nyu.pqs14sp.jx379.ps5.pojo.Dot;

/**
 * @author jing
 * 
 * The Canvas View in MVC pattern.
 * 
 * For display all function in view.
 *
 */
public class CanvasView implements CanvasAPI, MouseInputListener,
		MouseMotionListener, ActionListener {
	private CanvasController controller;
	private JFrame frame;
	private JLabel message;
	private JButton button;
	private JLabel label;
	private BufferedImage canvas;
	private int height = 500;
	private int width = 500;
	private boolean started = false;
	private Color background = Color.white;
	private Color frontColor = Color.black;

	public CanvasView(CanvasModel model) {
		controller = new CanvasController(model);
		model.addListener(this);

		frame = new JFrame("Canvas!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MigLayout layout = new MigLayout("fill");
		JPanel panel = new JPanel(layout);
		//use BufferedImage to draw.
		canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) canvas.getGraphics();
		g.setColor(background);
		g.fillRect(0, 0, height, width);
		//add this BufferedImage to JLabel.
		label = new JLabel(new ImageIcon(canvas));
		panel.add(label, "span 2 1, wrap");

		//Message bar.
		message = new JLabel("Message: Please press Start to begin.");

		panel.add(message, "w 80%");
		
		//Start/Reset Button.
		button = new JButton("Start");
		
		button.addActionListener(this);
		panel.add(button, "w 20%,wrap");

		frame.addMouseMotionListener(this);
		frame.addMouseListener(this);

		// Arrange the components inside the window
		frame.add(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	@Override
	public void start() {
		started = true;
		button.setText("Reset");
		message.setText("Message: Please press your mouse to draw. 'Reset' for clear all.");
	}

	@Override
	public void reset() {
		if (started) {
			//To clear all draw.
			message.setText("Message: Please press your mouse to draw. 'Reset' for clear all.");
			Graphics2D g = canvas.createGraphics();
			g.setColor(background);
			g.fillRect(0, 0, height, width);
			g.dispose();
			label.repaint();
		}
	}

	@Override
	public void addDot(int x, int y) {
		if (started) {
			//Adding dot by mouse movement.
			controller.addDot(x, y);
		}
	}

	@Override
	public void paint(List<List<Dot>> draws) {
		if (started) {
			//To draw lines by mouse movement.
			Graphics2D g2 = canvas.createGraphics();
			g2.setColor(frontColor);
			for (List<Dot> dotList : draws) {
				if (dotList.size() > 1) {
					for (int i = 0; i < dotList.size() - 1; i++) {
						g2.drawLine(dotList.get(i).getX(), dotList.get(i)
								.getY(), dotList.get(i + 1).getX(), dotList
								.get(i + 1).getY());
					}
				}
			}
			g2.dispose();
			label.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//Mouse release action means one line finished.
		keyReleased();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		addDot(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			if (button.getText() == "Start") {
				controller.start();
			} else if (button.getText() == "Reset") {
				controller.reset();
			}
		}
	}

	@Override
	public void keyReleased() {
		controller.keyReleased();
	}

}
