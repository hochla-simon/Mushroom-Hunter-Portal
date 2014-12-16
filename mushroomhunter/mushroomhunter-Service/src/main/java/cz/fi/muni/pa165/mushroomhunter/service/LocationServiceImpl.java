/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.api.service.LocationService;
import cz.fi.muni.pa165.mushroomhunter.converter.LocationConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.MushroomConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.LocationDao;
import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.MushroomDto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Simon
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationDao locationDao;
    @Autowired
    LocationConverter locationConverter;
    @Autowired
    MushroomConverter mushroomConverter;

    public LocationServiceImpl() {
    }

    /**
     * Saves location into database.
     *
     * @param location The location to be saved.
     * @return ID of the saved location.
     */
    @Transactional
    @Override
    public long save(LocationDto locationDto) {
        long id = locationDao.save(locationConverter.locationDtoToEntity(locationDto));
        locationDto.setId(id);
        return id;
    }

    /**
     * Updates given location in database.
     *
     * @param location The location to be updated.
     * @return Updated location.
     */
    @Transactional
    @Override
    public LocationDto update(LocationDto locationDto) {
        return locationConverter.locationEntityToDto(locationDao.update(locationConverter.locationDtoToEntity(locationDto)));
    }

    /**
     * Deletes given location from the database.
     *
     * @param location The location to be deleted.
     */
    @Transactional
    @Override
    public void delete(LocationDto locationDto) {
            locationDao.delete(locationConverter.locationDtoToEntity(locationDto));
    }

    /**
     * Finds a location by ID.
     *
     * @param id The ID of the searched location.
     * @return The found location.
     */
    @Transactional
    @Override
    public LocationDto find(long id) {
            return locationConverter.locationEntityToDto(locationDao.find(id));
    }

    /**
     * Finds all the locations near the given city.
     *
     * @param nearCity String with near city name.
     * @return The list of all locations near the given city.
     */
    @Transactional
    @Override
    public List<LocationDto> findByNearCity(String nearCity) {
            return locationConverter.locationEntityToDtoList(locationDao.findByNearCity(nearCity));
    }

    /**
     * Finds all locations where the given mushroom occurs.
     *
     * @param mushroom The mushroom according to which the search is performed.
     * @return The list of all locations with the occurence of given mushroom.
     */
    @Transactional
    @Override
    public List<LocationDto> findByMushroom(MushroomDto mushroomDto) {
            return locationConverter.locationEntityToDtoList(locationDao.findByMushroom(mushroomConverter.mushroomDtoToEntity(mushroomDto)));
    }

    /**
     * This method is used to get a list of locations ordered by the quantity of
     * mushroom occurence.
     *
     * @param ascending True, if the list should be ordered ascending, false
     * otherwise.
     * @return The list of all locations sorted in ascending/descending order by
     * the quantity of mushroom occurence.
     */
    @Transactional
    @Override
    public List<LocationDto> findByOccurence(boolean ascending) {
            return locationConverter.locationEntityToDtoList(locationDao.findByOccurence(ascending));
    }

    /**
     * This method is used to get a list of locations ordered by the quantity of
     * mushroom occurence.
     *
     * @param ascending True, if the list should be ordered ascending, false
     * otherwise.
     * @return The list of all locations sorted in ascending/descending order by
     * the quantity of mushroom occurence.
     */
    @Transactional
    @Override
    public List<LocationDto> findByOccurenceWithSumOfMushrooms(boolean ascending) {
        return locationConverter.locationEntityMapToDto(locationDao.findByOccurenceWithSumOfMushrooms(ascending));
    }

    /**
     * Finds all locations in the database.
     *
     * @return The list of all locations.
     */
    @Transactional
    @Override
    public List<LocationDto> findAll() {
            return locationConverter.locationEntityToDtoList(locationDao.findAll());
    }
}
