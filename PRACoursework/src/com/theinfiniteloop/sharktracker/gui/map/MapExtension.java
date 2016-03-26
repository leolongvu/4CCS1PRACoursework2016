package com.theinfiniteloop.sharktracker.gui.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.theinfiniteloop.sharktracker.api.Sharknado;
import com.theinfiniteloop.sharktracker.controller.Controller;

import api.jaws.Shark;

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
				coordinates
						.setText(((arg0.getX() * 360 / 1366.0) - 180) + " ; " + (90 - ((arg0.getY() * 180 / 683.0))));
				coordinates.revalidate();
			}
		});

		ImageIcon map = new ImageIcon("Map/Earth Map.jpg");
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

		JLabel rollOver = new JLabel("Roll over pins for shark's details");
		rollOver.setFont(font);
		rollOver.setForeground(Color.WHITE);
		rollOver.setBounds(120, 534, 500, 20);
		mapPanel.add(rollOver, new Integer(1), 0);

		ImageIcon compass = new ImageIcon("Map/Compass.png");
		Image compassImage = compass.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		JLabel compassLabel = new JLabel(new ImageIcon(compassImage));
		compassLabel.setBounds(30, 450, 100, 100);
		mapPanel.add(compassLabel, new Integer(1), 0);

		ImageIcon king = new ImageIcon("Map/Kings.png");
		Image kingImage = king.getImage().getScaledInstance(35, 50, java.awt.Image.SCALE_SMOOTH);
		JLabel kingLabel = new JLabel(new ImageIcon(kingImage));
		kingLabel.setBounds(665, 95, 35, 50);
		mapPanel.add(kingLabel, new Integer(1), 0);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(mapPanel, BorderLayout.CENTER);

		ImageIcon blackIcon = new ImageIcon("Map/black.png");
		Image imageBlack = blackIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		JLabel blackLabel = new JLabel(new ImageIcon(imageBlack));
		blackLabel.setBounds(20, 652, 30, 30);
		mapPanel.add(blackLabel, new Integer(1), 0);

		JLabel sharknado = new JLabel("Sharknado");
		sharknado.setBounds(60, 652, 100, 30);
		mapPanel.add(sharknado, new Integer(1), 0);

		ImageIcon yellowIcon = new ImageIcon("Map/yellow.png");
		Image imageyellow = yellowIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		JLabel yellowLabel = new JLabel(new ImageIcon(imageyellow));
		yellowLabel.setBounds(140, 652, 30, 30);
		mapPanel.add(yellowLabel, new Integer(1), 0);

		JLabel yellow = new JLabel("Tiger Shark (Galeocerdo cuvier)");
		yellow.setBounds(180, 652, 200, 30);
		mapPanel.add(yellow, new Integer(1), 0);

		ImageIcon redIcon = new ImageIcon("Map/red.png");
		Image imagered = redIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		JLabel redLabel = new JLabel(new ImageIcon(imagered));
		redLabel.setBounds(379, 652, 30, 30);
		mapPanel.add(redLabel, new Integer(1), 0);

		JLabel red = new JLabel("White Shark (Carcharodon carcharias)");
		red.setBounds(419, 652, 250, 30);
		mapPanel.add(red, new Integer(1), 0);

		ImageIcon blueIcon = new ImageIcon("Map/blue.png");
		Image imageblue = blueIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		JLabel blueLabel = new JLabel(new ImageIcon(imageblue));
		blueLabel.setBounds(660, 652, 30, 30);
		mapPanel.add(blueLabel, new Integer(1), 0);

		JLabel blue = new JLabel("Mako Shark (Isurus oxyrinchus)");
		blue.setBounds(700, 652, 250, 30);
		mapPanel.add(blue, new Integer(1), 0);

		ImageIcon greenIcon = new ImageIcon("Map/green.png");
		Image imagegreen = greenIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		JLabel greenLabel = new JLabel(new ImageIcon(imagegreen));
		greenLabel.setBounds(900, 652, 30, 30);
		mapPanel.add(greenLabel, new Integer(1), 0);

		JLabel green = new JLabel("Hammerhead Shark (Sphyrna)");
		green.setBounds(940, 652, 250, 30);
		mapPanel.add(green, new Integer(1), 0);

		addSharkPin();

		this.setBounds(40, 40, 1366, 734);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		this.setVisible(true);
		this.setTitle("Map of the sharks");
	}

	private void addSharkPin() {
		for (int i = 0; i < controller.getFavouriteSharkList().size(); i++) {
			double lon = controller.getFavouriteSharkList().get(i).getLon();
			double lat = controller.getFavouriteSharkList().get(i).getLat();

			double convertedLon = lon + 180;
			double convertedLat = 90 - lat;

			int x = (int) (convertedLon * 1366 / 360);
			int y = (int) (convertedLat * 683 / 180);

			String loc = lat + "," + lon;

			if (Sharknado.checkSharknado(loc) == true) {
				setPin("Map/NadoPin.png", x, y, controller.getFavouriteSharkList().get(i).getShark());
			} else {
				switch (controller.getFavouriteSharkList().get(i).getShark().getSpecies()) {
				case "White Shark (Carcharodon carcharias)":
					setPin("Map/Pin1.png", x, y, controller.getFavouriteSharkList().get(i).getShark());
					break;
				case "Tiger Shark  (Galeocerdo cuvier)":
					setPin("Map/Pin.png", x, y, controller.getFavouriteSharkList().get(i).getShark());
					break;
				case "Hammerhead Shark (Sphyrna)":
					setPin("Map/Pin3.png", x, y, controller.getFavouriteSharkList().get(i).getShark());
					break;
				case "Mako Shark (Isurus oxyrinchus)":
					setPin("Map/Pin2.png", x, y, controller.getFavouriteSharkList().get(i).getShark());
					break;
				}
			}
		}
		mapPanel.revalidate();
	}

	private void setPin(String fileName, int x, int y, Shark shark) {
		ImageIcon pin = new ImageIcon(fileName);
		Image pinImage = pin.getImage().getScaledInstance(30, 48, java.awt.Image.SCALE_SMOOTH);
		JLabel pinLabel = new JLabel(new ImageIcon(pinImage));
		pinLabel.setBounds(x - 17, y - 57, 34, 57);
		pinLabel.addMouseListener(new MouseListener() {
			SharkMapPanel sharkPanel = new SharkMapPanel(shark);

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				sharkPanel.setVisible(true);
				sharkPanel.setBounds(x, y, 368, 248);
				mapPanel.add(sharkPanel, new Integer(1), 0);
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				sharkPanel.setVisible(false);
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		mapPanel.add(pinLabel, new Integer(1), 0);
	}
}
