package com.theinfiniteloop.sharktracker.gui;

import com.theinfiniteloop.sharktracker.controller.FileIO;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.theinfiniteloop.sharktracker.api.Query;

public class SharkOfTheDayPanel extends JPanel {

	private String sharkOTD;
	private String dayOfMonth;
	private String month;
	private String link;
	private Query query;

	public SharkOfTheDayPanel() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		query = new Query();
		getShark();

		JLabel lbl = new JLabel("Shark Of The Day For " + dayOfMonth + "/" + month + " : " + sharkOTD);
		lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lbl);

		JLabel videolbl = new JLabel("Video Link: " + query.getVideo(sharkOTD));
		videolbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(videolbl);

		setMaximumSize(new Dimension(300, 50));
	}

	public void getShark() {
		Calendar calendar = Calendar.getInstance();
		dayOfMonth = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		month = Integer.toString(calendar.get(Calendar.MONTH));
		FileIO file = new FileIO("SharkOfTheDay");
		if (!dayOfMonth.equals(file.readLines().get(0))) {
			file.deleteFile();
			file.setFile("SharkOfTheDay");
			file.addLine(dayOfMonth);
			ArrayList<String> sharkList = query.getAllSharkNames();

			Random rnd = new Random();
			int i = rnd.nextInt(sharkList.size() - 1);
			sharkOTD = sharkList.get(i);
			file.addLine(sharkOTD);

		} else {
			sharkOTD = file.readLines().get(1);
		}
	}
}