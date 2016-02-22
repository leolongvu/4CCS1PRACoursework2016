package com.theinfiniteloops.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class InitialFrame {

	private JButton searchButton;
	private JButton favsButton;
	private JLabel logo;
	private JLabel label;

	public InitialFrame() {

		JFrame frame = new JFrame();
		frame.setTitle("Shark Tracker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);

		BufferedImage logoPicture = null;
		try {
			logoPicture = ImageIO.read(new File("Shark Logo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		logo = new JLabel(new ImageIcon(logoPicture.getScaledInstance(200, 200, logoPicture.SCALE_DEFAULT)));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		label = new JLabel("Shark Tracker");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		Border border = BorderFactory.createLineBorder(Color.black, 2);

		searchButton = new JButton("Search");
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.setMaximumSize(new Dimension(250, 50));
		searchButton.setBorder(border);
		searchButton.setBackground(Color.white);
		
		favsButton = new JButton("Favourites");
		favsButton.setEnabled(false);
		favsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		favsButton.setMaximumSize(new Dimension(250, 50));
		favsButton.setBorder(border);
		favsButton.setBackground(Color.white);

		frame.setLayout(boxLayout);
		frame.add(logo);
		frame.add(label);
		frame.add(new Box.Filler(new Dimension(10, 20), new Dimension(10, 20), new Dimension(10, 20)));
		frame.add(searchButton);
		frame.add(new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10)));
		frame.add(favsButton);
		
		frame.getContentPane().setBackground(Color.white);
		frame.setSize(new Dimension(300, 400));
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		InitialFrame i = new InitialFrame();
	}
}
