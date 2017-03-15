/**
 * 
 */
package io.egen.api.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.Weather;
import io.egen.api.exception.NotFoundException;
import io.egen.api.repository.WeatherRepository;
import io.egen.api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherRepository repository;

	public WeatherServiceImpl(WeatherRepository repository) {
		this.repository= repository;
	}

	@Override
	@Transactional
	public Weather create(Weather weather) {
		return repository.save(weather);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Weather> findAll() {
		return repository.findAll();
	}

	@Override
	public Set<String> findAllCities() {
		Set<String> cities = new HashSet<>();
		List<Weather> weatherList = findAll();
		for(Weather weather: weatherList) {
			cities.add(weather.getCity());
		}
		return cities;
	}
	
	@Override
	public Weather findByCity(String city) {
		Optional<Weather> weather = repository.findByCity(city);
		if (weather.isPresent()) {
			return weather.get();
		}else {
			throw new NotFoundException("Weather data for the city" + city + "does not exist");
		}
	}

	@Override
	public int findLatestTemperatureByCity(String city) {
		Weather weather = findByCity(city);
		if(weather != null) {
			return weather.getTemperature();
		} else {
			return -200;
		}
	}
	
	@Override
	public int findLatestHumidityByCity(String city) {
		Weather weather = findByCity(city);
		if(weather != null) {
			return weather.getHumidity();
		} else {
			return -1;
		}
	}
}