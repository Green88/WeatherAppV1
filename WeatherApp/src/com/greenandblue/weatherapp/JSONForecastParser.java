package com.greenandblue.weatherapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONForecastParser {
	
	private JSONObject root;
	//private final static int KELVIN = 273;
	
	public JSONForecastParser(String json)
	{
		try 
		{
			root = new JSONObject(json);
        } 
		catch (JSONException e) 
		{
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
	}
	
	public List<DayForecastEntry> getForecastData()
	{
		List<DayForecastEntry> forecast = new ArrayList<DayForecastEntry>();
		
		try
		{
			JSONArray list = root.getJSONArray("list");
			for(int i = 0; i < list.length(); i++)
			{
				DayForecastEntry entry = new DayForecastEntry();
				
				JSONObject day = list.getJSONObject(i);
				entry.setDate(day.getLong("dt"));
				entry.setHumidity(day.getInt("humidity"));
				entry.setWind(day.getDouble("speed"));
				
				JSONObject temperature = day.getJSONObject("temp");	
				entry.setDay((int)temperature.getDouble("day"));
				entry.setNight((int)temperature.getDouble("night"));
				entry.setMin((int)temperature.getDouble("min"));
				entry.setMax((int)temperature.getDouble("max"));
				
				JSONArray weather = day.getJSONArray("weather");
				JSONObject obj = weather.getJSONObject(0);
				entry.setDescription(obj.getString("main"));
				entry.setCodeId(obj.getInt("id"));

				forecast.add(entry);
			}
		}
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			Log.e("JSON Parser", "Error parsing data " + e.toString());
			e.printStackTrace();
		}
		return forecast;
	}
	
	

}
