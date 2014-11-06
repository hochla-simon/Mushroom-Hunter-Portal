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
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Simon
 */
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
        return locationDao.save(locationConverter.locationDtoToEntity(locationDto));
    }
    
    /**
    * Updates given location in database.
    * 
    * @param location The location to be updated.
    * @return Updated location.
    */
    @Transactional
    public LocationDto update(LocationDto locationDto) {
        return locationConverter.locationEntityToLocationDto(locationDao.update
                (locationConverter.locationDtoToEntity(locationDto)));
    }
    
    /**
     * Deletes given location from the database.
     * 
     * @param location The location to be deleted.
     */
    @Transactional
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
    public LocationDto find(long id) {
         return locationConverter.locationEntityToLocationDto(locationDao.find(id));
    }
    
    /**
     * Finds all the locations near the given city.
     * 
     * @param nearCity String with near city name.
     * @return The list of all locations near the given city.
     */
    @Transactional
    public List<Location> findByNearCity(String nearCity) {
        return locationDao.findByNearCity(nearCity);
    }
    
    /**
     * Finds all locations where the given mushroom occurs.
     * 
     * @param mushroom The mushroom according to which the search is performed.
     * @return The list of all locations with the occurence of given mushroom.
     */
    @Transactional
    public List<Location> findByMushroom(MushroomDto mushroomDto) {
         return locationDao.findByMushroom(mushroomConverter.mushroomDtoToEntity(mushroomDto));
    }
    
    /**
     * This method is used to get a list of locations ordered by the quantity of mushroom occurence.
     * 
     * @param ascending True, if the list should be ordered ascending, false otherwise.
     * @return The list of all locations sorted in ascending/descending order by the quantity of mushroom occurence.
     */
    @Transactional
    public List<Location> findByOccurence(boolean ascending) {
         return locationDao.findByOccurence(ascending);
    }
    
    /**
     * Finds all locations in the database.
     * 
     * @return The list of all locations.
     */
    @Transactional
    public List<Location> findAll() {
        return locationDao.findAll();
    }
}
