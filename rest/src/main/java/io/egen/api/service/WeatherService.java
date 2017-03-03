/**
 * 
 */
package io.egen.api.service;

import java.util.List;
import java.util.Optional;

import io.egen.api.entity.Weather;

public interface WeatherService {

	public Weather create(Weather weather);

	public List<Weather> findAll();

	public Weather findByCity(String city);

}
