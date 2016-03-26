package com.theinfiniteloop.sharktracker.gui;

import com.theinfiniteloop.sharktracker.controller.Controller;
import com.theinfiniteloop.sharktracker.controller.FileIO;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import com.theinfiniteloop.sharktracker.api.Query;

/**
 * @authors The Infinite Loop Class which has a "is-a" relationship to JPanel.
 *          Creates the "Shark of the Day" feature.
 */
public class SharkOfTheDayPanel extends JPanel {

	private String sharkOTD;
	private String dayOfMonth;
	private String month;
	private Controller controller;

	/**
	 * Constructor creates the SharkOfTheDayPanel when called. Creates the
	 * fields
	 */
	public SharkOfTheDayPanel(Controller controller) {
		this.controller = controller;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		getShark();

		JLabel lbl = new JLabel("Shark of the Day for " + dayOfMonth + "/" + month + " : " + sharkOTD);
		lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lbl);

		JLabel videolbl = new JLabel(
				"<html><a href=#>" + "Video Link: " + controller.getVideo(sharkOTD) + "</a></html>");
		videolbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		videolbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(videolbl);

		setBackground(Color.white);
		setBorder(new MatteBorder(2, 0, 2, 0, Color.BLACK));
		setMaximumSize(new Dimension(300, 50));
	}

	/**
	 * Void method creates SharkOfTheDay file which stores a random Shark name
	 * and changes it every 24 hours.
	 */
	public void getShark() {
		Calendar calendar = Calendar.getInstance();
		dayOfMonth = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		month = Integer.toString(calendar.get(Calendar.MONTH) + 1);

		FileIO file = new FileIO("SharkOfTheDay");

		// If today's date does not equal the previous shark of the day date
		// saved, then set today's date and a new shark as the shark of the day
		if (!dayOfMonth.equals(file.readLines().get(0))) {
			file.deleteFile();
			file.setFile("SharkOfTheDay");
			file.addLine(dayOfMonth);
			// getAllSharkNames() is called in order to store the names of all
			// the existing sharks in an ArrayList of type String
			ArrayList<String> sharkList = controller.getAllSharkNames();

			// Created a random generator to get a random number <= the size of
			// the sharkList Array
			Random rnd = new Random();
			int i = rnd.nextInt(sharkList.size() - 1);
			sharkOTD = sharkList.get(i);
			file.addLine(sharkOTD);

		}
		// Otherwise sharkOTD stores the name of the current shark in the file
		else {
			sharkOTD = file.readLines().get(1);
		}
	}
}
