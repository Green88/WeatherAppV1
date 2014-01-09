package com.greenandblue.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class InnerJSONParser {
	
	private JSONObject root;
	
	public InnerJSONParser(String JSONString)
	{
		try 
		{
			root = new JSONObject(JSONString);
        } 
		catch (JSONException e) 
		{
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
	}
	
	public String getImageCode(int code)
	{
		String imageCode = null;
		try {
			imageCode = root.getString(Integer.toString(code));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		return imageCode;
	}

}
