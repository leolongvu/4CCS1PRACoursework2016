package com.theinfiniteloop.sharktracker.gui;

import java.awt.FlowLayout;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import api.jaws.Shark;

public class FavouritesFrame {
        
        private static DecimalFormat df2 = new DecimalFormat(".##");

        private JPanel contentPane;
        private DefaultTableModel model;

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
                model = (DefaultTableModel) table.getModel();

                model.addColumn("Name of Shark");
                model.addColumn("Distance from Kings (km)");

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

        public void addShark(Shark s, double dist) {
                model.addRow(new Object[] { s.getName(), df2.format(dist)});
                contentPane.repaint();
        }    
}


