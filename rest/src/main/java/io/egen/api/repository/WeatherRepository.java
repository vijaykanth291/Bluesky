/**
 * 
 */
package io.egen.api.repository;

import java.util.List;
import java.util.Optional;

import io.egen.api.entity.Weather;

public interface WeatherRepository {

	public Weather create(Weather weather);

	public List<Weather> findAll();

	public Optional<Weather> findByCity(String city);

	public Optional<Weather> findById(String id);

}
