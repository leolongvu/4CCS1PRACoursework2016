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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.theinfiniteloop.sharktracker.api.SharkLocation;
import com.theinfiniteloop.sharktracker.api.Sharknado;
import com.theinfiniteloop.sharktracker.controller.Controller;
import com.theinfiniteloop.sharktracker.gui.map.MapExtension;

import api.jaws.Shark;

/**
 * @author the infinite loop. This class shows the users list of favourites
 */
public class FavouritesFrame {

	private static DecimalFormat df2 = new DecimalFormat(".##");

	private JFrame frame;
	private JPanel contentPane;

	private DefaultTableModel model;
	private Controller controller;
	private ArrayList<String> coords;

	/**
	 * creates a new favourites frame
	 * 
	 * @param controller
	 *            reference to a controller
	 */
	public FavouritesFrame(Controller controller) {
		this.controller = controller;
		createPanel();
		createFrame();
		sharknadoLoad();
	}

	/**
	 * creates the content pane for the frame
	 */
	private void createPanel() {
		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel label = new JLabel("Your favourite sharks are this far away from you right now:");
		contentPane.add(label);

		JLabel label2 = new JLabel("Click on a shark to view additional information.");
		contentPane.add(label2);

		JTable table = new JTable();
		model = (DefaultTableModel) table.getModel();
		model.addColumn("Name of Shark");
		model.addColumn("Distance from Kings (KM)");

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane);

		// button to open a map
		JButton mapButton = new JButton("Show Map");
		contentPane.add(mapButton);
		mapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MapExtension map = new MapExtension(controller);
			}
		});

		// action listener for the table, to open a shark in the search
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

	/**
	 * creates the frame
	 */
	private void createFrame() {
		frame = new JFrame();
		frame.setTitle("Favourites");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(contentPane);
		frame.setSize(450, 550);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * adds a sharks information to the table
	 * 
	 * @param s
	 *            is a shark object
	 * @param dist
	 *            is the distance to kings
	 */
	public void addShark(Shark s, double dist) {
		model.addRow(new Object[] { s.getName(), df2.format(dist) });
		contentPane.repaint();
	}

	/**
	 * iterates through the list of favourite sharks and checks for sharknado
	 * events, and then gives a popup alert if true
	 */
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
