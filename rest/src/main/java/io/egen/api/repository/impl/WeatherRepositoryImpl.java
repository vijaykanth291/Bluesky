/**
 * 
 */
package io.egen.api.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.Weather;
import io.egen.api.entity.Wind;
import io.egen.api.repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Weather create(Weather weather) {
		Wind wind = weather.getWind();
		em.persist(weather);
		em.persist(wind);
		return weather;
	}

	@Override
	public List<Weather> findAll() {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findAll", Weather.class);
		return query.getResultList();
	}

	@Override
	public Optional<Weather> findById (String id) {
		return Optional.ofNullable(em.find(Weather.class, id));
	}

	@Override
	public Optional<Weather> findByCity(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("city", city);
		List<Weather> weathers = query.getResultList();
		if (!weathers.isEmpty()) {
			return Optional.of(weathers.get(0));
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public Double findHourlyAvgByCity(String city, int field) {
		Query query = em.createNamedQuery("Weather.findHourlyAvgByCity");
		query.setParameter("city", city);
		List<Object[]> weathers = query.getResultList();
		if (!weathers.isEmpty()) {
			double temp = -1d;
			for(Object[] weather: weathers) {
				temp = (double) weather[field];
			}
			return temp;
		} else {
			return -1d;
		}
	}
	
	@Override
	public Double findDailyAvgByCity(String city, int field) {
		Query query = em.createNamedQuery("Weather.findDailyAvgByCity");
		query.setParameter("city", city);
		List<Object[]> weathers = query.getResultList();
		if (!weathers.isEmpty()) {
			double temp = -1d;
			for(Object[] weather: weathers) {
				temp = (double) weather[field];
			}
			return temp;
		} else {
			return -1d;
		}
	}
}
