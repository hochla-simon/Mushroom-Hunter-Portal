/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.rest;

import javax.ws.rs.Produces;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;

/**
 *
 * @author Lukáš Valach
 */
@Controller
@RequestMapping("/location")
public class locationRest {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public LocationDto getLocationList() {
        LocationDto dto = new LocationDto();
        dto.setId(1L);
        dto.setName("Location1");
        dto.setNearCity("Brno");
        dto.setDescription("Description location1");
        return dto;
    }

    @RequestMapping(value = "{locationId}", method = RequestMethod.GET)
    @ResponseBody
    public LocationDto getLocationDetail(@PathVariable Long locationId) {
        LocationDto dto = new LocationDto();
        dto.setId(locationId);
        dto.setName("Location detail: "+ locationId);
        dto.setNearCity("Brno");
        dto.setDescription("Description location1");
        return dto;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String createLocation() {
        return "list";

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteLocation() {
        return "list";

    }

}
