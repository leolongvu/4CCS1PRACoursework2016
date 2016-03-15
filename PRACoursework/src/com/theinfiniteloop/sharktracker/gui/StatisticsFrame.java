package com.theinfiniteloop.sharktracker.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class StatisticsFrame {

	private JFrame statistics;
	
	private JPanel trackingGraphPane;
	
	private ChartPanel trackingChartPanel;
	
	public StatisticsFrame()
	{
		
	}
	
	private static JFreeChart createTrackingChart()
	{
		
		DefaultPieDataset data = new DefaultPieDataset();
        return ChartFactory.createPieChart("Pie Chart", data);
        
     
	}
	
	
	
}