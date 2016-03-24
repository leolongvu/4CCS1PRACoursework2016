package com.theinfiniteloop.sharktracker.api;

import java.util.ArrayList;

import api.jaws.Location;
import api.jaws.Shark;

/**
 * 
 * @authors The Infinite Loops
 *
 *	A class which stores the favourite sharks of a particular user in an ArrayList
 */
public class Favourite {
	private String user;

	private ArrayList<SharkLocation> favouriteShark;
	private Query query;

	private final double kingsLat = 51.51193;
	private final double kingsLon = -0.11698;

	/**
	 * Constructor for the Favourite class, initialises all the un-initialised fields
	 * and takes a query as argument why?
	 */
	public Favourite(Query query) {
		user = "user";
		this.query = query;
		favouriteShark = new ArrayList<SharkLocation>();
	}

	/**
	 * Sets the user for the class by overwriting userName with an input string.
	 * 
	 * @param username A string input which is the required userName to be set
	 */
	public void setUser(String username) {
		user = username;
	}

	/**
	 * Adds a shark to the ArrayList favouriteShark after checking its distance against 
	 * all the other favourite sharks and then adding into the ArrayList in ascending distance order,
	 * or at the end if it farthest away.
	 * 
	 * @param shark The shark to be added to the favourites
	 */
	public void addFavourite(Shark shark) {
		Location l = query.getLocation(shark.getName());
		double lat = l.getLatitude();
		double lon = l.getLongitude();
		double distance = getDistance(lat, lon);
		boolean loopThroughAll = true;
		for (int i = 0; i < favouriteShark.size(); i++) {
			if (favouriteShark.get(i).getDistance() > distance) {
				SharkLocation sharkLocation = new SharkLocation(shark);
				sharkLocation.setDistance(distance);
				sharkLocation.setLat(lat);
				sharkLocation.setLon(lon);
				favouriteShark.add(i, sharkLocation);
				loopThroughAll = false;
				break;
			}
		}
		if (loopThroughAll == true) {
			SharkLocation sharkLocation = new SharkLocation(shark);
			sharkLocation.setDistance(distance);
			sharkLocation.setLat(lat);
			sharkLocation.setLon(lon);
			favouriteShark.add(sharkLocation);
		}
	}
	/**
	 *  Clears the ArrayList of favourite sharks
	 */
	public void clearFavourite() {
		favouriteShark.clear();
	}
	
	/**
	 * Checks the input sharks name against all the shark names in the favouriteShark ArrayList
	 * then returns the index position if the names match. Otherwise it returns -1
	 * 
	 * @param shark The input shark to be compared
	 * @return The index position of the favourite shark
	 */
	public int indexOfShark(Shark shark) {
		for (int i = 0; i < favouriteShark.size(); i++) {
			if (shark.getName().equals(favouriteShark.get(i).getShark().getName())) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Removes a given shark from the favourites list, if the index of the shark is not equal -1
	 * 
	 * @param shark The input shark to be removed
	 */
	public void removeFavourite(Shark shark) {
		int i = indexOfShark(shark);
		if (i != -1) {
			favouriteShark.remove(i);
		}
	}
	/**
	 * Calculates the distance to the shark using the radius of the earth and 
	 * longitude and latitude values relative to Kings College
	 * 
	 * @param l The location of the point and therefore the second point in the distance calculation
	 * @return The distance to the point defined
	 */
	private double getDistance(double lat, double lon) {
		final int R = 6371; // Radius of the earth

		Double latDistance = Math.toRadians(lat - kingsLat);
		Double lonDistance = Math.toRadians(lon - kingsLon);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat))
				* Math.cos(Math.toRadians(kingsLat)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c;
		                                          
		return distance;
	}
	/**
	 *  Gets the ArrayList favouriteShark
	 * @return The favouriteShark ArrayList
	 */
	public ArrayList<SharkLocation> getFavouriteSharkList() {
		return favouriteShark;
	}
}