package com.theinfiniteloop.sharktracker.api;

import api.jaws.Shark;

public class SharkTime {
	private Shark shark;
	private String time;
	
	public SharkTime(Shark shark, String time) {
		this.shark = shark;
		this.time = time;
	}
	
	public Shark getShark() {
		return shark;
	}
	
	public String getTime() {
		return time;
	}
}
