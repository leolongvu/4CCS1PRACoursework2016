package com.theinfiniteloop.sharktracker.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * @author Raf, the infinite loop. This class adds a splash screen when the
 *         program is first run
 *
 */
public class LoadingScreen {

	private JWindow window;
	private JLabel timer;
	private boolean stopTimer;
	private static DecimalFormat df = new DecimalFormat(".#");

	/**
	 * constructor for this, creates a new loading screen
	 */
	public LoadingScreen() {
		window = new JWindow();

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JLabel logo = new JLabel(new ImageIcon("images/Splash.gif"));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(logo);

		Font font = new Font("Impact", Font.ITALIC, 40);
		JLabel text = new JLabel("Loading...");
		text.setFont(font);
		text.setForeground(Color.black);
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(text);

		timer = new JLabel();
		timer.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(timer);

		addTimer();

		contentPane.setBackground(Color.white);
		contentPane.setBorder(BorderFactory.createLineBorder(Color.black, 5));

		window.setContentPane(contentPane);
		window.setSize(new Dimension(310, 410));
		window.setLocationRelativeTo(null);
		window.setAlwaysOnTop(true);
		window.setVisible(true);
	}

	/**
	 * method to remove the loading screen
	 */
	public void stopLoading() {
		stopTimer = false;
		window.dispose();
	}

	/**
	 * adds a timer to the loading screen
	 */
	private void addTimer() {
		long startTime = System.currentTimeMillis();
		stopTimer = true;
		Thread thread = new Thread() {
			public void run() {
				while (stopTimer = true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					timer.setText(("" + df.format((System.currentTimeMillis() - startTime) / 1000.000)) + "s");
					timer.repaint();
				}
			}
		};
		thread.start();
	}
}
