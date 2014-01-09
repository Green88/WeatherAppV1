package com.greenandblue.weatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONWeatherParser {
	
	private JSONObject root;
	private final static int KELVIN = 273;
	
	public JSONWeatherParser(String json)
	{
		try {
			root = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
	}
	
	public CurrentWeatherEntry getWeatherData()
	{
		CurrentWeatherEntry entry = new CurrentWeatherEntry();
		try {
			entry.setCity(root.getString("name"));
			
			JSONObject sys = root.getJSONObject("sys"); 
			entry.setCountry(sys.getString("country")); 
			
			JSONArray weather = root.getJSONArray("weather");
			for (int i = 0; i < weather.length(); i++) 
			{
                JSONObject ob = weather.getJSONObject(i);
                entry.setWeatherDescription(ob.getString("description"));
                entry.setCodeId(ob.getInt("id"));
			}
			
			JSONObject mainPart = root.getJSONObject("main");
			entry.setCurrTemperature((int)(mainPart.getDouble("temp") - KELVIN));
			entry.setHumidity(mainPart.getInt("humidity"));
			
			JSONObject wind = root.getJSONObject("wind");
			entry.setWindSpeed(wind.getDouble("speed"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("JSON Parser", "Error parsing data " + e.toString());
			e.printStackTrace();
		}
		return entry;
	}
	

}
