package com.theinfiniteloop.sharktracker;

import com.theinfiniteloop.sharktracker.api.Favourite;
import com.theinfiniteloop.sharktracker.api.Query;
import com.theinfiniteloop.sharktracker.controller.Controller;
import com.theinfiniteloop.sharktracker.gui.InitialFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Query query = new Query();
		Favourite favourite = new Favourite();
		Controller controller = new Controller();
		
		// Set Reference for controller
		controller.setQuery(query);
		controller.setFavourite(favourite);
		
		InitialFrame gui = new InitialFrame(controller);
	}
}
