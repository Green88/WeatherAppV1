package com.greenandblue.weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class JSONLoader {

	//private String jsonString;
	private String [] jsonSArray;
	
	public JSONLoader()
	{
		jsonSArray = new String[2];
	}
	
	public String [] getJSONFromUrl(String urlWeather, String urlForecast) {
		BufferedReader in = null;
		try{
			HttpClient client = new DefaultHttpClient();
			URI website = new URI(urlWeather);
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String nline = System.getProperty("line.separator");
			while((line = in.readLine()) != null){
				sb.append(line + nline);
			}
			in.close();
			jsonSArray[0] = sb.toString();
			//return jsonString;
		} 
		catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally{
			if(in != null)
			{
				try{
					in.close();
					//return jsonSArray;
				} 
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		in = null;
		try{
			HttpClient client = new DefaultHttpClient();
			URI website = new URI(urlForecast);
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String nline = System.getProperty("line.separator");
			while((line = in.readLine()) != null){
				sb.append(line + nline);
			}
			in.close();
			jsonSArray[1] = sb.toString();
			//return jsonString;
		} 
		catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally{
			if(in != null)
			{
				try{
					in.close();
					//return jsonSArray;
				} 
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
        return jsonSArray;
    }
	
	

}
