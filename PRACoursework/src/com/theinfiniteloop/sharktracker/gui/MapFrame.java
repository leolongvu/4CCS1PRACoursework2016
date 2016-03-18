package com.theinfiniteloop.sharktracker.gui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MapFrame {

	public MapFrame(ArrayList<String> coords) {

		JFrame frame = new JFrame("Shark Map");

		String map = "http://maps.googleapis.com/maps/api/staticmap?center=London&zoom=0&scale=2&size=260x200&maptype=satellite&key=AIzaSyB-2CcsptTIkDO5X3kX45pUMbBw7YNzuVo&format=png&visual_refresh=true";

		String marker = "&markers=size:tiny%7Ccolor:0xff0000%7Clabel:1%7C";

		String locations = "";

		for (String loc : coords) {
			String locMarker = marker + loc;
			locations = locations.concat(locMarker);
		}

		try {
			String imageUrl = map + locations;
			String destinationFile = "map.jpg";
			String str = destinationFile;
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame.add(new JLabel(new ImageIcon(
				(new ImageIcon("map.jpg")).getImage().getScaledInstance(520, 400, java.awt.Image.SCALE_SMOOTH))));

		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.pack();
	}
}
