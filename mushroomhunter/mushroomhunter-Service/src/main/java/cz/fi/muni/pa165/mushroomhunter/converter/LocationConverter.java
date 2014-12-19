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
     *
     * @param locationDto LocationDto
     * @return location Location
     */
    public Location locationDtoToEntity(LocationDto locationDto) {
        Mapper mapper = new DozerBeanMapper();
        Location location
                = mapper.map(locationDto, Location.class);
        return location;
    }

    /**
     * Convert Location entity to Location DTO.
     *
     * @param location Location
     * @return LocationDto locationDto
     */
    public LocationDto locationEntityToDto(Location location) {
        Mapper mapper = new DozerBeanMapper();
        LocationDto locationDto
                = mapper.map(location, LocationDto.class);
        return locationDto;
    }

    /**
     * Convert list of locations to list of locationDtos
     *
     * @param locationList List<Location>
     * @return locationDtoList List<LocationDto>
     */
    public List<LocationDto> locationEntityToDtoList(List<Location> locationList) {
        List<LocationDto> locationDtoList = new ArrayList<>();
        for (Location location : locationList) {
            locationDtoList.add(this.locationEntityToDto(location));
        }
        return locationDtoList;
    }

    /**
     * Convert list of locationDtos to list of locations
     *
     * @param locationDtoList List<LocationDto>
     * @return locationList List<Location>
     */
    public List<Location> locationDtoToEntityList(List<LocationDto> locationDtoList) {
        List<Location> locationList = new ArrayList<>();
        for (LocationDto locationDto : locationDtoList) {
            locationList.add(this.locationDtoToEntity(locationDto));
        }
        return locationList;
    }

    /**
     * Convert map of locations to map of locationDtos
     *
     * @param Map<Location, Integer> locationEntityMap
     * @return Map<LocationDto, Integer> locatoinDtoMap
     */
    public Map<LocationDto, Integer> locationEntityToDtoMap(Map<Location, Integer> locationEntityMap) {
        Map<LocationDto, Integer> locationDtoMap = new HashMap<LocationDto, Integer>();
        for (Location location : locationEntityMap.keySet()) {
            locationDtoMap.put(this.locationEntityToDto(location), locationEntityMap.get(location));
        }
        return locationDtoMap;
    }

    /**
     * Convert map of locations to list of LocationDtos
     *
     * @param locationEntityMap Map<Location, Integer>
     * @return locationEntityList List<LocationDto>
     */
    public List<LocationDto> locationEntityMapToDto(Map<Location, Integer> locationEntityMap) {
        List<LocationDto> locationDtoList = new ArrayList<>();
        for (Location location : locationEntityMap.keySet()) {
            LocationDto locationDto = this.locationEntityToDto(location);
            locationDto.setMushroomOccurence(locationEntityMap.get(location));
            locationDtoList.add(locationDto);
        }
        return locationDtoList;
    }

    /**
     * Convert map of locationDtos to map of locations
     *
     * @param locationDaoMap Map<LocationDto, Integer>
     * @return locatoinEntityMap Map<Location, Integer>
     */
    public Map<Location, Integer> locationDtoToEntityMap(Map<LocationDto, Integer> locationDaoMap) {
        Map<Location, Integer> locationEntityMap = new HashMap<Location, Integer>();
        for (LocationDto locationDto : locationDaoMap.keySet()) {
            locationEntityMap.put(this.locationDtoToEntity(locationDto), locationEntityMap.get(locationDto));
        }
        return locationEntityMap;
    }

}
