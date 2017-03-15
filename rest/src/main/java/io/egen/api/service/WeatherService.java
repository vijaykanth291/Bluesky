/**
 * 
 */
package io.egen.api.service;

import java.util.Set;
import java.util.List;
import io.egen.api.entity.Weather;

public interface WeatherService {

	public Weather create(Weather weather);

	public List<Weather> findAll();

	public Set<String> findAllCities();
	
	public Weather findByCity(String city);
	
	public int findLatestTemperatureByCity (String city);
	
	public int findLatestHumidityByCity (String city);
	
	public double findHourlyAvgByCity(String city, String field);
	
	public double findDailyAvgByCity(String city, String field);

}
