package com.greenandblue.weatherapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView tvCity, tvDescription, tvTemperature, tvHumField, tvHumidity, tvWindField, tvWind;
	private TextView tvDay1, tvDescDay1, tvTempDay1, tvHumDay1, tvWindDay1;
	private TextView tvDay2, tvDescDay2, tvTempDay2, tvHumDay2, tvWindDay2;
	private TextView tvDay3, tvDescDay3, tvTempDay3, tvHumDay3, tvWindDay3;
	private TextView tvDay4, tvDescDay4, tvTempDay4, tvHumDay4, tvWindDay4;
	private ImageView ivWeather, ivDay1, ivDay2, ivDay3, ivDay4;

	private String jsonWeather = null;
	private String jsonForecast = null;
	Context con;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		con = getApplicationContext();
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("info");
		jsonWeather = bundle.getString("weather");
		jsonForecast = bundle.getString("forecast");
		setTextViews();
		setImageViews();
		showData();
	}

	private void setImageViews() {
		ivWeather = (ImageView)findViewById(R.id.ivWeather);
		ivDay1 = (ImageView)findViewById(R.id.ivDay1);
		ivDay2 = (ImageView)findViewById(R.id.ivDay2);
		ivDay3 = (ImageView)findViewById(R.id.ivDay3);
		ivDay4 = (ImageView)findViewById(R.id.ivDay4);
	}

	public static String getJSONAsStringFromAssetFolder(String fileName,Context context) 
	{
		InputStream file;
		String root = null;
		try {
			file = context.getAssets().open(fileName);
			byte[] data = new byte[file.available()];
	        file.read(data);
	        root = new String(data);
	        file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return root; 
    }
	
	private void showData()
	{
		CurrentWeatherEntry weather = new CurrentWeatherEntry();
		JSONWeatherParser parser = new JSONWeatherParser(jsonWeather);
		weather = parser.getWeatherData();
		
		JSONForecastParser fpars = new JSONForecastParser(jsonForecast);
		List<DayForecastEntry> forecast = fpars.getForecastData();
		
		setImageViews(weather, forecast);
		
		tvCity.setText(weather.getCity() + ", " + weather.getCountry());
		tvDescription.setText(weather.getWeatherDescription());
		tvTemperature.setText("" + weather.getCurrTemperature() + "\u00b0C");
		tvHumField.setText("Humidity: ");
		tvHumidity.setText("" + weather.getHumidity() + " %");
		tvWindField.setText("Wind speed: ");
		tvWind.setText("" + weather.getWindSpeed() + " mps");
		
		tvDay1.setText(forecast.get(0).getDayOfWeek());
		tvTempDay1.setText("" + forecast.get(0).getMax() + "/" + forecast.get(0).getMin() + "\u00b0C");
		tvHumDay1.setText("" + forecast.get(0).getHumidity() + "%");
		tvWindDay1.setText("" + forecast.get(0).getWind() + "mps");
		tvDescDay1.setText("" + forecast.get(0).getDescription());
		
		tvDay2.setText(forecast.get(1).getDayOfWeek());
		tvDescDay2.setText("" + forecast.get(1).getDescription());
		tvTempDay2.setText("" + forecast.get(1).getMax() + "/" + forecast.get(0).getMin() + "\u00b0C");
		tvHumDay2.setText("" + forecast.get(1).getHumidity() + "%");
		tvWindDay2.setText("" + forecast.get(1).getWind() + "mps");
		
		tvDay3.setText(forecast.get(2).getDayOfWeek());
		tvDescDay3.setText("" + forecast.get(2).getDescription());
		tvTempDay3.setText("" + forecast.get(2).getMax() + "/" + forecast.get(0).getMin() + "\u00b0C");
		tvHumDay3.setText("" + forecast.get(2).getHumidity() + "%");
		tvWindDay3.setText("" + forecast.get(2).getWind() + "mps");
		
		tvDay4.setText(forecast.get(3).getDayOfWeek());
		tvDescDay4.setText("" + forecast.get(3).getDescription());
		tvTempDay4.setText("" + forecast.get(3).getMax() + "/" + forecast.get(0).getMin() + "\u00b0C");
		tvHumDay4.setText("" + forecast.get(3).getHumidity() + "%");
		tvWindDay4.setText("" + forecast.get(3).getWind() + "mps");
		
	}
	
	private void setImageViews(CurrentWeatherEntry weather, List<DayForecastEntry> forecast)
	{
		int codeW = weather.getCodeId();
		int [] codeF = new int[forecast.size()];
		for (int i = 0; i<forecast.size(); i++)
		{
			codeF[i] = forecast.get(i).getCodeId();
		}
		String listOfCodes = getJSONAsStringFromAssetFolder("weathercodes.json", con);
		InnerJSONParser imageParse = new InnerJSONParser(listOfCodes);
		String weatherImage = imageParse.getImageCode(codeW);
		int weatherImageResource = getResources().getIdentifier("@drawable/w" + weatherImage, null, getPackageName());
		Drawable res = getResources().getDrawable(weatherImageResource);
		ivWeather.setImageDrawable(res);

		
		Drawable [] resArray = new Drawable[codeF.length];
		for(int j = 0; j<codeF.length; j++)
		{
			String forecastImage = imageParse.getImageCode(codeF[j]);
			int forecastImageResource = getResources().getIdentifier("@drawable/f" + forecastImage, null, getPackageName());
			resArray[j] = getResources().getDrawable(forecastImageResource);
		}
		
		ivDay1.setImageDrawable(resArray[0]);
		ivDay2.setImageDrawable(resArray[1]);
		ivDay3.setImageDrawable(resArray[2]);
		ivDay4.setImageDrawable(resArray[3]);
	}
	
	private void setTextViews() 
	{
		tvCity = (TextView)findViewById(R.id.tvCity);
		tvDescription = (TextView)findViewById(R.id.tvDescription);
		tvTemperature = (TextView)findViewById(R.id.tvTemperature);
		tvHumField = (TextView)findViewById(R.id.tvHumField);
		tvHumidity = (TextView)findViewById(R.id.tvHumidity);
		tvWindField = (TextView)findViewById(R.id.tvWindField);
		tvWind = (TextView)findViewById(R.id.tvWind);
		
		tvDay1 = (TextView)findViewById(R.id.day1);
		tvTempDay1 = (TextView)findViewById(R.id.tvTempDay1);
		tvHumDay1 = (TextView)findViewById(R.id.tvHumDay1);
		tvWindDay1 = (TextView)findViewById(R.id.tvWindDay1);
		tvDescDay1 = (TextView)findViewById(R.id.tvDescriptionDay1);
		
		tvDay2 = (TextView)findViewById(R.id.day2);
		tvDescDay2 = (TextView)findViewById(R.id.tvDescriptionDay2);
		tvTempDay2 = (TextView)findViewById(R.id.tvTempDay2);
		tvHumDay2 = (TextView)findViewById(R.id.tvHumDay2);
		tvWindDay2 = (TextView)findViewById(R.id.tvWindDay2);
		
		tvDay3 = (TextView)findViewById(R.id.day3);
		tvDescDay3 = (TextView)findViewById(R.id.tvDescriptionDay3);
		tvTempDay3 = (TextView)findViewById(R.id.tvTempDay3);
		tvHumDay3 = (TextView)findViewById(R.id.tvHumDay3);
		tvWindDay3 = (TextView)findViewById(R.id.tvWindDay3);
		
		tvDay4 = (TextView)findViewById(R.id.day4);
		tvDescDay4 = (TextView)findViewById(R.id.tvDescriptionDay4);
		tvTempDay4 = (TextView)findViewById(R.id.tvTempDay4);
		tvHumDay4 = (TextView)findViewById(R.id.tvHumDay4);
		tvWindDay4 = (TextView)findViewById(R.id.tvWindDay4);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
	
	
	
}
