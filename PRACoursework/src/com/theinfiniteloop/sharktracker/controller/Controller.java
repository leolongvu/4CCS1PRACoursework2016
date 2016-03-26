package com.theinfiniteloop.sharktracker.controller;

import java.util.ArrayList;

import com.theinfiniteloop.sharktracker.api.Favourite;
import com.theinfiniteloop.sharktracker.api.Query;
import com.theinfiniteloop.sharktracker.api.SharkLocation;
import com.theinfiniteloop.sharktracker.api.SharkTime;
import com.theinfiniteloop.sharktracker.gui.InitialFrame;
import com.theinfiniteloop.sharktracker.gui.MainFrame;

import api.jaws.Shark;

/**
 * @author Leo, the infinite loop. Facilitates interaction between the models
 *         and views
 *
 */
public class Controller {
	private MainFrame mainFrame;
	private InitialFrame initialFrame;

	private Query query;
	private Favourite favourite;

	/**
	 * sets a reference to a search frame
	 * 
	 * @param frame
	 */
	public void setMainFrameReference(MainFrame frame) {
		mainFrame = frame;
		initialFrame.enableSearchButton();
	}

	/**
	 * sets a reference to an initial frame
	 * 
	 * @param initialFrame
	 */
	public void setInitialFrameReference(InitialFrame initialFrame) {
		this.initialFrame = initialFrame;
	}

	/**
	 * sets a reference to a query
	 * 
	 * @param query
	 */
	public void setQuery(Query query) {
		this.query = query;
	}

	/**
	 * sets a reference to a favourites list
	 * 
	 * @param favourite
	 */
	public void setFavourite(Favourite favourite) {
		this.favourite = favourite;
	}

	/**
	 * gets the acknowledgement string from the API
	 * 
	 * @return a string
	 */
	public String getAcknowledgement() {
		return query.getAcknowledgement();
	}

	/**
	 * gets all the tagged locations
	 * 
	 * @return an array list
	 */
	public ArrayList<String> getTagLocations() {
		return query.getTagLocations();
	}

	/**
	 * gets a list of all the sharks from a search
	 * 
	 * @return an array list
	 */
	public ArrayList<SharkTime> getSharkList() {
		return query.getSharkList();
	}

	/**
	 * @return
	 */
	public ArrayList<SharkLocation> getFavouriteSharkList() {
		return favourite.getFavouriteSharkList();
	}

	/**
	 * gets a shark time object from a given shark name from the searched list
	 * 
	 * @param name
	 *            of the shark
	 * @return the shark time object
	 */
	public SharkTime getSelectedSharkTime(String name) {
		return query.getSharkTimeFromName(name);
	}

	/**
	 * returns all the sharks in a particular time frame
	 * 
	 * @param timeFrame
	 * @return array list of shark time objects
	 */
	public ArrayList<SharkTime> getSharkByTimeFrame(String timeFrame) {
		return query.getSharkByTimeFrame(timeFrame);
	}

	/**
	 * returns all the sharks at a particular life stage
	 * 
	 * @param lifeStage
	 * @param sharkFilter
	 * @return array list of shark time objects
	 */
	public ArrayList<SharkTime> getSharkByLifeStage(String lifeStage, ArrayList<SharkTime> sharkFilter) {
		return query.getSharkByLifeStage(lifeStage, sharkFilter);
	}

	/**
	 * returns all the sharks at a location
	 * 
	 * @param location
	 * @param sharkFilter
	 * @return array list of shark time objects
	 */
	public ArrayList<SharkTime> getSharkByLocation(String location, ArrayList<SharkTime> sharkFilter) {
		return query.getSharkByLocation(location, sharkFilter);
	}

	/**
	 * returns all the sharks of a gender
	 * 
	 * @param gender
	 * @param sharkFilter
	 * @return array list of shark time objects
	 */
	public ArrayList<SharkTime> getSharkByGender(String gender, ArrayList<SharkTime> sharkFilter) {
		return query.getSharkByGender(gender, sharkFilter);
	}

	/**
	 * gets a shark from the api from a given name
	 * 
	 * @param name
	 *            of the shark
	 * @return a shark object
	 */
	public Shark getSharkFromName(String name) {
		return query.getSharkFromName(name);
	}
	
	/**
	 * method to get the video link of a shark from the query class
	 * @param name
	 * @return
	 */
	public String getVideo(String name){
		return query.getVideo(name);
	}
	
	public ArrayList<String> getAllSharkNames() {
		return query.getAllSharkNames();
	}

	/**
	 * searches for sharks on given constraints
	 * 
	 * @param timeFrame
	 * @param gender
	 * @param lifeStage
	 * @param location
	 */
	public void searchShark(String timeFrame, String gender, String lifeStage, String location) {
		query.implementAllSearch(timeFrame, gender, lifeStage, location);
	}

	/**
	 * adds a shark to the favourite list
	 * 
	 * @param shark
	 */
	public void addFavourite(Shark shark) {
		favourite.addFavourite(shark);
	}

	/**
	 * removes a shark from the favourite list
	 * 
	 * @param shark
	 */
	public void removeFavourite(Shark shark) {
		favourite.removeFavourite(shark);
	}

	/**
	 * checks the favourite list to see if the shark is in the list
	 * 
	 * @param shark
	 * @return
	 */
	public int checkFavourite(Shark shark) {
		return favourite.indexOfShark(shark);
	}

	/**
	 * clears the list of favourites
	 */
	public void clearFavourite() {
		favourite.clearFavourite();
	}

	/**
	 * displays a selected shark in the mainframe
	 * 
	 * @param s
	 *            shark time object
	 */
	public void selectShark(SharkTime s) {
		mainFrame.selectedShark(s);
	}

	/**
	 * shows the search screen on the main frame
	 */
	public void showSearch() {
		mainFrame.showSearch();
	}

	/**
	 * makes the main frame visible or not
	 * 
	 * @param visible
	 */
	public void setMainFrameVisibility(boolean visible) {
		mainFrame.setVisible(visible);
	}

	/**
	 * makes the initial frame visible or not
	 * 
	 * @param visible
	 */
	public void setInitialFrameVisibility(boolean visible) {
		initialFrame.setVisible(visible);
	}
}
