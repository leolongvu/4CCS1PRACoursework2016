package com.theinfiniteloop.sharktracker.controller;

import java.util.ArrayList;

import com.theinfiniteloop.sharktracker.api.Favourite;
import com.theinfiniteloop.sharktracker.api.Query;
import com.theinfiniteloop.sharktracker.api.SharkLocation;
import com.theinfiniteloop.sharktracker.api.SharkTime;
import com.theinfiniteloop.sharktracker.gui.InitialFrame;
import com.theinfiniteloop.sharktracker.gui.MainFrame;

import api.jaws.Shark;

public class Controller {
	private MainFrame mainFrame;
	private InitialFrame initialFrame;

	private Query query;
	private Favourite favourite;

	// Set reference methods
	public void setMainFrameReference(MainFrame frame) {
		mainFrame = frame;
		initialFrame.enableSearchButton();
	}

	public void setInitialFrameReference(InitialFrame initialFrame) {
		this.initialFrame = initialFrame;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public void setFavourite(Favourite favourite) {
		this.favourite = favourite;
	}

	// API getter methods
	public String getAcknowledgement() {
		return query.getAcknowledgement();
	}

	public ArrayList<String> getTagLocations() {
		return query.getTagLocations();
	}

	public ArrayList<SharkTime> getSharkList() {
		return query.getSharkList();
	}

	public ArrayList<SharkLocation> getFavouriteSharkList() {
		return favourite.getFavouriteSharkList();
	}

	public SharkTime getSelectedSharkTime(String name) {
		return query.getSharkTimeFromName(name);
	}

	public Shark getSharkFromName(String name) {
		return query.getSharkFromName(name);
	}

	// API functional methods
	public void searchShark(String timeFrame, String gender, String lifeStage, String location) {
		query.implementAllSearch(timeFrame, gender, lifeStage, location);
	}

	public void addFavourite(Shark shark) {
		favourite.addFavourite(shark);
	}

	public void removeFavourite(Shark shark) {
		favourite.removeFavourite(shark);
	}

	public int checkFavourite(Shark shark) {
		return favourite.indexOfShark(shark);
	}

	public void clearFavourite() {
		favourite.clearFavourite();
	}

	// GUI functions
	public void selectShark(SharkTime s) {
		mainFrame.selectedShark(s);
	}

	public void showSearch() {
		mainFrame.showSearch();
	}

	public void setMainFrameVisibility(boolean visible) {
		mainFrame.setVisible(visible);
	}

	public void setInitialFrameVisibility(boolean visible) {
		initialFrame.setVisible(visible);
	}
}
