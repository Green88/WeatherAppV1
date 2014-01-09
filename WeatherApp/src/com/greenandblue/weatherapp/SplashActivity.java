package com.greenandblue.weatherapp;

import java.util.List;

import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.ProgressBar;

public class SplashActivity extends Activity {

	private ProgressBar progressBar;
	private Location location;
	private final static String URL_WEATHER = "http://api.openweathermap.org/data/2.5/weather?";
	private final static String URL_FORECAST = "http://api.openweathermap.org/data/2.5/forecast/daily?";
	private final static String APPID = "aac7b7e62eeb4fcec5ce61db7e34fa3a";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar.setEnabled(true);
		
		if(isNetworkAvailable())
		{
			location = getLocation(SplashActivity.this);
			String urlWeather = composeWeatherURL(location);
			String urlForecast = composeForecastURL(location);
			new LoadJSONTask().execute(urlWeather, urlForecast);
		}
		else
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);

			builder.setMessage(R.string.no_internet_dialog_message)
			.setTitle(R.string.no_internet_dialog_title)
			.setPositiveButton(R.string.OK_string, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int id) 
				{
					SplashActivity.this.finish();
				}
			});

			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}
	
	public static Location getLocation(Context ctx) {
		Log.i("MYTAG", "in location check");
	    LocationManager lm = (LocationManager) ctx
	            .getSystemService(Context.LOCATION_SERVICE);
	    List<String> providers = lm.getProviders(true);

	    Location l = null;

	    for (int i = providers.size() - 1; i >= 0; i--) {
	        l = lm.getLastKnownLocation(providers.get(i));
	        if (l != null)
	            break;
	    }
	    Log.i("MYTAG", "in loc check: lat " + l.getLatitude() + " and lon " + l.getLongitude());
	    
	    return l;
	}
	
	private boolean isNetworkAvailable() 
	{
		boolean isConnected = false;
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();

	    return isConnected;
	}

	private String composeWeatherURL(Location loc)
	{
		String completeURL = "";
		String latitude = String.valueOf(loc.getLatitude());
		String longtitude = String.valueOf(loc.getLongitude());
		completeURL = URL_WEATHER + "lat=" + latitude + "&lon=" + longtitude + "&APPID=" + APPID;
		return completeURL;
	}
	
	private String composeForecastURL(Location loc)
	{
		String completeURL = "";
		String latitude = String.valueOf(loc.getLatitude());
		String longtitude = String.valueOf(loc.getLongitude());
		completeURL = URL_FORECAST + "lat=" + latitude + "&lon=" + longtitude + "&mode=json&units=metric&cnt=4";
		return completeURL;
	}
	
	private class LoadJSONTask extends AsyncTask<String, Void, String []>
	{

		@Override
		protected String [] doInBackground(String... url) {

			String [] JSONstring;
			JSONLoader loader = new JSONLoader();
			JSONstring = loader.getJSONFromUrl(url[0], url[1]);
			return JSONstring;
		}

		@Override
		protected void onPostExecute(String [] result) {
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("weather", result[0]);
			bundle.putString("forecast", result[1]); 
			intent.putExtra("info", bundle);
			startActivity(intent);
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
