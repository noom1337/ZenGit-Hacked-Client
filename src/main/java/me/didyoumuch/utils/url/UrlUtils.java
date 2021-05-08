package me.didyoumuch.utils.url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlUtils {
	public static String getVersion() {
		try {
		      URL url = new URL("https://raw.githubusercontent.com/DidYouMuch/ZenGit-Hacked-Client/main/url/version.txt");
		      URLConnection urlConnection = url.openConnection();
		      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

		      String line;
		      while ((line = bufferedReader.readLine()) != null)
		      {
		        return line;
		      }
		      bufferedReader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "EXCEPTION";
	}

}
