/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.service.LocationServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lukáš Valach
 */
@RestController
@RequestMapping("/location")
public class locationRest {

    @Autowired
    LocationServiceImpl locationService;

    @RequestMapping(method = RequestMethod.GET)
    public List<LocationDto> getLocationList() {
//        return locationService.findAll();
        List<LocationDto> locationList = new ArrayList<>();
        LocationDto locationDto = new LocationDto();
        locationDto.setId(1L);
        locationDto.setName("BrnoLes");
        locationDto.setDescription("Desc1");
        locationDto.setNearCity("Brno");

        LocationDto locationDto2 = new LocationDto();
        locationDto2.setId(2L);
        locationDto2.setName("PrahaLes");
        locationDto2.setDescription("Desc2");
        locationDto2.setNearCity("Praha");

        locationList.add(locationDto);
        locationList.add(locationDto2);
        return locationList;
    }

    @RequestMapping(value="/withMushroomOccurence", method = RequestMethod.GET)
    public Map<LocationDto, Integer> getLocationListWithOccurence() {
//        return locationService.findAll();
        Map<LocationDto, Integer> resultMap = new HashMap<LocationDto, Integer>();
        LocationDto locationDto = new LocationDto();
        locationDto.setId(1L);
        locationDto.setName("BrnoLes");
        locationDto.setDescription("Desc1");
        locationDto.setNearCity("Brno");

        LocationDto locationDto2 = new LocationDto();
        locationDto2.setId(2L);
        locationDto2.setName("PrahaLes");
        locationDto2.setDescription("Desc2");
        locationDto2.setNearCity("Praha");

        resultMap.put(locationDto,10);
        resultMap.put(locationDto2,20);
        return resultMap;
    }

    @RequestMapping(value = "{locationId}", method = RequestMethod.GET)
    public LocationDto getLocationDetail(@PathVariable Long locationId) {
        if (locationId == (long) 1L) {
            LocationDto locationDto = new LocationDto();
            locationDto.setId(1L);
            locationDto.setName("BrnoLes");
            locationDto.setDescription("Desc1");
            locationDto.setNearCity("Brno");
            return locationDto;
        }
        if (locationId == (long) 2L) {
            LocationDto locationDto2 = new LocationDto();
            locationDto2.setId(2L);
            locationDto2.setName("PrahaLes");
            locationDto2.setDescription("Desc2");
            locationDto2.setNearCity("Praha");
            return locationDto2;
        }
        return locationService.find(locationId);
    }

    //Can accept {"location" : { "name" : "Brno", "description" : "desc", ... }
    @RequestMapping(method = RequestMethod.POST)
    public Long createLocation(@RequestBody LocationDto location) {
        return locationService.save(location);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public LocationDto updateLocation(@RequestBody LocationDto location) {
        return locationService.update(location);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteLocation(@RequestBody LocationDto location) {
        locationService.delete(location);
    }

}
