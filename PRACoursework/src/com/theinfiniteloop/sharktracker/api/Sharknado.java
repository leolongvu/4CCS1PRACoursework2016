package com.theinfiniteloop.sharktracker.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sharknado {

	private static final Pattern p = Pattern.compile("(\"elevation\"\\s:\\s)(\\-?\\d*)");

	// Takes in coordinates and checks the elevation and returns
	// true if higher than 0 meters
	public static boolean checkSharknado(String location) {

		// uses googles elevation api
		String query = "https://maps.googleapis.com/maps/api/elevation/json?locations=";

		String key = "&key=AIzaSyD6_J9SKMDa57kjk-k6vuMmYRTAPotiSJU";

		String text = null;

		try {
			String weburl = query + location + key;
			URL url = new URL(weburl);

			InputStream is = url.openStream();

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			text = new String(os.toByteArray(), "UTF-8");
			is.close();
			os.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		String elevation = null;
		Matcher m = p.matcher(text);
		if (m.find()) {
			elevation = m.group(2);
		}
		if (Integer.parseInt(elevation) > 0) {
			System.out.println("Sharknado found for elevation: " + elevation + "m");
			return true;
		} else
			return false;
	}
}
