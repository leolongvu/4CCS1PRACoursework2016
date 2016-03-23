package com.theinfiniteloop.sharktracker.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.theinfiniteloop.sharktracker.api.SharkTime;
import com.theinfiniteloop.sharktracker.controller.Controller;
import com.theinfiniteloop.sharktracker.controller.FileIO;

public class MainFrame {

        private JPanel contentPane;
        private JFrame frame;
        private JButton searchButton;
        private JButton statsButton;
        private JLabel loader;

        private JPanel mainPanel;

        private Controller controller;
        private FileIO file;

        public MainFrame(Controller controller, FileIO file) {
                this.controller = controller;
                this.file = file;
                createPanel();
                createSidePanel();
                createMainPanel();
                createFrame();
        }

        // Create the content pane
        public void createPanel() {

                contentPane = new JPanel();
                contentPane.setLayout(new BorderLayout(0, 0));
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                JLabel acknowledgementLabel = new JLabel(controller.getAcknowledgement());
                contentPane.add(acknowledgementLabel, BorderLayout.SOUTH);
        }

        // Create the frame
        public void createFrame() {

                frame = new JFrame();
                frame.setTitle("Search");
                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                                file.writeFavouriteList(controller.getFavouriteSharkList());
                                System.exit(0);
                        }
                });
                frame.setContentPane(contentPane);
                frame.setSize(1200, 700);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
        }

        public void createSidePanel() {

                // Side panel
                JPanel sidePanel = new JPanel();
                sidePanel.setBackground(Color.white);
                sidePanel.setPreferredSize(new Dimension(300, 700));
                sidePanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
                sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

                JLabel sharkTrackerLabel = new JLabel("Shark Tracker");
                sharkTrackerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                sidePanel.add(sharkTrackerLabel);

                JSeparator separator1 = new JSeparator();
                separator1.setMaximumSize(new Dimension(300, 20));
                sidePanel.add(separator1);

                // Tracking Range
                JLabel trackingRangeLabel = new JLabel("Tracking Range");
                trackingRangeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                sidePanel.add(trackingRangeLabel);

                JComboBox trackingRangeBox = new JComboBox();
                trackingRangeBox.setMaximumSize(new Dimension(250, 20));
                trackingRangeBox.addItem("24 Hours");
                trackingRangeBox.addItem("Week");
                trackingRangeBox.addItem("Month");
                sidePanel.add(trackingRangeBox);

                JSeparator separator2 = new JSeparator();
                separator2.setMaximumSize(new Dimension(300, 20));
                sidePanel.add(separator2);

                // Gender
                JLabel genderLabel = new JLabel("Gender");
                genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                sidePanel.add(genderLabel);

                JComboBox genderBox = new JComboBox();
                genderBox.setMaximumSize(new Dimension(250, 20));
                genderBox.addItem("All Genders");
                genderBox.addItem("Male");
                genderBox.addItem("Female");
                sidePanel.add(genderBox);

                JSeparator separator3 = new JSeparator();
                separator3.setMaximumSize(new Dimension(300, 20));
                sidePanel.add(separator3);

                // Stage of Life
                JLabel stageOfLifeLabel = new JLabel("Stage of Life");
                stageOfLifeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                sidePanel.add(stageOfLifeLabel);

                JComboBox stageOfLifeBox = new JComboBox();
                stageOfLifeBox.setMaximumSize(new Dimension(250, 20));
                stageOfLifeBox.addItem("All Stages of Life");
                stageOfLifeBox.addItem("Mature");
                stageOfLifeBox.addItem("Immature");
                stageOfLifeBox.addItem("Undetermined");
                sidePanel.add(stageOfLifeBox);

                JSeparator separator4 = new JSeparator();
                separator4.setMaximumSize(new Dimension(300, 20));
                sidePanel.add(separator4);

                // Tag Location
                JLabel tagLocationLabel = new JLabel("Tag Location");
                tagLocationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                sidePanel.add(tagLocationLabel);

                JComboBox tagLocationBox = new JComboBox();
                tagLocationBox.setMaximumSize(new Dimension(250, 20));
                ArrayList<String> locations = controller.getTagLocations();
                Collections.sort(locations);
                locations.add(0, "All Locations");
                for (String s : locations) {
                        tagLocationBox.addItem(s);
                }
                sidePanel.add(tagLocationBox);

                JSeparator separator5 = new JSeparator();
                separator5.setMaximumSize(new Dimension(300, 35));
                sidePanel.add(separator5);

                createLoader();

                // Search button
                searchButton = new JButton("Search");
                searchButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                Thread thread = new Thread() {
                                        public void run() {

                                                mainPanel.removeAll();
                                                mainPanel.add(loader);
                                                searchButton.setEnabled(false);
                                                searchButton.setText("Searching...");
                                                controller.searchShark(trackingRangeBox.getSelectedItem().toString(),
                                                                genderBox.getSelectedItem().toString(), stageOfLifeBox.getSelectedItem().toString(),
                                                                tagLocationBox.getSelectedItem().toString());

                                                ArrayList<SharkTime> sharkFilter = controller.getSharkList();
                                                mainPanel.removeAll();
                                                mainPanel.revalidate();
                                                System.out.println("Shark count: " + sharkFilter.size());
                                                for (SharkTime s : sharkFilter) {
                                                        SharkPanel sharkPanel = new SharkPanel(s, controller);
                                                        mainPanel.add(sharkPanel);
                                                }
                                                mainPanel.revalidate();
                                                searchButton.setText("Search");
                                                searchButton.setEnabled(true);
                                        }
                                };
                                thread.start();
                        }
                });
                searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                searchButton.setMaximumSize(new Dimension(250, 25));
                sidePanel.add(searchButton);
                
                JSeparator separator6 = new JSeparator();
                separator6.setMaximumSize(new Dimension(300, 30));
                sidePanel.add(separator6);
                
                statsButton = new JButton("Statistics");
                statsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                statsButton.setMaximumSize(new Dimension(250, 25));
                sidePanel.add(statsButton);
                
                statsButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                StatisticsFrame stats = new StatisticsFrame(trackingRangeBox.getSelectedItem().toString());
                        }
                });

        

                // Shark logo
                ImageIcon icon = new ImageIcon("Shark Logo.jpg");
                Image image = icon.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
                JLabel logoLabel = new JLabel(new ImageIcon(image));
                logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                sidePanel.add(logoLabel);

                
                
                contentPane.add(sidePanel, BorderLayout.WEST);
        }

        public void createMainPanel() {

                // Main panel
                mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                mainPanel.setMaximumSize(new Dimension(900, 700));
                mainPanel.setBackground(Color.white);

                // Scroll pane to show information
                JScrollPane scrollPane = new JScrollPane(mainPanel);
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.getVerticalScrollBar().setUnitIncrement(20);
                scrollPane.setBorder(new MatteBorder(2, 0, 3, 3, (Color) new Color(0, 0, 0)));
                contentPane.add(scrollPane, BorderLayout.CENTER);
        }

        public void selectedShark(SharkTime s) {
                mainPanel.removeAll();
                mainPanel.add(new SharkPanel(s, controller));
                mainPanel.revalidate();
        }

        public void createLoader() {
                ImageIcon loaderPicture = new ImageIcon("Loader.gif");
                loader = new JLabel(loaderPicture);
                loader.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
}


