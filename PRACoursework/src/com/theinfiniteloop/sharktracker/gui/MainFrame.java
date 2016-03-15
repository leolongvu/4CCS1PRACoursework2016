package com.theinfiniteloop.sharktracker.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.theinfiniteloop.sharktracker.api.Query;
import com.theinfiniteloop.sharktracker.api.SharkTime;

public class MainFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JButton searchButton;
	private JButton favouritesButton;
	
	private Query query;
	private ArrayList<SharkTime> sharkFilter; 

	public MainFrame() {
		query = new Query();
		sharkFilter = new ArrayList<SharkTime>();
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
		JLabel acknowledgementLabel = new JLabel(query.getAcknowledgement());
		contentPane.add(acknowledgementLabel, BorderLayout.SOUTH);
	}

	// Create the frame
	public void createFrame() {
		
		frame = new JFrame();
		frame.setTitle("Search");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(contentPane);
		frame.setSize(1200, 700);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void createSidePanel() {

		// Side panel
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(Color.white);
		sidePanel.setPreferredSize(new Dimension(300, 700));
		sidePanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
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

		JComboBox trackingRangeBox = new JComboBox();
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

		JComboBox genderBox = new JComboBox();
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

		JComboBox stageOfLifeBox = new JComboBox();
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

		JComboBox tagLocationBox = new JComboBox();
		tagLocationBox.setMaximumSize(new Dimension(250, 20));
		String[] Locations = { "All Locations", "Batt Reef, Australia", "Cairns", "Cape Cod", "Cape Town", "Chile",
				"Darwin Arch, Galápagos Islands", "Durban", "False Bay", "Fernado de Noronha",
				"Fraser Island, Australia", "Galapagos Islands", "Gansbaai", "Itabaca Channel, Galapagos Islands",
				"Montauk, NY", "Mosquera Island, Galapagos Islands", "Mossel Bay", "Ningaloo Reef, Australia",
				"North Central Gulf of Mexico", "Playa Millonario Baltra, Galapagos Islands", "Port Aransas, TX",
				"Port Edward", "Port Royal Sound, South Carolina", "South Maui", "Struisbaai",
				"Whale Rock Beach, Plettenberg Bay", "Wolf Island, Galapagos Islands" };
		for (int i = 0; i < (Locations.length); i++) {
			tagLocationBox.addItem(Locations[i]);
		}
		sidePanel.add(tagLocationBox);

		JSeparator separator5 = new JSeparator();
		separator5.setMaximumSize(new Dimension(300, 35));
		sidePanel.add(separator5);

		// Search button
		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				sharkFilter = query.implementAllSearch(trackingRangeBox.getSelectedItem().toString(), 
						genderBox.getSelectedItem().toString(), stageOfLifeBox.getSelectedItem().toString(), 
						tagLocationBox.getSelectedItem().toString());
			}			
		});
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.setMaximumSize(new Dimension(250, 25));
		sidePanel.add(searchButton);

		JSeparator separator6 = new JSeparator();
		separator6.setMaximumSize(new Dimension(300, 10));
		sidePanel.add(separator6);

		// Shark logo
		JLabel logo = new JLabel("New label");
		BufferedImage logoPicture = null;
		try {
			logoPicture = ImageIO.read(new File("Shark Logo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo = new JLabel(new ImageIcon(logoPicture.getScaledInstance(300, 300, logoPicture.SCALE_DEFAULT)));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidePanel.add(logo);

		// Favourites button
		favouritesButton = new JButton("Favourites");
		favouritesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		favouritesButton.setMaximumSize(new Dimension(250, 25));
		favouritesButton.setEnabled(false);
		sidePanel.add(favouritesButton);

		// Action listener for the favourites button to open the favourites
		// frame
		favouritesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FavouritesFrame favouritesFrame = new FavouritesFrame();
				frame.dispose();
			}
		});

		contentPane.add(sidePanel, BorderLayout.WEST);
	}

	public void createMainPanel() {

		// Main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(3, 0, 3, 3, (Color) new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) mainPanel.getLayout();
		mainPanel.setPreferredSize(new Dimension(700, 700));

		// Scroll pane to show information
		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane);

		contentPane.add(mainPanel, BorderLayout.CENTER);

	}

	// Method to enable the favourites button, to be used later when the
	// favourites list is not empty
	public void enableFavourites() {
		favouritesButton.setEnabled(true);
	}
}
