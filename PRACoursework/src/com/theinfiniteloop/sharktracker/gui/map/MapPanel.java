package com.theinfiniteloop.sharktracker.gui.map;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Raf, the infinite loop. The original map we created to use in the
 *         favourites, but was later replaced so we have now repurposed it to
 *         show when clicking a shark in the favourites list, to show it's
 *         location. Uses google's static map API to download an image of the
 *         Earth with the markers placed on it
 *
 */
public class MapPanel extends JPanel {

	public MapPanel(double lat, double lon) {

		String latitude = String.valueOf(lat);

		String longitude = String.valueOf(lon);

		String map = "http://maps.googleapis.com/maps/api/staticmap?center=London&zoom=0&scale=2&size=260x200&maptype=satellite&key=AIzaSyB-2CcsptTIkDO5X3kX45pUMbBw7YNzuVo&format=png&visual_refresh=true";

		String marker = "&markers=size:tiny%7Ccolor:0xff0000%7Clabel:A%7C";

		String location = latitude + "," + longitude;

		try {
			String imageUrl = map + marker + location;
			String destinationFile = "images/Map/Map Panel.jpg";
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

		this.add(new JLabel(new ImageIcon((new ImageIcon("images/Map/Map Panel.jpg")).getImage().getScaledInstance(850,
				600, java.awt.Image.SCALE_SMOOTH))));

	}
}
