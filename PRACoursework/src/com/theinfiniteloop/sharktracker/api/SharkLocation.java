package com.theinfiniteloop.sharktracker.api;

import api.jaws.Shark;

/**
 * The location class for a shark which stores the longitude and the latitude as
 * well as the distance all as doubles
 * 
 * @author The infinite loops
 *
 */
public class SharkLocation {
	private Shark shark;
	private double distance;
	private double lat;
	private double lon;

	/**
	 * Constructor for the sharkLocation class which sets the current shark
	 * object
	 * 
	 * @param shark
	 *            The input shark object
	 */
	public SharkLocation(Shark shark) {
		this.shark = shark;
	}

	/**
	 * A getter method for the shark object
	 * 
	 * @return The shark object
	 */
	public Shark getShark() {
		return shark;
	}

	/**
	 * A getter method for the distance
	 * 
	 * @return the distance as a double
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * A getter method for the latitude
	 * 
	 * @return The latitude as a double
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * A getter method for the longitude
	 * 
	 * @return The longitude as a double
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * A setter method for the distance
	 * 
	 * @param distance
	 *            The input distance to be set as a double
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * A setter method for the latitude
	 * 
	 * @param lat
	 *            The latitude input as a double
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * A setter method for the longitude
	 * 
	 * @param lon
	 *            The longitude input as a double
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
}
