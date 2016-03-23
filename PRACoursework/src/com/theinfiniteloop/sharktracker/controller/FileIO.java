package com.theinfiniteloop.sharktracker.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.theinfiniteloop.sharktracker.api.SharkLocation;

import api.jaws.Shark;

public class FileIO {

	private File file;
	private Controller controller;

	public FileIO(Controller controller) {
		this.controller = controller;
	}

	public FileIO(String user) {
		setFile(user);
	}

	// sets the current file as a given user name
	public void setFile(String user) {
		try {
			file = new File(user + ".txt");
			if (user.equals("User")) {
				file.delete();
			}

			if (!file.exists()) {
				file.createNewFile();
				System.out.println("File created: " + user + ".txt");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			controller.clearFavourite();
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				Shark shark = controller.getSharkFromName(line);
				controller.addFavourite(shark);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// returns an array list of all the text in a file
	public ArrayList<String> readLines() {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

	// adds a given line to the set file
	public void addLine(String lineToAdd) {
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw = new BufferedWriter(fw);
			bw.write(lineToAdd);
			bw.newLine();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteFile() {
		System.out.println("File deleted: " + file.getName());
		file.delete();
	}

	// Leo's input out using the same IO basic.
	public void writeFavouriteList(ArrayList<SharkLocation> list) {
		try {
			// Write the list
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw = new BufferedWriter(fw);
			for (SharkLocation s : list) {
				bw.write(s.getShark().getName());
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
