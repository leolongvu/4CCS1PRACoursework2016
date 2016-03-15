package com.theinfiniteloop.sharktracker.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;

public class SharkPanel extends JPanel {

	public SharkPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		JPanel infoPanel = new JPanel();
		add(infoPanel);
		infoPanel.setMaximumSize(new Dimension(700,70));
		infoPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblName = new JLabel("Name");
		infoPanel.add(lblName);

		JLabel lblNamebox = new JLabel("NameBox");
		infoPanel.add(lblNamebox);

		JLabel lblGender = new JLabel("Gender");
		infoPanel.add(lblGender);

		JLabel lblGenderbox = new JLabel("GenderBox");
		infoPanel.add(lblGenderbox);

		JLabel lblStageOfLife = new JLabel("Stage of Life");
		infoPanel.add(lblStageOfLife);

		JLabel lblStageoflifebox = new JLabel("StageOfLifeBox");
		infoPanel.add(lblStageoflifebox);

		JLabel lblSpecies = new JLabel("Species");
		infoPanel.add(lblSpecies);

		JLabel lblSpeciesbox = new JLabel("SpeciesBox");
		infoPanel.add(lblSpeciesbox);

		JLabel lblLength = new JLabel("Length");
		infoPanel.add(lblLength);

		JLabel lblLengthbox = new JLabel("LengthBox");
		infoPanel.add(lblLengthbox);

		JLabel lblWeight = new JLabel("Weight");
		infoPanel.add(lblWeight);

		JLabel lblWeightbox = new JLabel("WeightBox");
		infoPanel.add(lblWeightbox);

		JLabel lblDescription = new JLabel("Description");
		infoPanel.add(lblDescription);

		JPanel descriptionPanel = new JPanel();
		add(descriptionPanel);
		descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
		
		JTextPane textPane = new JTextPane();
		textPane.setMaximumSize(new Dimension(700,200));
		descriptionPanel.add(textPane);

		JPanel pingPanel = new JPanel();
		add(pingPanel);

		JLabel lblLastPing = new JLabel("Last Ping: ");
		pingPanel.add(lblLastPing);

		JLabel lblPing = new JLabel("Ping");
		pingPanel.add(lblPing);

		JButton btnFollow = new JButton("Follow");
		pingPanel.add(btnFollow);

	}
}
