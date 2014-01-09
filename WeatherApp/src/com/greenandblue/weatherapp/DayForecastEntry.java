package com.greenandblue.weatherapp;

import android.annotation.SuppressLint;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DayForecastEntry {
	
	private long date;
	private int codeId;
	private int min;
	private int max;
	private int day;
	private int night;
	private int humidity;
	private double wind;
	private String description;
	private String dayOfWeek;
	
	public DayForecastEntry()
	{
		
	}

	@SuppressLint("NewApi")
	public void setDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
        TimeZone t = TimeZone.getDefault();

        calendar.setTimeInMillis(date * 1000);
        calendar.add(Calendar.MILLISECOND, t.getOffset(calendar.getTimeInMillis()));
        String day = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
        dayOfWeek = day.substring(0, 3).toUpperCase(Locale.US);
	}
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
		setDayOfWeek();
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getNight() {
		return night;
	}

	public void setNight(int night) {
		this.night = night;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public double getWind() {
		return wind;
	}

	public void setWind(double wind) {
		this.wind = wind;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCodeId() {
		return codeId;
	}

	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}
	
	
	

}
