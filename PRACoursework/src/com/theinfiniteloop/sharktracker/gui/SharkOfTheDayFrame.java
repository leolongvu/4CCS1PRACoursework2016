package com.theinfiniteloop.sharktracker.gui;

import com.theinfiniteloop.sharktracker.controller.FileIO;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.theinfiniteloop.sharktracker.api.Query;
import com.theinfiniteloop.sharktracker.api.SharkTime;

public class SharkOfTheDayFrame {

	private Query query;

	private JFrame jfShark;

	private JPanel jpShark;

	private JLabel sharkOfTheDay;

	private ArrayList<String> sharkList;

	private Random randomNameGenerator;

	private String sharkOTD;

	public SharkOfTheDayFrame(String rangeChosen) {
		query = new Query();

		jfShark = new JFrame();

		jpShark = new JPanel();
		jpShark.setLayout(new FlowLayout());

		sharkList = new ArrayList<String>();
		sharkList = query.getAllSharksNames();

	}

	/*
	 * public void getTheSharkName() { int index =
	 * randomNameGenerator.nextInt(sharkList.size());
	 * sharkOfTheDay.setText(sharkList.get(index).getShark().getName());
	 * jpShark.add(sharkOfTheDay); jfShark.add(jpShark); }
	 */

	public void setDate() {
		Calendar calendar = Calendar.getInstance();
		String dayOfMonth = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		FileIO file = new FileIO("SharkOfTheDay");

		if (!dayOfMonth.equals(file.readFile().get(0))) {
			System.out.println("new day");
			// FileIO file2 = new FileIO("tempShark");
			file.deleteFile();
			file.setFile("SharkOfTheDay");

			file.addLine(dayOfMonth);

			System.out.println("day added");
			Random rnd = new Random();
			int i = rnd.nextInt(sharkList.size() - 1);
			sharkOTD = sharkList.get(i);
			file.addLine(sharkOTD);
			// file2.renameFile("SharkOfTheDay");
		}

		else {
			System.out.println("same day");
			sharkOTD = file.readFile().get(1);
		}

		System.out.println(sharkOTD);

	}

	public static void main(String[] args) {
		SharkOfTheDayFrame shark = new SharkOfTheDayFrame("Week");
		shark.setDate();
	}
}