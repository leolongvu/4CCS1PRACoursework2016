package com.theinfiniteloop.sharktracker.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import api.jaws.Jaws;
import api.jaws.Location;
import api.jaws.Ping;
import api.jaws.Shark;

/**
 * The query class which retrieves all of the information about the sharks from
 * the API
 * 
 * @author The Infinite loops
 * @see PRACoursework / Coursework Resources / doc / api / jaws /
 *
 */
public class Query {

	private static final String privateKey = "Wi1TzUkfnMAAdJAo";
	private static final String publicKey = "5J9KvvPX5L0sPTFG";

	private ArrayList<SharkTime> sharkFilter;

	private Jaws jawAPI;

	/**
	 * Constructor for the query class which stores the encryption keys for the
	 * jawAPI and initially stores all the available shark objects
	 */
	public Query() {
		jawAPI = new Jaws(privateKey, publicKey, true);
		sharkFilter = new ArrayList<SharkTime>();
		sharkFilter = getSharkByTimeFrame("Month");
	}

	/**
	 * Method which filters the sharkTime objects relative to the time
	 * constraint selected by the input
	 * 
	 * @param timeFrame
	 *            The timeFrame to be searched input as a string
	 * @return The ArrayList SharkTime which has been filtered by timeFrame
	 */
	public ArrayList<SharkTime> getSharkByTimeFrame(String timeFrame) {
		// Setup a list of ping return by time frame, which we will be using to
		// extract the name of all sharks.
		ArrayList<Ping> pingTimeFrameConstrain = new ArrayList<Ping>();
		switch (timeFrame) {
		case "24 Hours":
			pingTimeFrameConstrain = jawAPI.past24Hours();
			break;
		case "Week":
			pingTimeFrameConstrain = jawAPI.pastWeek();
			break;
		case "Month":
			pingTimeFrameConstrain = jawAPI.pastMonth();
			break;
		}
		HashMap<String, Ping> map = new HashMap<String, Ping>();
		for (int i = 0; i < pingTimeFrameConstrain.size(); i++) {
			Ping toCompare = map.get(pingTimeFrameConstrain.get(i).getName());
			if (toCompare == null) {
				// Add the new Ping into the list
				map.put(pingTimeFrameConstrain.get(i).getName(), pingTimeFrameConstrain.get(i));
			} else {
				// Ping of the same shark is already existed
				// Time formatter for the Ping object
				SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					// Parse two time frame to compare them
					Date oldPingTime = inFormat.parse(toCompare.getTime());
					Date toUpdatedPingTime = inFormat.parse(pingTimeFrameConstrain.get(i).getTime());
					// So if the new found Ping refer to same shark and its time
					// frame is after the
					// found one, update ping object
					if (toUpdatedPingTime.after(oldPingTime)) {
						map.put(pingTimeFrameConstrain.get(i).getName(), pingTimeFrameConstrain.get(i));
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		ArrayList<SharkTime> sharkTimeFrameConstrain = new ArrayList<SharkTime>();
		// Illiterate through the hashmap and then call getShark() methods with
		// parameter String name return from its keys
		for (HashMap.Entry<String, Ping> entry : map.entrySet()) {
			SharkTime sharkAdd = new SharkTime(jawAPI.getShark(entry.getKey()), entry.getValue().getTime());
			sharkTimeFrameConstrain.add(sharkAdd);
		}
		return sharkTimeFrameConstrain;
	}

	/**
	 * A method which filters the sharkTime objects by gender
	 * 
	 * @param gender
	 *            The gender to be filtered by
	 * @param sharkFilter
	 *            The ArrayList of sharkTime objects being filtered
	 * @return The sharkTime ArrayList now filtered by gender
	 */
	public ArrayList<SharkTime> getSharkByGender(String gender, ArrayList<SharkTime> sharkFilter) {
		ArrayList<SharkTime> returnList = new ArrayList<SharkTime>();
		for (int i = 0; i < sharkFilter.size(); i++) {
			if (gender.equals("All Genders") || sharkFilter.get(i).getShark().getGender().equals(gender)) {
				returnList.add(sharkFilter.get(i));
			}
		}
		return returnList;
	}

	/**
	 * A method which filters the sharkTime objects by lifeStage
	 * 
	 * @param lifeStage
	 *            The lifeStage to be filtered by
	 * @param sharkFilter
	 *            The ArrayList to be filtered
	 * @return The sharkTime ArrayList now filtered by lifeStage
	 */
	public ArrayList<SharkTime> getSharkByLifeStage(String lifeStage, ArrayList<SharkTime> sharkFilter) {
		ArrayList<SharkTime> returnList = new ArrayList<SharkTime>();
		for (int i = 0; i < sharkFilter.size(); i++) {
			if (lifeStage.equals("All Stages of Life")
					|| sharkFilter.get(i).getShark().getStageOfLife().equals(lifeStage)) {
				returnList.add(sharkFilter.get(i));
			}
		}
		return returnList;
	}

	/**
	 * A method which filters the sharkTime objects by location
	 * 
	 * @param location
	 *            The location to be filtered by
	 * @param sharkFilter
	 *            The ArrayList to be filtered
	 * @return The sharkTime ArrayList now filtered by location
	 */
	public ArrayList<SharkTime> getSharkByLocation(String location, ArrayList<SharkTime> sharkFilter) {
		ArrayList<SharkTime> returnList = new ArrayList<SharkTime>();
		for (int i = 0; i < sharkFilter.size(); i++) {
			if (location.equals("All Locations") || sharkFilter.get(i).getShark().getTagLocation().equals(location)) {
				returnList.add(sharkFilter.get(i));
			}
		}
		return returnList;
	}

	/**
	 * A method to search the sharkTime objects by all relative constraints
	 * 
	 * @param timeFrame
	 *            The relative timeFrame for the search function as a string
	 * @param gender
	 *            The relative gender for the search function as a string
	 * @param lifeStage
	 *            The relative lifeStage for the search function as a string
	 * @param location
	 *            The relative location for the search function as a string
	 */
	public void implementAllSearch(String timeFrame, String gender, String lifeStage, String location) {
		System.out.println("Downloading data...");
		sharkFilter.clear();
		sharkFilter = getSharkByTimeFrame(timeFrame);
		sharkFilter = getSharkByGender(gender, sharkFilter);
		sharkFilter = getSharkByLifeStage(lifeStage, sharkFilter);
		sharkFilter = getSharkByLocation(location, sharkFilter);
		sortListByPing();
		System.out.println("Done!");
		for (int i = 0; i < sharkFilter.size(); i++) {
			System.out.println(sharkFilter.get(i).getShark().getName() + " " + sharkFilter.get(i).getShark().getGender()
					+ " " + sharkFilter.get(i).getTime());
		}
	}

	/**
	 * A bubble sort method to sort the sharkTime ArrayList by ping time
	 */
	public void sortListByPing() {
		// Bubble sort
		boolean hasSwap = true;
		SharkTime temp;
		SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while (hasSwap) {
			hasSwap = false;
			for (int i = 0; i < sharkFilter.size() - 1; i++) {
				Date pingTime = new Date();
				Date toComparePingTime = new Date();
				try {
					pingTime = inFormat.parse(sharkFilter.get(i).getTime());
					toComparePingTime = inFormat.parse(sharkFilter.get(i + 1).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (toComparePingTime.after(pingTime)) {
					temp = sharkFilter.get(i);
					sharkFilter.set(i, sharkFilter.get(i + 1));
					sharkFilter.set(i + 1, temp);
					hasSwap = true;
				}
			}
		}
	}

	/**
	 * A method that returns all of the Sharks names
	 * 
	 * @return The sharks names as an ArrayList
	 */
	public ArrayList<String> getAllSharkNames() {
		return jawAPI.getSharkNames();
	}

	/**
	 * A method to find the sharkTime object relative to a sharks name
	 * 
	 * @param sharkName
	 *            The sharks name input as a string
	 * @return The sharkTime object related to the sharks name
	 */
	public SharkTime getSharkTimeFromName(String sharkName) {
		for (int i = 0; i < sharkFilter.size(); i++) {
			if (sharkFilter.get(i).getShark().getName().equals(sharkName)) {
				return sharkFilter.get(i);
			}
		}
		return null;
	}

	/**
	 * A method to find a shark object relative to a name
	 * 
	 * @param name
	 *            The name of the shark input as a string
	 * @return The shark object relative to the name
	 */
	public Shark getSharkFromName(String name) {
		return jawAPI.getShark(name);
	}

	/**
	 * A method to get the url of the video associated with a given shark name
	 * 
	 * @param name
	 *            The name of the shark to be searched for as a string
	 * @return The URL of the video as a string
	 */
	public String getVideo(String name) {
		return jawAPI.getVideo(name);
	}

	/**
	 * A getter method for the sharkFilter ArrayList
	 * 
	 * @return The ArrayList sharkFilter
	 */
	public ArrayList<SharkTime> getSharkList() {
		return sharkFilter;
	}

	/**
	 * A getter method for the acknowledgement
	 * 
	 * @return The acknowledgement as a string
	 */
	public String getAcknowledgement() {
		return jawAPI.getAcknowledgement();
	}

	/**
	 * A getter method for the location object associated with a shark
	 * 
	 * @param sharkName
	 *            The sharks name input as a string
	 * @return The location object associated a given shark
	 */
	public Location getLocation(String sharkName) {
		return jawAPI.getLastLocation(sharkName);
	}

	/**
	 * A getter method for all of the tag locations
	 * 
	 * @return The ArrayList of all the tag locations
	 */
	public ArrayList<String> getTagLocations() {
		return jawAPI.getTagLocations();
	}
}