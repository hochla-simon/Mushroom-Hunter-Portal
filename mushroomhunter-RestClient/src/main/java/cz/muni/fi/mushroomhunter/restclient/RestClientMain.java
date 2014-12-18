/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.mushroomhunter.restclient;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JFrame;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Lukáš Valach
 */
public class RestClientMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RestClientException, JsonProcessingException {
//        JFrame frame = new mainFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);

        RestClientMain clientMain = new RestClientMain();
        clientMain.updateLocation();
        clientMain.createLocation();
        clientMain.getLocationList();
        clientMain.getLocationListWithOccurence();
        clientMain.getLocationDetail(12L);
//        clientMain.deleteLocation(25L);
    }

    //Send GET to URL and get array of objects
    private void getLocationList() throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LocationDto[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/pa165/rest/location/", LocationDto[].class);
        LocationDto[] locationDtoArray = responseEntity.getBody();
        for (int i = 0; i < locationDtoArray.length; i++) {
            LocationDto location = locationDtoArray[0];
            System.out.println("Id:                 " + location.getId());
            System.out.println("Name:               " + location.getName());
            System.out.println("Description:        " + location.getDescription());
            System.out.println("Near city:          " + location.getNearCity());
            System.out.println("Mushroom occurence: " + location.getMushroomOccurence());
        }
    }

    //Send GET to URL and get array of objects
    private void getLocationListWithOccurence() throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LocationDto[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/pa165/rest/location/withMushroomOccurence", LocationDto[].class);
        LocationDto[] locationDtoArray = responseEntity.getBody();
        for (int i = 0; i < locationDtoArray.length; i++) {
            LocationDto location = locationDtoArray[0];
            System.out.println("Id:                 " + location.getId());
            System.out.println("Name:               " + location.getName());
            System.out.println("Description:        " + location.getDescription());
            System.out.println("Near city:          " + location.getNearCity());
            System.out.println("Mushroom occurence: " + location.getMushroomOccurence());
        }
    }

    //Send GET to URL and get LocationDto
    private LocationDto getLocationDetail(Long locationId) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        LocationDto location = restTemplate.getForObject("http://localhost:8080/pa165/rest/location/" + locationId, LocationDto.class);
        System.out.println("Id:                 " + location.getId());
        System.out.println("Name:               " + location.getName());
        System.out.println("Description:        " + location.getDescription());
        System.out.println("Near city:          " + location.getNearCity());
        System.out.println("Mushroom occurence: " + location.getMushroomOccurence());
        return location;
    }

    //Create location with random name
    private void createLocation() throws JsonProcessingException {
        //Prepare random number of new location
        Random randomGenerator = new Random();
        int locationNumber = randomGenerator.nextInt(1000);

        LocationDto location = new LocationDto();
        location.setName("Location-Name" + locationNumber);
        location.setDescription("Location-Description");
        location.setNearCity("Location-NearCity");
        location.setMushroomOccurence(10);
        System.out.println("Creating Location: " + location.getName());

        //Prepare http header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(MediaType.ALL);
        headers.setAccept(mediaTypeList);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(location);
        HttpEntity request = new HttpEntity(json, headers);

        RestTemplate restTemplate = new RestTemplate();
        Long[] result = restTemplate.postForObject("http://localhost:8080/pa165/rest/location", request, Long[].class);
    }

    //Update location (description)
    private void updateLocation() throws JsonProcessingException {
        LocationDto location = this.getLocationDetail(12L);
        location.setDescription(location.getDescription() + "-Updated");

        //Prepare random number of new location
        Random randomGenerator = new Random();
        int locationNumber = randomGenerator.nextInt(1000);

        System.out.println("Updating Location: " + location.getName());
        System.out.println("New description: " + location.getDescription());

        //Prepare http header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(location);
        HttpEntity request = new HttpEntity(json, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LocationDto> newlocation = restTemplate.exchange("http://localhost:8080/pa165/rest/location", HttpMethod.PUT, request, LocationDto.class);
        LocationDto updatedLocation = newlocation.getBody();
    }

    //Delete Location
    private void deleteLocation(Long locationId) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Deleting location with ID: " + locationId);
        restTemplate.delete("http://localhost:8080/pa165/rest/location/" + locationId);
        System.out.println("Location was deleted.");
    }
}
