package com.theinfiniteloop.sharktracker.gui;

import com.theinfiniteloop.sharktracker.controller.FileIO;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.theinfiniteloop.sharktracker.api.Query;
import com.theinfiniteloop.sharktracker.api.SharkTime;

public class SharkOfTheDayFrame extends JFrame {

        private Query query;

        private JPanel jpShark;

        private ArrayList<String> sharkList;

        private String sharkOTD;

        public SharkOfTheDayFrame(String rangeChosen) {
                query = new Query();

                jpShark = new JPanel();
                jpShark.setLayout(new FlowLayout());

                sharkList = new ArrayList<String>();
                sharkList = query.getAllSharkNames();

                this.add(jpShark);
        }

        public void setDate() {
                Calendar calendar = Calendar.getInstance();
                String dayOfMonth = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
                FileIO file = new FileIO("SharkOfTheDay");

                if (!dayOfMonth.equals(file.readLines().get(0))) {
                        System.out.println("new day");
                        file.deleteFile();
                        file.setFile("SharkOfTheDay");

                        file.addLine(dayOfMonth);

                        System.out.println("day added");
                        Random rnd = new Random();
                        int i = rnd.nextInt(sharkList.size() - 1);
                        sharkOTD = sharkList.get(i);
                        file.addLine(sharkOTD);
                }
                else {
                        System.out.println("same day");
                        sharkOTD = file.readLines().get(1);
                }

                System.out.println(sharkOTD);
        }

        public static void main(String[] args) {
                SharkOfTheDayFrame shark = new SharkOfTheDayFrame("Week");
                shark.setDate();
        }
}

