package com.theinfiniteloop.sharktracker.api;

import api.jaws.Shark;

public class SharkLocation {
	private Shark shark;
	private double distance;
	private double lat;
	private double lon;
	
	public SharkLocation(Shark shark) {
		this.shark = shark;
	}
	
	public Shark getShark() {
		return shark;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
}
