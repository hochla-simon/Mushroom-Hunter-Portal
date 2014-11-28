/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.dto.MushroomDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    void delete(LocationDto locationDto);

    /**
     * Finds a location by ID.
     *
     * @param id The ID of the searched location.
     * @return The found location.
     */
    @Transactional
    LocationDto find(long id);

    /**
     * Finds all locations in the database.
     *
     * @return The list of all locations.
     */
    @Transactional
    List<LocationDto> findAll();

    /**
     * Finds all locations where the given mushroom occurs.
     *
     * @param mushroom The mushroom according to which the search is performed.
     * @return The list of all locations with the occurence of given mushroom.
     */
    @Transactional
    List<LocationDto> findByMushroom(MushroomDto mushroomDto);

    /**
     * Finds all the locations near the given city.
     *
     * @param nearCity String with near city name.
     * @return The list of all locations near the given city.
     */
    @Transactional
    List<LocationDto> findByNearCity(String nearCity);

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
    List<LocationDto> findByOccurence(boolean ascending);

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
    List<LocationDto> findByOccurenceWithSumOfMushrooms(boolean ascending);

    /**
     * Saves location into database.
     *
     * @param location The location to be saved.
     * @return ID of the saved location.
     */
    @Transactional
    long save(LocationDto locationDto);

    /**
     * Updates given location in database.
     *
     * @param location The location to be updated.
     * @return Updated location.
     */
    @Transactional
    LocationDto update(LocationDto locationDto);
    
}
