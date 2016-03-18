package com.theinfiniteloop.sharktracker.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.theinfiniteloop.sharktracker.api.Query;
import com.theinfiniteloop.sharktracker.api.SharkTime;

public class StatisticsFrame {
        
        private Query query;

        private JFrame statistics;
        
        private JPanel trackingGraphPane;
        
        private JLabel messageGender;
        private JLabel messageLife;
        private JLabel messageLocation;
        
        private ChartPanel genderChartPanel;
        private ChartPanel stageOfLifeChartPanel;
        private ChartPanel locationChartPanel;
        
        private DefaultPieDataset data;
        
        
        public DefaultPieDataset getData() {
                return data;
        }

        private int totalCount;
        private int maleCount;
        private int femaleCount;
        private int matureCount;
        private int immatureCount;
        private int undeterminedCount;
        
        private ArrayList<SharkTime> getSharkList;
        
        private ArrayList<String> locations;
        
        private int[] numberByLocation;
        
        public StatisticsFrame(String rangeChosen)
        {
                statistics = new JFrame();
                
                query = new Query();
                locations = query.getTagLocations();
                
                trackingGraphPane = new JPanel();
                trackingGraphPane.setLayout(new GridLayout(3,1));
                trackingGraphPane.setPreferredSize(new Dimension(800,600));
                
                getSharkList = new ArrayList<SharkTime>();
                getSharkList = query.getSharkByTimeFrame(rangeChosen);
                totalCount = getSharkList.size();
                System.out.println(totalCount);
                messageGender = new JLabel();
                messageLife = new JLabel();
                messageLocation = new JLabel();                
                
                createGenderChart();
                createStageOfLifeChart();
                createLocationChart();
                
                statistics.add(trackingGraphPane);
                statistics.setVisible(true);
                statistics.pack();
        }
        
        public void createGenderChart()
        {        
                getNumberOfSharksByGender();
                if(femaleCount+maleCount!=0)
                {
                        data = new DefaultPieDataset();
                        if(maleCount!=0)
                        {
                                data.setValue("Male", maleCount);
                        }
                        if(femaleCount!=0)
                        {
                                data.setValue("Female", femaleCount);
                        }
                        genderChartPanel = new ChartPanel(ChartFactory.createPieChart("Gender", data));
                        trackingGraphPane.add(genderChartPanel);
                }
                else
                {
                        messageGender.setText("There is not enough data about the Sharks' Gender.");
                        messageGender.setHorizontalAlignment(JLabel.CENTER);
                        trackingGraphPane.add(messageGender);
                }
        }
        
        public void createStageOfLifeChart()
        {
                getNumberOfSharksByStageOfLife();
                if(matureCount+immatureCount+undeterminedCount!=0)
                {
                        data = new DefaultPieDataset();
                
                        if(matureCount!=0)
                        {
                                data.setValue("Mature", matureCount);
                        }
                        if(immatureCount!=0)
                        {
                                data.setValue("Immature", immatureCount);
                        }
                        if(undeterminedCount!=0)
                        {
                                data.setValue("Undetermined", undeterminedCount);
                        }
                        stageOfLifeChartPanel = new ChartPanel(ChartFactory.createPieChart("Stage of Life", data));
                        trackingGraphPane.add(stageOfLifeChartPanel);
                }
                else
                {
                        messageLife.setText("There is not enough data about the Sharks' life stage.");
                        messageLife.setHorizontalAlignment(JLabel.CENTER);
                        trackingGraphPane.add(messageLife);
                }
        }
        
        public void createLocationChart()
        {
                int[] numberOfSharks=getNumberOfSharksByLocation();
                int totalNo=0;
                for(int s: numberOfSharks)
                {
                        totalNo=s+totalNo;
                }
                System.out.println(numberOfSharks.length);
                if(totalNo!=0)
                {
                        data = new DefaultPieDataset();
                        int i = 0;
                        for(String s: locations)
                        {
                                if(numberOfSharks[i]!=0)
                                {
                                        data.setValue(s,numberOfSharks[i] );
                                }
                                i++;
                        }
                        locationChartPanel = new ChartPanel(ChartFactory.createPieChart("Location", data));
                        trackingGraphPane.add(locationChartPanel);
                }
                else
                {
                        messageLocation.setText("There is not enugh data about the Sharks' location.");
                        messageLocation.setHorizontalAlignment(JLabel.CENTER);
                        trackingGraphPane.add(messageLocation);
                }
        }
        
        public void getNumberOfSharksByGender()
        {
                ArrayList<SharkTime> getSharkListTest = query.getSharkByGender("Male", getSharkList);
                maleCount = getSharkListTest.size();
                femaleCount = totalCount - maleCount;
                System.out.println(maleCount+" "+femaleCount);
                
        }
        public void getNumberOfSharksByStageOfLife()
        {
                ArrayList<SharkTime> getSharkListTest = query.getSharkByLifeStage("Mature", getSharkList);
                matureCount = getSharkListTest.size();
                getSharkListTest = query.getSharkByLifeStage("Immature", getSharkList);
                immatureCount = getSharkListTest.size();
                undeterminedCount = totalCount - matureCount - immatureCount;
                System.out.println(matureCount+" "+immatureCount+" "+undeterminedCount);
        }
        
        public int[] getNumberOfSharksByLocation()
        {
                int i = 0;
                numberByLocation = new int[locations.size()];
                for(String s: locations)
                {
                        ArrayList<SharkTime> getSharkListTest = query.getSharkByLocation(s, getSharkList);
                        numberByLocation[i] = getSharkListTest.size();
                        System.out.println(s + " " + numberByLocation[i]);
                        i++;
                }
                
                return numberByLocation;
                
        }
        public static void main(String[] args)
        {
                StatisticsFrame st = new StatisticsFrame("Week");
        }
}
