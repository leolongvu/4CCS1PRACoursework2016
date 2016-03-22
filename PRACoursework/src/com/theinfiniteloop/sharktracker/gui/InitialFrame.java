package com.theinfiniteloop.sharktracker.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

import com.theinfiniteloop.sharktracker.controller.FileIO;
import com.theinfiniteloop.sharktracker.controller.Controller;

public class InitialFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JButton favouritesButton;

	private Controller controller;
	private FileIO file;

	public InitialFrame(Controller controller) {
		this.controller = controller;
		file = new FileIO(controller);
		file.setFile("user");

		createPanel();
		createLabels();
		createButtons();
		createFrame();
		createMenu();
		frame.setVisible(true);

	}

	// Create the content pane
	public void createPanel() {

		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBackground(Color.white);
	}

	// Create the frame
	public void createFrame() {
		frame = new JFrame();
		frame.setTitle("Shark Tracker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(contentPane);
		frame.setSize(new Dimension(300, 400));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

	}

	// Creates the logo and label
	public void createLabels() {
		ImageIcon icon = new ImageIcon("Shark Logo.jpg");
		Image image = icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(image));
		logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(logoLabel);

		JLabel label = new JLabel("Shark Tracker");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(label);

		contentPane.add(new Box.Filler(new Dimension(10, 20), new Dimension(10, 20), new Dimension(10, 20)));
	}

	// Creates the search and favourites buttons
	public void createButtons() {
		Border border = BorderFactory.createLineBorder(Color.black, 2);

		JButton searchButton = new JButton("Search");
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.setMaximumSize(new Dimension(250, 50));
		searchButton.setBorder(border);
		searchButton.setBackground(Color.white);

		// Action listener for the search button to open the main frame
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame mainFrame = new MainFrame(controller, file);
				controller.setMainFrameReference(mainFrame);
				frame.dispose();
			}
		});
		contentPane.add(searchButton);
		contentPane.add(new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10)));

		favouritesButton = new JButton("Favourites");
		favouritesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		favouritesButton.setMaximumSize(new Dimension(250, 50));
		favouritesButton.setBorder(border);
		favouritesButton.setBackground(Color.white);
		favouritesButton.setEnabled(false);

		// Action listener for the favourites button to open the favourites
		// frame
		favouritesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FavouritesFrame favouritesFrame = new FavouritesFrame(controller);
				for (int i = 0; i < controller.getFavouriteSharkList().size(); i++) {
					favouritesFrame.addShark(controller.getFavouriteSharkList().get(i).getShark(),
							controller.getFavouriteSharkList().get(i).getDistance());
				}
			}
		});
		contentPane.add(favouritesButton);
	}

	public void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnUserOptions = new JMenu("User Options");
		menuBar.add(mnUserOptions);

		JMenuItem mntmNewloadUser = new JMenuItem("New / Load User");
		mnUserOptions.add(mntmNewloadUser);

		JMenuItem mntmDeleteCurrentUser = new JMenuItem("Delete Current User");
		mnUserOptions.add(mntmDeleteCurrentUser);

		mntmNewloadUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createUserMenu();
			}
		});

		mntmDeleteCurrentUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				file.deleteFile();
				file.setFile("user");
				favouritesButton.setEnabled(false);
				favouritesButton.setText("Favourites");
			}
		});
	}

	public void createUserMenu() {
		JFrame userFrame = new JFrame();
		userFrame.setResizable(false);
		userFrame.setTitle("New / Load User");
		userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userFrame.setBounds(100, 100, 300, 130);
		userFrame.setLocationRelativeTo(null);

		JPanel pane = new JPanel();

		userFrame.setContentPane(pane);
		userFrame.setVisible(true);

		JPanel userPanel = new JPanel();
		pane.add(userPanel);
		userPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblEnterUsername = new JLabel("Enter Username:");
		userPanel.add(lblEnterUsername);

		JTextField textField = new JTextField();
		userPanel.add(textField);
		textField.setColumns(10);

		JPanel buttonPanel = new JPanel();
		pane.add(buttonPanel);

		JButton btnConfirm = new JButton("Confirm");
		buttonPanel.add(btnConfirm);

		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = textField.getText();
				if (!user.equals(null) && user.trim().length() > 0) {
					file.setFile(user);
					favouritesButton.setText("Favourites: " + user);
				}
				if (file.readFile() > 0) {
					favouritesButton.setEnabled(true);
				} else if (file.readFile() == 0) {
					favouritesButton.setEnabled(false);
				}
				favouritesButton.repaint();
				userFrame.dispose();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		buttonPanel.add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userFrame.dispose();
			}
		});
	}
}
