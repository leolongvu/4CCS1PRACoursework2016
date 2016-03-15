package com.theinfiniteloop.sharktracker.gui;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.theinfiniteloop.sharktracker.api.Query;
import com.theinfiniteloop.sharktracker.api.SharkTime;

import api.jaws.Location;

public class FavouritesFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Query query;

	public FavouritesFrame() {
		query = new Query();
		createPanel();
		createFrame();
	}

	public void createPanel() {
		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel label = new JLabel("Your favourite sharks are this far away from you right now:");
		contentPane.add(label);
		
		JTable table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		model.addColumn("Name of Shark");
		model.addColumn("Distance from Kings");

		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane);

	}

	public void createFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("Favourites");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(contentPane);
		frame.setSize(450, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// To do: method to add a favourite shark to the table
	public void addFavourite(SharkTime s) {
		Location l = query.getLocation(s.getShark().getName());
		double dist = getDistance(l);
		String name = s.getShark().getName();
		
		model.addRow(new Object[]{name, dist});
		
	}
	
	public double getDistance(Location l){
		double lat = l.getLatitude();
		double lon = l.getLongitude();
		
		double klat = 51.51193;
		double klon = -0.11698;
		
		double l1 = (lat-klat);
		double l11 = l1*l1;
		
		double l2 = (lon-klon);
		double l22 = l2*l2;
		
		double l3 = l11+l22;
		double dist = Math.sqrt(l3);
		
		return dist;
	}

	public static void main(String[] args) {
		FavouritesFrame i = new FavouritesFrame();
	}

}
