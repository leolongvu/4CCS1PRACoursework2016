package com.theinfiniteloop.sharktracker.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

        // sets the current file as a given username
        public void setFile(String user) {
                try {
                        file = new File(user + ".txt");
                        if (user.equals("user")) {
                                file.delete();
                        }

                        if (!file.exists()) {
                                file.createNewFile();
                                System.out.println("File created: " + user + ".txt");
                        }
                        System.out.println("File set: " + user + ".txt");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        // returns an array list of strings for every line in the file
        public int readFile() {
                int count = 0;
                try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        controller.clearFavourite();
                        String line;
                        while ((line = reader.readLine()) != null) {
                                count++;
                                Shark shark = controller.getSelectedSharkTime(line).getShark();
                                controller.addFavourite(shark);
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return count;
        }

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

        // removes a given line from the file
        public void removeLine(String lineToRemove) {

                try {

                        File inFile = file;

                        if (!inFile.isFile()) {
                                return;
                        }

                        // Construct the new file that will later be renamed to the original
                        // filename.
                        File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

                        BufferedReader br = new BufferedReader(new FileReader(file));
                        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

                        String line = null;

                        // Read from the original file and write to the new
                        // unless content matches data to be removed.
                        while ((line = br.readLine()) != null) {

                                if (!line.trim().equals(lineToRemove)) {

                                        pw.println(line);
                                        pw.flush();
                                }
                        }
                        pw.close();
                        br.close();

                        // Delete the original file
                        if (!inFile.delete()) {
                                System.out.println("Could not delete file");
                                return;
                        }
                        // Rename the new file to the filename the original file had.
                        if (!tempFile.renameTo(inFile))
                                System.out.println("Could not rename file");

                } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                } catch (IOException ex) {
                        ex.printStackTrace();
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

