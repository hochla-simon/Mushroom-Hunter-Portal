/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.converter;

import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Šimon Hochla
 */
@Component
public class LocationConverter {

    /**
     * Convert Location DTO to Location entity.
     * @param Location DTO
     * @return Location entity
     */
    public Location locationDtoToEntity(LocationDto locationDto) {
        Mapper mapper = new DozerBeanMapper();
        Location location =
                mapper.map(locationDto, Location.class);
        return location;
    }
    
     /**
     * Convert Location entity to Location DTO.
     * @param Location entity
     * @return Location DTO
     */
    public LocationDto locationEntityToDto(Location location) {
        Mapper mapper = new DozerBeanMapper();
        LocationDto locationDto =
                mapper.map(location, LocationDto.class);
        return locationDto;
    }
    
    public List<LocationDto> locationEntityToDtoList(List<Location> locationList) {
        List<LocationDto> locationDaoList = new ArrayList<>();
        for(Location location : locationList)
		locationDaoList.add(this.locationEntityToDto(location));
        return locationDaoList;
    }
    
    public List<Location> locationDtoToEntityList(List<LocationDto> locationDtoList) {
        List<Location> locationList = new ArrayList<>();
        for (LocationDto locationDto: locationDtoList) {
            locationList.add(this.locationDtoToEntity(locationDto));
        }
        return locationList;
    }
}
