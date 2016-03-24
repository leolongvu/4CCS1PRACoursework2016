package com.theinfiniteloop.sharktracker.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.theinfiniteloop.sharktracker.api.SharkLocation;
import com.theinfiniteloop.sharktracker.controller.Controller;

public class MapExtension extends JFrame {
	private JLayeredPane mapPanel;
	private JPanel panel;
	private JLabel coordinates;
	private Controller controller;

	public MapExtension(Controller controller) {
		this.controller = controller;
		mapPanel = new JLayeredPane();
		mapPanel.setLayout(null);
		mapPanel.setPreferredSize(new Dimension(200, 0));
		mapPanel.setBackground(Color.blue);
		mapPanel.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				coordinates.setText(((arg0.getX() * 360 / 1366.0) - 180) + " ; " + (90 - ((arg0.getY() * 180 / 683.0))));
				coordinates.revalidate();
			}
		});

		ImageIcon map = new ImageIcon("Earth Map.jpg");
		Image imageMap = map.getImage().getScaledInstance(1366, 683, java.awt.Image.SCALE_SMOOTH);
		JLabel mapLabel = new JLabel(new ImageIcon(imageMap));

		JPanel layerOne = new JPanel();
		layerOne.add(mapLabel);
		layerOne.setBounds(0, 0, 1366, 683);
		mapPanel.add(layerOne, new Integer(0), 0);

		Font font = new Font("Comic Sans MS", Font.BOLD, 14);
		coordinates = new JLabel("0:0");
		coordinates.setFont(font);
		coordinates.setForeground(Color.WHITE);
		coordinates.setBounds(25, 576, 500, 20);
		mapPanel.add(coordinates, new Integer(1), 0);
		
		ImageIcon compass = new ImageIcon("Compass.png");
		Image compassImage = compass.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		JLabel compassLabel = new JLabel(new ImageIcon(compassImage));
		compassLabel.setBounds(30, 450, 100, 100);
		mapPanel.add(compassLabel, new Integer(1), 0);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(mapPanel, BorderLayout.CENTER);
		
		addSharkPin();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(40, 40, 1366, 734);
		this.setContentPane(panel);
		this.setVisible(true);
		this.setTitle("Map of the sharks");
	}

	private void addSharkPin() {
		for (SharkLocation s : controller.getFavouriteSharkList()) {
			double convertedLon = s.getLon() + 180;
			double convertedLat = 90 - s.getLat();

			int x = (int) (convertedLon * 1366 / 360);
			int y = (int) (convertedLat * 683 / 180);

			ImageIcon pin = new ImageIcon("Pin.png");
			Image pinImage = pin.getImage().getScaledInstance(30, 48, java.awt.Image.SCALE_SMOOTH);
			JLabel pinLabel = new JLabel(new ImageIcon(pinImage));
			pinLabel.setBounds(x - 17, y - 57, 34, 57);
			mapPanel.add(pinLabel, new Integer(1), 0);

		}	
		mapPanel.revalidate();
	}
}
