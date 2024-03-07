package com.codingdojo.projectsandalgorithms.groupproject.gotel.Services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HotelService {
	
	private final String apiKey = "b6756322e1msh9b1ff40d24c74c7p1bd389jsnf60360a04cc3";
	
	public HotelService() {}

	public String getDestinationId(String q) throws IOException, InterruptedException {
		
		String convertedQuery = q.replaceAll(" ", "%20" );
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://booking-com15.p.rapidapi.com/api/v1/hotels/searchDestination?query="+convertedQuery))
				.header("X-RapidAPI-Key", apiKey)
				.header("X-RapidAPI-Host", "booking-com15.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode jsonNode = objectMapper.readTree(response.body());

	    List<JsonNode> destinations = new ArrayList<>();
	    for (JsonNode result : jsonNode.get("data")) {
	    	destinations.add(result);
	    }
	    String destinationId = destinations.get(0).get("dest_id").toString().replace("\"", "");
		
		return destinationId;
	}
	
	public List<JsonNode> getHotels(String id, String q, LocalDate arrival, LocalDate departure) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://booking-com15.p.rapidapi.com/api/v1/hotels/searchHotels?dest_id="+id+"&search_type=CITY&arrival_date="+arrival+"&departure_date="+departure+"&adults=1&children_age=0%2C17&room_qty=1&page_number=1&languagecode=en-us&currency_code=USD"))
				.header("X-RapidAPI-Key", "b6756322e1msh9b1ff40d24c74c7p1bd389jsnf60360a04cc3")
				.header("X-RapidAPI-Host", "booking-com15.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode jsonNode = objectMapper.readTree(response.body());
	    
	    List<JsonNode> hotels = new ArrayList<>();
	    for (JsonNode result : jsonNode.get("data").get("hotels")) {
	    	hotels.add(result);
	    }
		
		
		return hotels;
	}
	
	public JsonNode getHotelDetails(String hotelId, LocalDate arrival, LocalDate departure) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://booking-com15.p.rapidapi.com/api/v1/hotels/getHotelDetails?hotel_id="+hotelId+"&arrival_date="+arrival+"&departure_date="+departure+"&adults=1&children_age=1%2C17&room_qty=1&languagecode=en-us&currency_code=USD"))
				.header("X-RapidAPI-Key", "b6756322e1msh9b1ff40d24c74c7p1bd389jsnf60360a04cc3")
				.header("X-RapidAPI-Host", "booking-com15.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		
		ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode jsonNode = objectMapper.readTree(response.body());
	    
	    JsonNode hotelDetails = jsonNode.get("data");
		
		return hotelDetails;
		
	}
	
}
