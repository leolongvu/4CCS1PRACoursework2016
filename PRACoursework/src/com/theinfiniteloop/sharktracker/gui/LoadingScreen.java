package com.theinfiniteloop.sharktracker.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class LoadingScreen {

	private JWindow window;
	private JLabel timer;
	private boolean stopTimer;

	public LoadingScreen() {
		window = new JWindow();
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JLabel logo = new JLabel(new ImageIcon("Splash.gif"));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(logo);
		Font font = new Font("Comic Sans MS", Font.BOLD, 40);
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
		window.setVisible(true);
	}

	public void stopLoading() {
		stopTimer = false;
		window.dispose();
	}

	public void addTimer() {
		long startTime = System.currentTimeMillis();
		stopTimer = true;
		Thread thread = new Thread() {
			public void run() {
				while (stopTimer = true) {
					timer.setText("" + (System.currentTimeMillis() - startTime));
					timer.repaint();
				}
			}
		};
		thread.start();
	}
}
