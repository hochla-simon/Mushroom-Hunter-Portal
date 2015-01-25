/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.api.service.LocationService;
import cz.fi.muni.pa165.mushroomhunter.api.service.SecurityService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lukáš Valach
 */
@RestController
@RequestMapping("/location")
public class LocationRest {

    @Autowired
    LocationService locationService;
    
    @Autowired
    SecurityService securityService;

    @RequestMapping(method = RequestMethod.GET)
    public List<LocationDto> getLocationList() {
        List<LocationDto> locationList = locationService.findAll();
        if (locationList == null) {
            locationList = new ArrayList<LocationDto>();
        }
        return locationList;
    }

    @RequestMapping(value = "/withMushroomOccurence", method = RequestMethod.GET)
    public List<LocationDto> getLocationListWithOccurence() {
        List<LocationDto> locationList = locationService.findByOccurenceWithSumOfMushrooms(false);
        if (locationList == null) {
            locationList = new ArrayList<LocationDto>();
        }
        return locationList;
    }

    @RequestMapping(value = "{locationId}", method = RequestMethod.GET)
    public LocationDto getLocationDetail(@PathVariable Long locationId) {
        return locationService.find(locationId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Long> createLocation(@RequestBody @Valid LocationDto location) {
        List<Long> resultList = new ArrayList<>();      
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        location.setOwnerId(currentUserId);
        resultList.add(locationService.save(location));
        return resultList;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public LocationDto updateLocation(@RequestBody @Valid LocationDto location) {
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        if (!securityService.hasPermissionToModifyEntity(location.getOwnerId())){
            throw new AccessDeniedException("Access denied: User "+currentUserId+" cannot update location "+location.getId());
        }
        return locationService.update(location);
    }

    @RequestMapping(value = "{locationId}", method = RequestMethod.DELETE)
    public void deleteLocation(@PathVariable Long locationId) {
        LocationDto location = locationService.find(locationId);
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        if (!securityService.hasPermissionToModifyEntity(location.getOwnerId())){
            throw new AccessDeniedException("Access denied: User "+currentUserId+" cannot delete location "+location.getId());
        }
        locationService.delete(location);
    }
}
