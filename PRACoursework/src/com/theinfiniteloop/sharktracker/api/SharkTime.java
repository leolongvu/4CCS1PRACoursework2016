package com.theinfiniteloop.sharktracker.api;

import api.jaws.Shark;

/**
 * The SharkTime class which bundles together a shark object 
 * with a time string 
 * @author The infinite loops
 *
 */
public class SharkTime {
	private Shark shark;
	private String time;
	
	/**
	 * Constructor for the SharkTime Class
	 * @param shark The input Shark object 
	 * @param time The input time as a string
	 */
	public SharkTime(Shark shark, String time) {
		this.shark = shark;
		this.time = time;
	}
	
	/**
	 * A getter method for the shark
	 * @return The shark object
	 */
	public Shark getShark() {
		return shark;
	}
	
	/**
	 * a getter method for the time 
	 * @return The time as a string 
	 */
	public String getTime() {
		return time;
	}
}
