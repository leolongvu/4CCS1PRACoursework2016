package com.theinfiniteloop.sharktracker.api;

import java.util.ArrayList;

import api.jaws.Location;
import api.jaws.Shark;

public class Favourite {
        private String user;
        
    private ArrayList<Shark> favouriteShark;
    private ArrayList<Double> sharksDistance;
    private Query query;

    private final double kingsLat = 51.51193;
    private final double kingsLon = -0.11698;

    public Favourite() {
            user = "user";
        query = new Query();
        favouriteShark = new ArrayList<Shark>();
        sharksDistance = new ArrayList<Double>();
    }
    
    public void setUser(String username){
            user = username;
    }

    public void addFavourite(Shark shark) {
        double distance = calculateDistance(shark);

        if (favouriteShark.size() == 0) {
            favouriteShark.add(shark);
            sharksDistance.add(distance);
        }
        else {
        	 for (int i = 0; i < favouriteShark.size(); i++) {
                 if (sharksDistance.get(i) > distance) {
                     favouriteShark.add(i, shark);
                     sharksDistance.add(i, distance);
                     break;
                 }
                 if (i + 1 >= favouriteShark.size()) {
                     favouriteShark.add(shark);
                     sharksDistance.add(distance);
                 }
             }
        }    
    }

    private double getDistance(Location l) {
        double lat = l.getLatitude();
        double lon = l.getLongitude();
        
        System.out.println(lat + "   " + lon);

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(lat - kingsLat);
        Double lonDistance = Math.toRadians(lon - kingsLon);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(kingsLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return distance;
    }

    public double calculateDistance(Shark shark) {
        Location l = query.getLocation(shark.getName());
        return getDistance(l);
    }

    public ArrayList<Shark> getFavouriteSharkList() {
        return favouriteShark;
    }
    
    public ArrayList<Double> getFavouriteDistanceList() {
        return sharksDistance;
    }
    
    public void addFavourite(String shark, double dist){
                String newShark = shark+","+dist;
                FileEditor fileEditor = new FileEditor(user);
                fileEditor.writeThis(newShark);
        }
}