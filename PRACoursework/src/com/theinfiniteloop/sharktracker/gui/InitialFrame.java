package com.theinfiniteloop.sharktracker.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import com.theinfiniteloop.sharktracker.controller.Controller;

public class InitialFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JButton favouritesButton;
	
	private Controller controller;

	public InitialFrame(Controller controller) {
		this.controller = controller;
		createPanel();
		createLabels();
		createButtons();
		createFrame();
	}

	// Create the content pane
	public void createPanel() {
		
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBackground(Color.white);
	}
	
	// Create the frame
	public void createFrame() {
		frame = new JFrame();
		frame.setTitle("Shark Tracker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(contentPane);
		frame.setSize(new Dimension(300, 400));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	
	// Creates the logo and label
	public void createLabels() {
		BufferedImage logoPicture = null;
		try {
			logoPicture = ImageIO.read(new File("Shark Logo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel logo = new JLabel(new ImageIcon(logoPicture.getScaledInstance(200, 200, logoPicture.SCALE_DEFAULT)));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(logo);

		JLabel label = new JLabel("Shark Tracker");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(label);

		contentPane.add(new Box.Filler(new Dimension(10, 20), new Dimension(10, 20), new Dimension(10, 20)));
	}

	// Creates the search and favourites buttons
	public void createButtons() {
		Border border = BorderFactory.createLineBorder(Color.black, 2);

		JButton searchButton = new JButton("Search");
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.setMaximumSize(new Dimension(250, 50));
		searchButton.setBorder(border);
		searchButton.setBackground(Color.white);

		// Action listener for the search button to open the main frame
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame mainFrame = new MainFrame(controller);
				frame.dispose();
			}
		});
		contentPane.add(searchButton);
		contentPane.add(new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10)));

		favouritesButton = new JButton("Favourites");
		favouritesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		favouritesButton.setMaximumSize(new Dimension(250, 50));
		favouritesButton.setBorder(border);
		favouritesButton.setBackground(Color.white);
		favouritesButton.setEnabled(false);

		// Action listener for the favourites button to open the favourites
		// frame
		favouritesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FavouritesFrame favouritesFrame = new FavouritesFrame();
				frame.dispose();
			}
		});
		contentPane.add(favouritesButton);
	}

	// Method to enable the favourites button, to be used later when the
	// favourites list is not empty
	public void enableFavourites() {
		favouritesButton.setEnabled(true);
	}
}
