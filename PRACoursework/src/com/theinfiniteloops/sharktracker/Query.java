package com.theinfiniteloops.sharktracker;

import java.util.ArrayList;

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
	
	public ArrayList<Shark> getSharkByTimeFrame(String timeFrame) {
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
		ArrayList<Shark> sharkTimeFrameConstrain = new ArrayList<Shark>();
		// Illiterate through all ping objects and then call getShark() methods with 
		// parameter String name return from every ping object.
		for (int i = 0; i < pingTimeFrameConstrain.size(); i++) {
			// This will break Demeter's Law, but we do not have access to Ping class's code.
			String name = pingTimeFrameConstrain.get(i).getName(); 
			sharkTimeFrameConstrain.add(jawAPI.getShark(name));
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
		ArrayList<Shark> getSharkList = testing.getSharkByTimeFrame("Month");
		for (int i = 0; i < getSharkList.size(); i++) {
			System.out.println(getSharkList.get(i).getName());
		}
		System.out.println("Raf test");
	}
}
