package com.theinfiniteloop.sharktracker;

import com.theinfiniteloop.sharktracker.api.Favourite;
import com.theinfiniteloop.sharktracker.api.Query;
import com.theinfiniteloop.sharktracker.controller.Controller;
import com.theinfiniteloop.sharktracker.gui.InitialFrame;
import com.theinfiniteloop.sharktracker.gui.LoadingScreen;
import com.theinfiniteloop.sharktracker.gui.MainFrame;

public class Main {

	public static void main(String[] args) {
		// create loading screen and controller
		LoadingScreen splashScreen = new LoadingScreen();
		Controller controller = new Controller();

		Query query = new Query();
		Favourite favourite = new Favourite(query);

		// set reference for controller
		controller.setQuery(query);
		controller.setFavourite(favourite);

		// create GUI
		InitialFrame initial = new InitialFrame(controller);
		controller.setInitialFrameReference(initial);
		MainFrame search = new MainFrame(controller);
		controller.setMainFrameReference(search);

		// stop loading screen and show initial GUI once everything is ready
		splashScreen.stopLoading();
		initial.setVisible(true);
	}
}
