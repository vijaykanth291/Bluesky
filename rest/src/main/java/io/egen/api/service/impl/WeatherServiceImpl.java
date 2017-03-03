/**
 * 
 */
package io.egen.api.service.impl;

import java.util.List;
import java.util.Optional;

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
		return repository.create(weather);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Weather> findAll() {
		return repository.findAll();
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
}