package com.theinfiniteloop.sharktracker.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

import com.theinfiniteloop.sharktracker.controller.FileIO;
import com.theinfiniteloop.sharktracker.controller.Controller;

/**
 * @author Raf, the infinite loop. This class is the inital gui shown when the
 *         program starts
 */
public class InitialFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JButton favouritesButton;
	private JButton searchButton;

	private Controller controller;
	private FileIO file;

	/**
	 * constructs the initial gui
	 * 
	 * @param controller
	 *            is the reference to the controller
	 */
	public InitialFrame(Controller controller) {
		this.controller = controller;
		file = new FileIO(controller);
		file.setFile("User");

		createPanel();
		createLabels();
		createButtons();
		createFrame();
		createMenu();
	}

	/**
	 * creates the content pane
	 */
	private void createPanel() {
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBackground(Color.white);
	}

	/**
	 * creates the frame, and has a window listener so that when it is closed,
	 * the list of favourites is saved
	 */
	private void createFrame() {
		frame = new JFrame();
		frame.setTitle("Shark Tracker");
		frame.setContentPane(contentPane);
		frame.setSize(new Dimension(300, 400));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				file.writeFavouriteList(controller.getFavouriteSharkList());
				System.exit(0);
			}
		});
	}

	/**
	 * creates the labels
	 */
	private void createLabels() {
		ImageIcon icon = new ImageIcon("Shark Logo.jpg");
		Image image = icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(image));
		logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(logoLabel);

		Font font = new Font("Comic Sans MS", Font.BOLD, 15);
		JLabel label = new JLabel("Shark Tracker");
		label.setForeground(Color.BLACK);
		label.setFont(font);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(label);
	}

	/**
	 * creates the buttons
	 */
	private void createButtons() {
		Border border = BorderFactory.createLineBorder(Color.black, 2);

		searchButton = new JButton("Search");
		searchButton.setEnabled(false);
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.setMaximumSize(new Dimension(250, 50));
		searchButton.setBorder(border);
		searchButton.setBackground(Color.white);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setMainFrameVisibility(true);
				controller.showSearch();
				setVisible(false);
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
		contentPane.add(new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10)));
	}

	/**
	 * creates the user menu
	 */
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnUserOptions = new JMenu("User Options");
		menuBar.add(mnUserOptions);

		JMenuItem mntmNewloadUser = new JMenuItem("New / Load User");
		mnUserOptions.add(mntmNewloadUser);

		JMenuItem mntmDeleteCurrentUser = new JMenuItem("Delete Current User");
		mnUserOptions.add(mntmDeleteCurrentUser);

		// if the new / load user button is pressed it open a dialog to enter a
		// user
		mntmNewloadUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createUserMenu();
			}
		});

		// if the delete option is pressed it delets the current user
		mntmDeleteCurrentUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				file.deleteFile();
				file.setFile("User");
				favouritesButton.setEnabled(false);
				favouritesButton.setText("Favourites");
			}
		});
	}

	/**
	 * creates a frame to enter user details
	 */
	private void createUserMenu() {
		JFrame userFrame = new JFrame();
		userFrame.setResizable(false);
		userFrame.setTitle("New / Load User");
		userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		userFrame.setBounds(100, 100, 300, 130);
		userFrame.setLocationRelativeTo(null);

		JPanel pane = new JPanel();

		userFrame.setContentPane(pane);
		userFrame.setVisible(true);

		JPanel userPanel = new JPanel();
		pane.add(userPanel);
		userPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblEnterUsername = new JLabel("Enter Username: ");
		userPanel.add(lblEnterUsername);

		JTextField textField = new JTextField();
		userPanel.add(textField);
		textField.setColumns(10);

		JPanel buttonPanel = new JPanel();
		pane.add(buttonPanel);

		// when the confirm button is pressed, if the username is not blank or
		// just white space,
		// it creates a new user, or loads an existing user with the same name
		JButton btnConfirm = new JButton("Confirm");
		buttonPanel.add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = textField.getText();
				if (!user.equals(null) && user.trim().length() > 0) {
					file.setFile(user);
					favouritesButton.setText("Favourites for: " + user);
					file.readFile();
					checkFavouriteButton();
					userFrame.dispose();
				}
			}
		});
		// closes the new user dialog
		JButton btnCancel = new JButton("Cancel");
		buttonPanel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userFrame.dispose();
			}
		});
	}

	/**
	 * sets this frame to be visible or not
	 * 
	 * @param visible
	 *            boolean true or false
	 */
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
		checkFavouriteButton();
	}

	/**
	 * enables the search button once everything has loaded
	 */
	public void enableSearchButton() {
		searchButton.setEnabled(true);
	}

	/**
	 * this method enables or disables the favourites button based on the size
	 * of the favourites list
	 */
	private void checkFavouriteButton() {
		if (controller.getFavouriteSharkList().size() > 0) {
			favouritesButton.setEnabled(true);
		} else {
			favouritesButton.setEnabled(false);
		}
	}
}
