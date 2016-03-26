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

/**
 * @author Raf, Leo the infinite loop this class facilitates saving, deleting and
 *         reading of physical files
 *
 */
public class FileIO {

	private File file;
	private Controller controller;

	/**
	 * constructor where you need a reference to a controller
	 * 
	 * @param controller
	 */
	public FileIO(Controller controller) {
		this.controller = controller;
	}

	/**
	 * constructor where you only need a user name
	 * 
	 * @param user
	 */
	public FileIO(String user) {
		setFile(user);
	}

	/**
	 * sets the current file as a given user name, if the file already exists
	 * then it just loads that. Also, if the name is "User" which is what we
	 * have given to the default user, the it resets it every time it is loaded
	 * 
	 * @param user
	 */
	public void setFile(String user) {
		try {
			file = new File("data/" + user + ".txt");
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

	/**
	 * gives the constructor the names all the sharks in the file
	 */
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

	/**
	 * returns all the text as an array list, where each line is a new string in
	 * the list
	 * 
	 * @return array list of the text
	 */
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

	/**
	 * adds a given line to the file
	 * 
	 * @param lineToAdd
	 *            which is a string
	 */
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

	/**
	 * deletes a selected file
	 */
	public void deleteFile() {
		System.out.println("File deleted: " + file.getName());
		file.delete();
	}

	/**
	 * this method adds a given array list to a file as text
	 * 
	 * @param list
	 */
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
