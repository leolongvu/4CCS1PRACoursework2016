package com.theinfiniteloop.sharktracker.gui;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class FavouritesFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;

	public FavouritesFrame() {
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
	public void addFavourite() {
		
	}

	public static void main(String[] args) {
		FavouritesFrame i = new FavouritesFrame();
	}

}
