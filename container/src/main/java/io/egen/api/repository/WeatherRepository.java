/**
 * 
 */
package io.egen.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import io.egen.api.entity.Weather;

public interface WeatherRepository extends Repository<Weather, String> {

	public Weather save(Weather weather);

	public List<Weather> findAll();

	public Optional<Weather> findByCity(String city);
	
}
