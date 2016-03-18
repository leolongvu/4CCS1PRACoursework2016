package com.theinfiniteloop.sharktracker.controller;

import java.util.ArrayList;

import com.theinfiniteloop.sharktracker.api.Favourite;
import com.theinfiniteloop.sharktracker.api.Query;
import com.theinfiniteloop.sharktracker.api.SharkLocation;
import com.theinfiniteloop.sharktracker.api.SharkTime;
import com.theinfiniteloop.sharktracker.gui.FavouritesFrame;
import com.theinfiniteloop.sharktracker.gui.MainFrame;

import api.jaws.Shark;

public class Controller {
	private MainFrame mainFrame;
	private FavouritesFrame favouritesFrame;
	
	private Query query;
	private Favourite favourite;
	
	public void setMainFrameReference(MainFrame frame) {
		mainFrame = frame;
	}
	
	public void setFavouriteFrameReference(FavouritesFrame frame) {
		favouritesFrame = frame;
	}
	
	public void setQuery(Query query) {
		this.query = query;
	}
	
	public void setFavourite(Favourite favourite) {
		this.favourite = favourite;
	}
	
	public String getAcknowledgement() {
		return query.getAcknowledgement();
	}
	
	public ArrayList<String> getTagLocations() {
		return query.getTagLocations();
	}
	
	public void searchShark(String timeFrame, String gender, String lifeStage, String location) {
		query.implementAllSearch(timeFrame, gender, lifeStage, location);
	}
	
	public ArrayList<SharkTime> getSharkList() {
		return query.getSharkList();
	}
	
	public void addFavouriteShark(Shark shark) {
		favourite.addFavourite(shark);
	}
	
	public ArrayList<SharkLocation> getFavouriteSharkList() {
		return favourite.getFavouriteSharkList();
	}
	
	public void removeFavourite(Shark shark) {
		favourite.removeFavourite(shark);
	}
	
	public void selectShark(SharkTime s) {
		mainFrame.selectedShark(s);
	}
	
	public SharkTime getSelectedSharkTime(String name) {
		return query.getSharkTimeFromName(name);
	}
	
	public int checkFavourite(Shark shark) {
		return favourite.indexOfShark(shark);
	}
	
	public Shark getSharkFromName(String name) {
		return query.getSharkFromName(name);
	}
	
	public void clearFavourite() {
		favourite.clearFavourite();
	}
}
