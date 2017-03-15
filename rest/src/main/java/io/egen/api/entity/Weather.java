
package io.egen.api.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
	@NamedQuery(name="Weather.findAll", query="SELECT w FROM Weather w ORDER BY w.city"),
	@NamedQuery(name="Weather.findByCity", query="SELECT w FROM Weather w where w.city=:city ORDER BY w.city DESC"),
	@NamedQuery(name="Weather.findHourlyAvgByCity", query="SELECT Avg(w.humidity), Avg(w.pressure), Avg(w.temperature), \n" + 
			"Avg(wind.degree), Avg(wind.speed) From Weather w inner join Wind wind on w.wind.id = wind.id where w.city =:city group by hour(w.timestamp) , w.city"),
	@NamedQuery(name="Weather.findDailyAvgByCity", query="SELECT Avg(w.humidity), Avg(w.pressure), Avg(w.temperature), \n" + 
			"Avg(wind.degree), Avg(wind.speed) From Weather w inner join Wind wind on w.wind.id = wind.id where w.city =:city group by date(w.timestamp) , w.city")
})
public class Weather {

	
	@Id
	private String id;

	private String city;
	private String description;
	private int humidity;
	private int pressure;
	private int temperature;
	private String timestamp;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wind wind;

	public Weather() {
		this.id = UUID.randomUUID().toString();
		wind = new Wind();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public int getPressure() {
		return pressure;
	}
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
}