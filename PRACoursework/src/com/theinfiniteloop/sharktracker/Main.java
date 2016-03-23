package com.theinfiniteloop.sharktracker;

import com.theinfiniteloop.sharktracker.api.Favourite;
import com.theinfiniteloop.sharktracker.api.Query;
import com.theinfiniteloop.sharktracker.controller.Controller;
import com.theinfiniteloop.sharktracker.gui.InitialFrame;
import com.theinfiniteloop.sharktracker.gui.MainFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller controller = new Controller();
		
		Query query = new Query();
		Favourite favourite = new Favourite(query);
		
		// Set Reference for controller
		controller.setQuery(query);
		controller.setFavourite(favourite);
			
		InitialFrame initial = new InitialFrame(controller);
		controller.setInitialFrameReference(initial);
		MainFrame search = new MainFrame(controller);
		controller.setMainFrameReference(search);
	}
}
