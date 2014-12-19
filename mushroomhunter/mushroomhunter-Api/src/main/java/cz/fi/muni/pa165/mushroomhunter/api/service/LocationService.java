/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.api.service;

import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.MushroomDto;
import java.util.List;


/**
 *
 * @author Simon
 */
public interface LocationService {

    /**
     * Deletes given location from the database.
     *
     * @param location The location to be deleted.
     */
    void delete(LocationDto locationDto);

    /**
     * Finds a location by ID.
     *
     * @param id The ID of the searched location.
     * @return The found location.
     */
    LocationDto find(long id);

    /**
     * Finds all locations in the database.
     *
     * @return The list of all locations.
     */
    List<LocationDto> findAll();

    /**
     * This method is used to get a list of locations ordered by the quantity of
     * mushroom occurence.
     *
     * @param ascending True, if the list should be ordered ascending, false
     * otherwise.
     * @return The list of all locations sorted in ascending/descending order by
     * the quantity of mushroom occurence.
     */
    List<LocationDto> findByOccurenceWithSumOfMushrooms(boolean ascending);

    /**
     * Saves location into database.
     *
     * @param location The location to be saved.
     * @return ID of the saved location.
     */
    long save(LocationDto locationDto);

    /**
     * Updates given location in database.
     *
     * @param location The location to be updated.
     * @return Updated location.
     */
    LocationDto update(LocationDto locationDto);
    
}
