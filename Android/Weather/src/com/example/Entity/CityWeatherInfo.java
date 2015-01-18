package com.example.Entity;

public class CityWeatherInfo {
	public String CityId;
	public String CityName;
	public Coord coord;
	public Weather weather;
	public Sys sys;
	public Wind wind;
	public WeatherMain weathermain;
	public String dt;
	public CityWeatherInfo(){}
	public String getCityId() {
		return CityId;
	}
	public void setCityId(String cityId) {
		CityId = cityId;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public Coord getCoord() {
		return coord;
	}
	public void setCoord(Coord coord) {
		this.coord = coord;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	public Sys getSys() {
		return sys;
	}
	public void setSys(Sys sys) {
		this.sys = sys;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public WeatherMain getWeathermain() {
		return weathermain;
	}
	public void setWeathermain(WeatherMain weathermain) {
		this.weathermain = weathermain;
	}
	
}
