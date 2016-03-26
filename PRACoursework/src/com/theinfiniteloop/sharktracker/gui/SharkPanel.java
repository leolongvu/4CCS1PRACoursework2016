package com.theinfiniteloop.sharktracker.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;

import com.theinfiniteloop.sharktracker.api.SharkTime;
import com.theinfiniteloop.sharktracker.controller.Controller;

/**
 * @author Raf, the infinite loop. This class is a panel to display the
 *         information of a shark
 *
 */
public class SharkPanel extends JPanel {

	private Controller controller;

	/**
	 * creates a shark panel
	 * 
	 * @param s
	 *            which is a sharktime object
	 * @param controller
	 *            is the reference to the controller
	 */
	public SharkPanel(SharkTime s, Controller controller) {
		this.controller = controller;
		setSize(new Dimension(900, 300));
		setMinimumSize(new Dimension(900, 300));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new MatteBorder(1, 2, 1, 2, Color.BLACK));

		JPanel infoPanel = new JPanel();
		infoPanel.setMaximumSize(new Dimension(850, 100));
		add(infoPanel);
		infoPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblName = new JLabel("Name:");
		infoPanel.add(lblName);

		JLabel lblNamebox = new JLabel(s.getShark().getName());
		infoPanel.add(lblNamebox);

		JLabel lblGender = new JLabel("Gender:");
		infoPanel.add(lblGender);

		JLabel lblGenderbox = new JLabel(s.getShark().getGender());
		infoPanel.add(lblGenderbox);

		JLabel lblStageOfLife = new JLabel("Stage of Life:");
		infoPanel.add(lblStageOfLife);

		JLabel lblStageoflifebox = new JLabel(s.getShark().getStageOfLife());
		infoPanel.add(lblStageoflifebox);

		JLabel lblSpecies = new JLabel("Species:");
		infoPanel.add(lblSpecies);

		JLabel lblSpeciesbox = new JLabel(s.getShark().getSpecies());
		infoPanel.add(lblSpeciesbox);

		JLabel lblLength = new JLabel("Length:");
		infoPanel.add(lblLength);

		JLabel lblLengthbox = new JLabel(s.getShark().getLength());
		infoPanel.add(lblLengthbox);

		JLabel lblWeight = new JLabel("Weight:");
		infoPanel.add(lblWeight);

		JLabel lblWeightbox = new JLabel(s.getShark().getWeight());
		infoPanel.add(lblWeightbox);

		JLabel lblDescription = new JLabel("Description:");
		infoPanel.add(lblDescription);
		lblDescription.setMaximumSize(new Dimension(55, 15));
		lblDescription.setAlignmentX(Component.CENTER_ALIGNMENT);

		JTextArea textPane = new JTextArea();
		textPane.setMaximumSize(new Dimension(850, 150));
		textPane.setMinimumSize(new Dimension(850, 150));
		textPane.setText(s.getShark().getDescription());
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true);
		textPane.setEditable(false);
		textPane.setOpaque(false);
		add(textPane);

		JPanel pingPanel = new JPanel();
		pingPanel.setMaximumSize(new Dimension(850, 35));
		add(pingPanel);
		pingPanel.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblLastPing = new JLabel("Last Ping: ");
		pingPanel.add(lblLastPing);

		JLabel lblPing = new JLabel(s.getTime());
		pingPanel.add(lblPing);

		// action for the follow button, tells the controller to follow
		// or un follow a shark
		JButton btnFollow = new JButton();
		if (controller.checkFavourite(s.getShark()) == -1) {
			btnFollow.setText("Follow");
		} else {
			btnFollow.setText("Following");
		}
		pingPanel.add(btnFollow);

		btnFollow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnFollow.getText().equals("Follow")) {
					controller.addFavourite(s.getShark());
					btnFollow.setText("Following");
					revalidate();
				} else {
					controller.removeFavourite(s.getShark());
					btnFollow.setText("Follow");
					revalidate();
				}
			}
		});
	}
}
