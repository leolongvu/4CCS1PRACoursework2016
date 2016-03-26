package com.theinfiniteloop.sharktracker.gui.map;

import javax.swing.JPanel;

import api.jaws.Shark;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * @author Leo, the infinite loop. This class is for showing a small panel
 *         holding the information about a shark, and is visible when clicking
 *         or hovering over a pin on the map
 *
 */
public class SharkMapPanel extends JPanel {

	private JLayeredPane layeredPane;

	/**
	 * creates the panel for a shark on the map
	 * 
	 * @param shark
	 */
	public SharkMapPanel(Shark shark) {
		setOpaque(false);
		layeredPane = new JLayeredPane();
		setLayout(new BorderLayout());
		add(layeredPane, BorderLayout.CENTER);

		ImageIcon map = new ImageIcon("images/Map/SharkMap.png");
		Image imageMap = map.getImage().getScaledInstance(350, 136, java.awt.Image.SCALE_SMOOTH);
		JLabel mapLabel = new JLabel(new ImageIcon(imageMap));
		mapLabel.setBounds(0, 0, 350, 136);
		layeredPane.add(mapLabel, new Integer(0), 0);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setLayout(new GridLayout(6, 1));

		Font font = new Font("Comic Sans MS", Font.BOLD, 14);

		JLabel name = new JLabel("Name: " + shark.getName());
		name.setForeground(Color.WHITE);
		name.setFont(font);
		panel_1.add(name);

		JLabel gender = new JLabel("Gender: " + shark.getGender());
		gender.setForeground(Color.WHITE);
		gender.setFont(font);
		panel_1.add(gender);

		JLabel stage = new JLabel("Age: " + shark.getStageOfLife());
		stage.setForeground(Color.WHITE);
		stage.setFont(font);
		panel_1.add(stage);

		JLabel spi = new JLabel("Species: " + shark.getSpecies());
		spi.setForeground(Color.WHITE);
		spi.setFont(font);
		panel_1.add(spi);

		JLabel weight = new JLabel("Weight: " + shark.getWeight());
		weight.setForeground(Color.WHITE);
		weight.setFont(font);
		panel_1.add(weight);

		JLabel length = new JLabel("Length: " + shark.getLength());
		length.setForeground(Color.WHITE);
		length.setFont(font);
		panel_1.add(length);

		panel_1.setBounds(140, 11, 190, 110);
		layeredPane.add(panel_1, new Integer(1), 0);

		switch (shark.getSpecies()) {
		case "White Shark (Carcharodon carcharias)":
			setImage("images/Map/WhiteShark.jpg");
			break;
		case "Tiger Shark  (Galeocerdo cuvier)":
			setImage("images/Map/TigerShark.jpg");
			break;
		case "Hammerhead Shark (Sphyrna)":
			setImage("images/Map/Hammerhead.jpg");
			break;
		case "Mako Shark (Isurus oxyrinchus)":
			setImage("images/Map/MakoShark.jpg");
			break;
		}
	}

	/**
	 * sets the image of a shark species
	 * 
	 * @param image
	 */
	private void setImage(String image) {
		ImageIcon sharkIcon = new ImageIcon(image);
		Image imageShark = sharkIcon.getImage().getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
		JLabel sharkLabel = new JLabel(new ImageIcon(imageShark));
		sharkLabel.setBounds(18, 13, 110, 110);
		layeredPane.add(sharkLabel, new Integer(1), 0);
	}
}
