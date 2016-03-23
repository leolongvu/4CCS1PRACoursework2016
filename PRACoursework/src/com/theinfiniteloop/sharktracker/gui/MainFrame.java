package com.theinfiniteloop.sharktracker.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.theinfiniteloop.sharktracker.api.SharkTime;
import com.theinfiniteloop.sharktracker.controller.Controller;

public class MainFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JPanel mainPanel;
	private JButton searchButton;
	private JButton statsButton;
	private JComboBox trackingRangeBox;
	private JComboBox genderBox;
	private JComboBox stageOfLifeBox;
	private JComboBox tagLocationBox;

	private Controller controller;

	public MainFrame(Controller controller) {
		this.controller = controller;
		createPanel();
		createSidePanel();
		createMainPanel();
		createFrame();
	}

	// Create the content pane
	public void createPanel() {
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel acknowledgementLabel = new JLabel(controller.getAcknowledgement());
		contentPane.add(acknowledgementLabel, BorderLayout.SOUTH);
	}

	// Create the frame
	public void createFrame() {
		frame = new JFrame();
		frame.setTitle("Search");
		frame.setContentPane(contentPane);
		frame.setSize(1200, 700);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				setVisible(false);
				controller.setInitialFrameVisibility(true);
			}
		});
	}

	// Create the side panel
	public void createSidePanel() {
		// Side panel
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(Color.white);
		sidePanel.setPreferredSize(new Dimension(300, 700));
		sidePanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color.black)));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

		JLabel sharkTrackerLabel = new JLabel("Shark Tracker");
		sharkTrackerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidePanel.add(sharkTrackerLabel);

		JSeparator separator1 = new JSeparator();
		separator1.setMaximumSize(new Dimension(300, 20));
		sidePanel.add(separator1);

		// Tracking Range
		JLabel trackingRangeLabel = new JLabel("Tracking Range");
		trackingRangeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidePanel.add(trackingRangeLabel);

		trackingRangeBox = new JComboBox();
		trackingRangeBox.setMaximumSize(new Dimension(250, 20));
		trackingRangeBox.addItem("24 Hours");
		trackingRangeBox.addItem("Week");
		trackingRangeBox.addItem("Month");
		sidePanel.add(trackingRangeBox);

		JSeparator separator2 = new JSeparator();
		separator2.setMaximumSize(new Dimension(300, 20));
		sidePanel.add(separator2);

		// Gender
		JLabel genderLabel = new JLabel("Gender");
		genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidePanel.add(genderLabel);

		genderBox = new JComboBox();
		genderBox.setMaximumSize(new Dimension(250, 20));
		genderBox.addItem("All Genders");
		genderBox.addItem("Male");
		genderBox.addItem("Female");
		sidePanel.add(genderBox);

		JSeparator separator3 = new JSeparator();
		separator3.setMaximumSize(new Dimension(300, 20));
		sidePanel.add(separator3);

		// Stage of Life
		JLabel stageOfLifeLabel = new JLabel("Stage of Life");
		stageOfLifeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidePanel.add(stageOfLifeLabel);

		stageOfLifeBox = new JComboBox();
		stageOfLifeBox.setMaximumSize(new Dimension(250, 20));
		stageOfLifeBox.addItem("All Stages of Life");
		stageOfLifeBox.addItem("Mature");
		stageOfLifeBox.addItem("Immature");
		stageOfLifeBox.addItem("Undetermined");
		sidePanel.add(stageOfLifeBox);

		JSeparator separator4 = new JSeparator();
		separator4.setMaximumSize(new Dimension(300, 20));
		sidePanel.add(separator4);

		// Tag Location
		JLabel tagLocationLabel = new JLabel("Tag Location");
		tagLocationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidePanel.add(tagLocationLabel);

		tagLocationBox = new JComboBox();
		tagLocationBox.setMaximumSize(new Dimension(250, 20));

		ArrayList<String> locations = controller.getTagLocations();
		Collections.sort(locations);
		locations.add(0, "All Locations");
		for (String s : locations) {
			tagLocationBox.addItem(s);
		}
		sidePanel.add(tagLocationBox);

		JSeparator separator5 = new JSeparator();
		separator5.setMaximumSize(new Dimension(300, 35));
		sidePanel.add(separator5);

		// Search button
		searchButton = new JButton("Search");
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.setMaximumSize(new Dimension(250, 25));
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});

		sidePanel.add(searchButton);

		JSeparator separator6 = new JSeparator();
		separator6.setMaximumSize(new Dimension(300, 30));
		sidePanel.add(separator6);

		// Stats button
		statsButton = new JButton("Statistics");
		statsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		statsButton.setMaximumSize(new Dimension(250, 25));
		sidePanel.add(statsButton);
		statsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StatisticsFrame stats = new StatisticsFrame(trackingRangeBox.getSelectedItem().toString());
			}
		});

		// Shark of the day
		SharkOfTheDayPanel sharkOTD = new SharkOfTheDayPanel();
		sharkOTD.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidePanel.add(sharkOTD);

		// Shark picture
		ImageIcon icon = new ImageIcon("Shark Logo.jpg");
		Image image = icon.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(image));
		logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidePanel.add(logoLabel);

		// Back button
		JButton backButton = new JButton("Go Back");
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.setMaximumSize(new Dimension(250, 25));
		sidePanel.add(backButton);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controller.setInitialFrameVisibility(true);
			}
		});

		contentPane.add(sidePanel, BorderLayout.WEST);
	}

	public void createMainPanel() {

		// Main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setMaximumSize(new Dimension(900, 700));
		mainPanel.setBackground(Color.white);

		// Scroll pane to show information
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBorder(new MatteBorder(2, 0, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

	public void search() {
		Thread thread = new Thread() {
			public void run() {
				mainPanel.removeAll();
				mainPanel.repaint();
				createLoader();
				searchButton.setEnabled(false);
				searchButton.setText("Searching...");
				controller.searchShark(trackingRangeBox.getSelectedItem().toString(),
						genderBox.getSelectedItem().toString(), stageOfLifeBox.getSelectedItem().toString(),
						tagLocationBox.getSelectedItem().toString());

				ArrayList<SharkTime> sharkFilter = controller.getSharkList();
				mainPanel.removeAll();
				if (sharkFilter.size() == 0) {
					noSharkFound();
				} else {
					System.out.println("Shark Count: " + sharkFilter.size());
					for (SharkTime s : sharkFilter) {
						SharkPanel sharkPanel = new SharkPanel(s, controller);
						mainPanel.add(sharkPanel);
					}
				}
				mainPanel.revalidate();
				searchButton.setText("Search");
				searchButton.setEnabled(true);
			}
		};
		thread.start();
	}

	public void selectedShark(SharkTime s) {
		mainPanel.removeAll();
		mainPanel.add(new SharkPanel(s, controller));

		ImageIcon icon = new ImageIcon("Sharks/" + s.getShark().getSpecies() + ".jpg");
		Image image = icon.getImage().getScaledInstance(850, 350, java.awt.Image.SCALE_SMOOTH);
		JLabel sharkPic = new JLabel(new ImageIcon(image));

		sharkPic.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK));
		sharkPic.setAlignmentX(Component.CENTER_ALIGNMENT);

		mainPanel.add(sharkPic);
		mainPanel.revalidate();
	}
	
	public void showSearch(){
		ImageIcon loaderPicture = new ImageIcon("Search.png");
		JLabel searchPic = new JLabel(loaderPicture);
		searchPic.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.removeAll();
		mainPanel.add(searchPic);
		mainPanel.repaint();
	}

	public void createLoader() {
		ImageIcon loaderPicture = new ImageIcon("Loader.gif");
		JLabel loader = new JLabel(loaderPicture);
		loader.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(loader);
		mainPanel.repaint();
	}

	public void noSharkFound() {
		ImageIcon icon = new ImageIcon("No Shark.jpg");
		Image image = icon.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
		JLabel noShark = new JLabel(new ImageIcon(image));
		noShark.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(noShark);
		mainPanel.repaint();
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
}
