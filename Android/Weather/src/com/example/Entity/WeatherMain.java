package com.example.Entity;

public class WeatherMain {
	public String Temp;
	public String humidity;
	public String temp_min;
	public String temp_max;
	public String getTemp() {
		return Temp;
	}
	public void setTemp(String temp) {
		Temp = temp;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(String temp_min) {
		this.temp_min = temp_min;
	}
	public String getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(String temp_max) {
		this.temp_max = temp_max;
	}
	
}
