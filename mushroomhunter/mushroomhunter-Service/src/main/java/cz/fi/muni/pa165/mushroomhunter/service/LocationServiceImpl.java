/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.converter.LocationConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.MushroomConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.LocationDao;
import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.dto.MushroomDto;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Simon
 */
@Component
public class LocationServiceImpl {

    @Autowired
    LocationDao locationDao;
    @Autowired
    LocationConverter locationConverter;
    @Autowired
    MushroomConverter mushroomConverter;
    @PersistenceContext
    private EntityManager em;

    public LocationServiceImpl() {
    }

    /**
     * Saves location into database.
     *
     * @param location The location to be saved.
     * @return ID of the saved location.
     */
    @Transactional
    public long save(LocationDto locationDto) {
        try {
            long id = locationDao.save(locationConverter.locationDtoToEntity(locationDto));
            locationDto.setId(id);
            return id;
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error saving data.", e);
        }
    }

    /**
     * Updates given location in database.
     *
     * @param location The location to be updated.
     * @return Updated location.
     */
    @Transactional
    public LocationDto update(LocationDto locationDto) {
        try {
            return locationConverter.locationEntityToDto(locationDao.update(locationConverter.locationDtoToEntity(locationDto)));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error updating data.", e);
        }
    }

    /**
     * Deletes given location from the database.
     *
     * @param location The location to be deleted.
     */
    @Transactional
    public void delete(LocationDto locationDto) {
        try {
            locationDao.delete(locationConverter.locationDtoToEntity(locationDto));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error deleting data.", e);
        }
    }

    /**
     * Finds a location by ID.
     *
     * @param id The ID of the searched location.
     * @return The found location.
     */
    @Transactional
    public LocationDto find(long id) {
        try {
            return locationConverter.locationEntityToDto(locationDao.find(id));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error retrieving data.", e);
        }
    }

    /**
     * Finds all the locations near the given city.
     *
     * @param nearCity String with near city name.
     * @return The list of all locations near the given city.
     */
    @Transactional
    public List<LocationDto> findByNearCity(String nearCity) {
        try {
            return locationConverter.locationEntityToDtoList(locationDao.findByNearCity(nearCity));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error retrieving data.", e);
        }
    }

    /**
     * Finds all locations where the given mushroom occurs.
     *
     * @param mushroom The mushroom according to which the search is performed.
     * @return The list of all locations with the occurence of given mushroom.
     */
    @Transactional
    public List<LocationDto> findByMushroom(MushroomDto mushroomDto) {
        try {
            return locationConverter.locationEntityToDtoList(locationDao.findByMushroom(mushroomConverter.mushroomDtoToEntity(mushroomDto)));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error retrieving data.", e);
        }
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
    public List<LocationDto> findByOccurence(boolean ascending) {
        try {
            return locationConverter.locationEntityToDtoList(locationDao.findByOccurence(ascending));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error retrieving data.", e);
        }
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
    public Map<LocationDto, Integer> findByOccurenceWithSumOfMushrooms(boolean ascending) {
        return locationConverter.locationEntityToDtoMap(locationDao.findByOccurenceWithSumOfMushrooms(ascending));
    }

    /**
     * Finds all locations in the database.
     *
     * @return The list of all locations.
     */
    @Transactional
    public List<LocationDto> findAll() {
        try {
            return locationConverter.locationEntityToDtoList(locationDao.findAll());
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error retrieving data.", e);
        }
    }
}
