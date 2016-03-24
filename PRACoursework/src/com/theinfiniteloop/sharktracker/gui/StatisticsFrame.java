package com.theinfiniteloop.sharktracker.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import com.theinfiniteloop.sharktracker.api.SharkTime;
import com.theinfiniteloop.sharktracker.controller.Controller;

/**
 * 
 * @author Laura
 * 
 *         A class which creates pie charts that indicate the number of sharks
 *         in the tracking range selected for each possible option (Gender,
 *         Stage of Life, Tag of Location). I had to import jcommon and
 *         jfreechart libraries in order to be able to create the pie charts.
 */
public class StatisticsFrame {

	private Controller controller;

	private JFrame statistics;
	private JPanel trackingGraphPane;
	private JLabel messageGender;
	private JLabel messageLife;
	private JLabel messageLocation;

	private ChartPanel genderChartPanel;
	private ChartPanel stageOfLifeChartPanel;
	private ChartPanel locationChartPanel;
	private DefaultPieDataset data;

	private int totalCount;
	private int maleCount;
	private int femaleCount;
	private int matureCount;
	private int immatureCount;
	private int undeterminedCount;

	private ArrayList<SharkTime> getSharkList;
	private ArrayList<String> locations;

	private int[] numberByLocation;

	/**
	 * Constructor initializes the fields and creates the Statistics frame with
	 * the pie charts when called.
	 * 
	 * @param rangeChosen
	 *            A String input which represents the option chosen in the
	 *            "Tracking Range"
	 * @param controller
	 *            The "StatisticsFrame" class uses the controller(gives a
	 *            reference to Query) to call the methods from the "Query"
	 *            class.
	 */
	public StatisticsFrame(String rangeChosen, Controller controller) {
		statistics = new JFrame();
		this.controller = controller;

		locations = controller.getTagLocations();

		trackingGraphPane = new JPanel();
		trackingGraphPane.setLayout(new GridLayout(3, 1));
		trackingGraphPane.setPreferredSize(new Dimension(800, 600));

		getSharkList = new ArrayList<SharkTime>();
		getSharkList = controller.getSharkByTimeFrame(rangeChosen);
		totalCount = getSharkList.size();
		messageGender = new JLabel();
		messageLife = new JLabel();
		messageLocation = new JLabel();

		createGenderChart();
		createStageOfLifeChart();
		createLocationChart();

		statistics.add(trackingGraphPane);
		statistics.setVisible(true);
		statistics.pack();
		statistics.setLocationRelativeTo(null);
		statistics.setDefaultCloseOperation(statistics.DISPOSE_ON_CLOSE);
	}

	/**
	 * Void method creates pie chart for the gender of sharks. Calls
	 * getNumberOfSharkByGender() method (commented below). If there are any
	 * sharks found it creates the pie chart relevant to the data. Otherwise a
	 * message shows up.
	 */
	public void createGenderChart() {
		getNumberOfSharksByGender();
		if (femaleCount + maleCount != 0) {
			data = new DefaultPieDataset();
			if (maleCount != 0) {
				data.setValue("Male", maleCount);
			}
			if (femaleCount != 0) {
				data.setValue("Female", femaleCount);
			}
			genderChartPanel = new ChartPanel(ChartFactory.createPieChart("Gender", data));
			trackingGraphPane.add(genderChartPanel);
		} else {
			messageGender.setText("There is not enough data about the Sharks' Gender.");
			messageGender.setHorizontalAlignment(JLabel.CENTER);
			trackingGraphPane.add(messageGender);
		}
	}

	/**
	 * Void method creates pie chart for the stage of life of the sharks. Calls
	 * getNumberOfSharkByStageOfLife() method (commented below). If there are
	 * any sharks found it creates the pie chart relevant to the data. Otherwise
	 * a message shows up.
	 */
	public void createStageOfLifeChart() {
		getNumberOfSharksByStageOfLife();
		if (matureCount + immatureCount + undeterminedCount != 0) {
			data = new DefaultPieDataset();

			if (matureCount != 0) {
				data.setValue("Mature", matureCount);
			}
			if (immatureCount != 0) {
				data.setValue("Immature", immatureCount);
			}
			if (undeterminedCount != 0) {
				data.setValue("Undetermined", undeterminedCount);
			}
			stageOfLifeChartPanel = new ChartPanel(ChartFactory.createPieChart("Stage of Life", data));
			trackingGraphPane.add(stageOfLifeChartPanel);
		} else {
			messageLife.setText("There is not enough data about the Sharks' life stage.");
			messageLife.setHorizontalAlignment(JLabel.CENTER);
			trackingGraphPane.add(messageLife);
		}
	}

	/**
	 * Void method creates pie chart for the location of sharks. Calls
	 * getNumberOfSharkByLocationr() method (commented below). If there are any
	 * sharks found it creates the pie chart relevant to the data. Otherwise a
	 * message shows up.
	 */
	public void createLocationChart() {
		int[] numberOfSharks = getNumberOfSharksByLocation();
		int totalNo = 0;
		for (int s : numberOfSharks) {
			totalNo = s + totalNo;
		}
		if (totalNo != 0) {
			data = new DefaultPieDataset();
			int i = 0;
			for (String s : locations) {
				if (numberOfSharks[i] != 0) {
					data.setValue(s, numberOfSharks[i]);
				}
				i++;
			}
			locationChartPanel = new ChartPanel(ChartFactory.createPieChart("Location", data));
			trackingGraphPane.add(locationChartPanel);
		} else {
			messageLocation.setText("There is not enugh data about the Sharks' location.");
			messageLocation.setHorizontalAlignment(JLabel.CENTER);
			trackingGraphPane.add(messageLocation);
		}
	}

	/**
	 * Void method which gets the number of male sharks and male sharks,
	 * respectively. Uses an ArrayList of type SharkTime to store all the male
	 * sharks. Computes the number of female sharks by subtracting the number of
	 * male sharks from the total number of sharks.
	 */
	public void getNumberOfSharksByGender() {
		ArrayList<SharkTime> getSharkListTest = controller.getSharkByGender("Male", getSharkList);
		maleCount = getSharkListTest.size();
		femaleCount = totalCount - maleCount;
	}

	/**
	 * Void method which gets the number of mature sharks, immature sharks and
	 * sharks which have undetermined gender. Uses an ArrayList of type
	 * SharkTime to store all the mature sharks and immature sharks,
	 * respectively. Computes the number of undetermined sharks by subtracting
	 * the number of mature and immature sharks from the total number of sharks.
	 */
	public void getNumberOfSharksByStageOfLife() {
		ArrayList<SharkTime> getSharkListTest = controller.getSharkByLifeStage("Mature", getSharkList);
		matureCount = getSharkListTest.size();
		getSharkListTest = controller.getSharkByLifeStage("Immature", getSharkList);
		immatureCount = getSharkListTest.size();
		undeterminedCount = totalCount - matureCount - immatureCount;
	}

	/**
	 * Method which returns the number of sharks in a certain location. Uses a
	 * for-each loop which iterates through all the String locations in the
	 * "locations" ArrayList and stores in an ArrayList of type SharkTime all
	 * the sharks in the "s" locations.
	 * 
	 * @return int Array which stores the number of shaks by location
	 */
	public int[] getNumberOfSharksByLocation() {
		int i = 0;// number of the location
		numberByLocation = new int[locations.size()];
		for (String s : locations) {
			ArrayList<SharkTime> getSharkListTest = controller.getSharkByLocation(s, getSharkList);
			numberByLocation[i] = getSharkListTest.size();
			i++;
		}
		return numberByLocation;
	}
}
