package com.theinfiniteloop.sharktracker.api;

import java.util.ArrayList;

import api.jaws.Location;
import api.jaws.Shark;

public class Favourite {
	private String user;

	private ArrayList<SharkLocation> favouriteShark;
	private Query query;

	private final double kingsLat = 51.51193;
	private final double kingsLon = -0.11698;

	public Favourite(Query query) {
		user = "user";
		this.query = query;
		favouriteShark = new ArrayList<SharkLocation>();
	}

	public void setUser(String username) {
		user = username;
	}

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
	
	public void clearFavourite() {
		favouriteShark.clear();
	}
	
	public int indexOfShark(Shark shark) {
		for (int i = 0; i < favouriteShark.size(); i++) {
			if (shark.getName().equals(favouriteShark.get(i).getShark().getName())) {
				return i;
			}
		}
		return -1;
	}
	
	public void removeFavourite(Shark shark) {
		int i = indexOfShark(shark);
		if (i != -1) {
			favouriteShark.remove(i);
		}
	}

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

	public ArrayList<SharkLocation> getFavouriteSharkList() {
		return favouriteShark;
	}
}