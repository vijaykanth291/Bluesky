
package io.egen.api.Controller;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.egen.api.entity.Weather;
import io.egen.api.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "weather")
public class WeatherController {

	@Autowired
	private WeatherService service;

	/**
	 * 
	 * To redirect to Swagger UI by default
	 */
	@RequestMapping(value= "/", method=RequestMethod.GET)
	@ApiIgnore
	public ModelAndView method() {
		return new ModelAndView("redirect:" + "/api/swagger-ui.html");

	}

	/**
	 * 
	 * Consuming Weather data from Mocker
	 */
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST, value="/weather")
	@ApiOperation(value = "Create", notes = "Gets the data from the Mocker")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Weather create(@RequestBody Weather weather){
		System.out.println("Posting: "+weather.getCity());
		return service.create(weather);
	}

	/**
	 * 
	 * Returns list of Cities for which weather is captured till now
	 */
	@RequestMapping(method=RequestMethod.GET, value="/cities")
	@ApiOperation(value = "Get list of cities", notes = "Returns the list of cities that have ever reported")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Set<String> findAll(){
		return service.findAllCities();
	}

	/**
	 * 
	 * Returns Latest Weather for a given City
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/{city}/weather")
	@ApiOperation(value = "Get Weather by city", notes = "Returns weather data for a  city")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Weather findWeatherByCity(@PathVariable("city") String city) throws Exception {
		if(!isValidCity(city)) {
			throw new Exception("Please enter valid city");
		}
		return service.findByCity(city);
	}

	/**
	 * 
	 * Returns Latest Temperature for a given City
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/{city}/temperature")
	@ApiOperation(value = "Get the latest value of temeperature for a city", notes = "Returns the latest temperature value for a city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public int findTemperatureByCity(@PathVariable("city") String city) throws Exception {
		if(!isValidCity(city)) {
			throw new Exception("Please enter valid city");
		}
		return service.findLatestTemperatureByCity(city);
	}

	/**
	 * 
	 * Returns Latest Humidity for a given City
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/{city}/humidity")
	@ApiOperation(value = "Get the latest value of humidity for a city", notes = "Returns the latest humidity value for a city")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public int findHumidityByCity(@PathVariable("city") String city) throws Exception {
		if(!isValidCity(city)) {
			throw new Exception("Please enter valid city");
		}
		return service.findLatestHumidityByCity(city);
	}

	private boolean isValidCity(String city) {
		// checks if the city is not empty and do not contain Special Characters
		return StringUtils.isNotEmpty(city) && StringUtils.isAlphaSpace(city);
	}
}
