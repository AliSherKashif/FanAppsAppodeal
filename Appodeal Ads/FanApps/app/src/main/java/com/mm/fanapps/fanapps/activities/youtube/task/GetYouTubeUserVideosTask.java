package com.mm.fanapps.fanapps.activities.youtube.task;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.mm.fanapps.fanapps.activities.youtube.domain.Library;
import com.mm.fanapps.fanapps.activities.youtube.domain.Video;

/**
 * This is the task that will ask YouTube for a list of videos for a specified
 * user</br> This class implements Runnable meaning it will be ran on its own
 * Thread</br> Because it runs on it's own thread we need to pass in an object
 * that is notified when it has finished
 * 
 * @author paul.blundell
 */
public class GetYouTubeUserVideosTask implements Runnable {
	// A reference to retrieve the data when this task finishes
	public static final String LIBRARY = "Library";
	// A handler that will be notified when the task is finished
	private final Handler replyTo;
	// The user we are querying on YouTube for videos
	private final String username;

	/**
	 * Don't forget to call run(); to start this task
	 * 
	 * @param replyTo
	 *            - the handler you want to receive the response when this task
	 *            has finished
	 * @param username
	 *            - the username of who on YouTube you are browsing
	 */
	public GetYouTubeUserVideosTask(Handler replyTo, String username) {
		this.replyTo = replyTo;
		this.username = username;
	}

	@Override
	public void run() {
//https://www.googleapis.com/youtube/v3/search?part=id%2C+snippet&channelId=UCaP8FIBzhvpNg6TFGqiX-qg&maxResults=50&order=date&type=video%2C+playlist&key=AIzaSyBy1B9vYblsDWNlUbrN6fK6aFA8Aj5T-84

			InputStream inputStream = null;
			HttpURLConnection urlConnection = null;
			Integer result = 0;
			String jsonString = null;
			try {
                /* forming th java.net.URL object */
				URL httpurl = new URL("https://www.googleapis.com/youtube/v3/search?key=AIzaSyBy1B9vYblsDWNlUbrN6fK6aFA8Aj5T-84&channelId=UCaP8FIBzhvpNg6TFGqiX-qg&part=snippet,id&order=date&maxResults=20");
				urlConnection = (HttpURLConnection) httpurl.openConnection();

                 /* optional request header */
				urlConnection.setRequestProperty("Content-Type", "application/json");

                /* optional request header */
				urlConnection.setRequestProperty("Accept", "application/json");

                /* for Get request */
				urlConnection.setRequestMethod("GET");
				int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
				if (statusCode == 200) {
					inputStream = new BufferedInputStream(urlConnection.getInputStream());
					jsonString = convertInputStreamToString(inputStream);
					//parseResult(response);
					result = 1; // Successful

					// Create a JSON object that we can use from the String
					JSONObject json = null;
					JSONArray jsonArray = null;
					try {
						json = new JSONObject(jsonString);
						jsonArray = json.getJSONArray("items");
					} catch (JSONException e) {

					}

					// For further information about the syntax of this request and
					// JSON-C
					// see the documentation on YouTube
					// http://code.google.com/apis/youtube/2.0/developers_guide_jsonc.html

					// Get are search result items


					// Create a list to store are videos in
					List<Video> videos = new ArrayList<Video>();
					// Loop round our JSON list of videos creating Video objects to use
					// within our app
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = null;
						try {
							jsonObject = jsonArray.getJSONObject(i);
						} catch (JSONException e) {
							e.printStackTrace();
						}

						// if it doesnt it gets the standard url
						String url, title = null, thumb = null;

						try {
							url = jsonObject.getJSONObject("id").getString("videoId");
							title = jsonObject.getJSONObject("snippet").getString(
									"title");
							thumb = jsonObject.getJSONObject("snippet")
									.getJSONObject("thumbnails").getJSONObject("high")
									.getString("url");
						} catch (JSONException ignore) {
							url = "";
							title = "";
							thumb = "";
						}

						// Create the video object and add it to our list
						if (title.equals("") || url.equals("") || thumb.equals("")) {

						} else {
							videos.add(new Video(title, url, thumb));

						}

					}
					// Create a library to hold our videos
					Library lib = new Library(username, videos);
					// Pack the Library into the bundle to send back to the Activity
					Bundle data = new Bundle();
					data.putSerializable(LIBRARY, lib);

					// Send the Bundle of data (our Library) back to the handler (our
					// Activity)
					Message msg = Message.obtain();
					msg.setData(data);
					replyTo.sendMessage(msg);

					// We don't do any error catching, just nothing will happen if this
					// task falls over
					// an idea would be to reply to the handler with a different message
					// so your Activity can act accordingly




				} else {
					result = 0; //"Failed to fetch data!";
				}
			} catch (Exception e) {
				//Log.d("exception", e.getLocalizedMessage());
			}




		}

	private String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while((line = bufferedReader.readLine()) != null){
			result += line;
		}

            /* Close Stream */
		if(null!=inputStream){
			inputStream.close();
		}
		return result;
	}

}