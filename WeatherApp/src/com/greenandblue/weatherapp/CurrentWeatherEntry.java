package com.greenandblue.weatherapp;

public class CurrentWeatherEntry {

	private String country;
	private String city;
	private String weatherDescription;
	private int currTemperature;
	//private float minTemperature;
	//private float maxTemperature;
	private int codeId;
	private int humidity;
	private double windSpeed;
	
	public CurrentWeatherEntry() {	
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	public int getCurrTemperature() {
		return currTemperature;
	}

	public void setCurrTemperature(int currTemperature) {
		this.currTemperature = currTemperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public int getCodeId() {
		return codeId;
	}

	public void setCodeId(int id) {
		this.codeId = id;
	}
	
	
}
