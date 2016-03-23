package com.theinfiniteloop.sharktracker.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.theinfiniteloop.sharktracker.api.SharkLocation;
import com.theinfiniteloop.sharktracker.api.Sharknado;
import com.theinfiniteloop.sharktracker.controller.Controller;

import api.jaws.Shark;

public class FavouritesFrame {

	private static DecimalFormat df2 = new DecimalFormat(".##");

	private JFrame frame;
	private JPanel contentPane;

	private DefaultTableModel model;
	private Controller controller;
	private ArrayList<String> coords;

	public FavouritesFrame(Controller controller) {
		this.controller = controller;
		createPanel();
		createFrame();
		sharknadoLoad();
	}

	private void createPanel() {
		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel label = new JLabel("Your favourite sharks are this far away from you right now:");
		contentPane.add(label);

		JTable table = new JTable();
		model = (DefaultTableModel) table.getModel();

		model.addColumn("Name of Shark");
		model.addColumn("Distance from Kings (KM)");

		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane);

		JButton mapButton = new JButton("Show Map");
		contentPane.add(mapButton);
		mapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MapFrame map = new MapFrame(coords);
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() > -1) {
					System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
					controller.selectShark(
							controller.getSelectedSharkTime(table.getValueAt(table.getSelectedRow(), 0).toString()));
					controller.setMainFrameVisibility(true);
					controller.setInitialFrameVisibility(false);
					frame.dispose();
				}
			}
		});
	}

	private void createFrame() {
		frame = new JFrame();
		frame.setTitle("Favourites");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(contentPane);
		frame.setSize(450, 520);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void addShark(Shark s, double dist) {
		model.addRow(new Object[] { s.getName(), df2.format(dist) });
		contentPane.repaint();
	}

	private void sharknadoLoad() {
		coords = new ArrayList<String>();

		for (SharkLocation sl : controller.getFavouriteSharkList()) {
			String loc = sl.getLat() + "," + sl.getLon();
			coords.add(loc);
		}

		String sharknado = "";
		boolean sharknadoEvent = false;
		for (int i = 0; i < coords.size(); i++) {
			if (Sharknado.checkSharknado(coords.get(i)) == true) {
				sharknadoEvent = true;
				if (sharknado.length() != 0) {
					sharknado += ", " + controller.getFavouriteSharkList().get(i).getShark().getName();
				} else {
					sharknado += controller.getFavouriteSharkList().get(i).getShark().getName();
				}
			}
		}
		if (sharknadoEvent) {
			JOptionPane.showMessageDialog(frame, "These sharks are on land: " + sharknado, "Sharknado Alert!",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
