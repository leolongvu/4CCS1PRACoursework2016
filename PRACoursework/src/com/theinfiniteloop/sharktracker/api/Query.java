package com.theinfiniteloop.sharktracker.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import api.jaws.Jaws;
import api.jaws.Ping;
import api.jaws.Shark;

public class Query {
	
	private static final String privateKey = "Wi1TzUkfnMAAdJAo";
	private static final String publicKey = "5J9KvvPX5L0sPTFG";
	
	private Jaws jawAPI;
	
	public Query() {
		jawAPI = new Jaws(privateKey, publicKey, true);
	}	
	
	public ArrayList<SharkTime> getSharkByTimeFrame(String timeFrame) {
		// Setup a list of ping return by time frame, which we will be using to
		// extract the name of all sharks.
		ArrayList<Ping> pingTimeFrameConstrain = new ArrayList<Ping>();
		switch (timeFrame) {
			case "24 hours":
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
			}
			else {
				// Ping of the same shark is already existed
				// Time formatter for the Ping object
				SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					// Parse two time frame to compare them
					Date oldPingTime = inFormat.parse(toCompare.getTime());
					Date toUpdatedPingTime = inFormat.parse(pingTimeFrameConstrain.get(i).getTime());
					// So if the new found Ping refer to same shark and its time frame is after the
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
		for (HashMap.Entry<String, Ping> entry : map.entrySet())
		{
			SharkTime sharkAdd = new SharkTime(jawAPI.getShark(entry.getKey()), entry.getValue().getTime());
			sharkTimeFrameConstrain.add(sharkAdd);
		}
		return sharkTimeFrameConstrain;
	}
	
	public ArrayList<Shark> getSharkByGender(String gender, ArrayList<Shark> sharkFilter) {
		for (int i = 0; i < sharkFilter.size(); i++) {
			if (!sharkFilter.get(i).getGender().equals(gender)) {
				sharkFilter.remove(i);
			}
		}
		return sharkFilter;
	}
	
	public ArrayList<Shark> getSharkByLifeStage(String lifeStage, ArrayList<Shark> sharkFilter) {
		for (int i = 0; i < sharkFilter.size(); i++) {
			if (!sharkFilter.get(i).getStageOfLife().equals(lifeStage)) {
				sharkFilter.remove(i);
			}
		}
		return sharkFilter;
	}
	
	public ArrayList<Shark> getSharkByLocation(String lifeStage, ArrayList<Shark> sharkFilter) {
		for (int i = 0; i < sharkFilter.size(); i++) {
			if (!sharkFilter.get(i).getStageOfLife().equals(lifeStage)) {
				sharkFilter.remove(i);
			}
		}
		return sharkFilter;
	}
	
	public static void main(String[] args) {
		Query testing = new Query();
		System.out.println("Downloading data...");
		ArrayList<SharkTime> getSharkList = testing.getSharkByTimeFrame("Week");
		for (int i = 0; i < getSharkList.size(); i++) {
			System.out.println(getSharkList.get(i).getShark().getName() + " "
					+ getSharkList.get(i).getTime());
		}
		System.out.println("Finish!");
	}
}
