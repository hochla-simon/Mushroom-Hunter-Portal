/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.converter;

import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public Map<LocationDto, Integer> locationEntityToDtoMap(Map<Location, Integer> locationEntityMap) {
        Map<LocationDto, Integer> locationDaoMap = new HashMap<LocationDto, Integer>();
        for(Location location : locationEntityMap.keySet())
		locationDaoMap.put(this.locationEntityToDto(location),locationEntityMap.get(location));
        return locationDaoMap;
    }
    
    public List<LocationDto> locationEntityMapToDto(Map<Location, Integer> locationEntityMap) {
        List<LocationDto> locationDtoList = new ArrayList<>();
        for(Location location : locationEntityMap.keySet()){
                LocationDto locationDto = this.locationEntityToDto(location);
                locationDto.setMushroomOccurence(locationEntityMap.get(location));
                locationDtoList.add(locationDto);
        }
        return locationDtoList;
    }
    
    public Map<Location, Integer>locationDtoToEntityMap(Map<LocationDto, Integer> locationDaoMap) {
        Map<Location, Integer> locationEntityMap = new HashMap<Location, Integer>();
        for (LocationDto locationDto: locationDaoMap.keySet()) {
            locationEntityMap.put(this.locationDtoToEntity(locationDto), locationEntityMap.get(locationDto));
        }
        return locationEntityMap;
    }
    
    
}
