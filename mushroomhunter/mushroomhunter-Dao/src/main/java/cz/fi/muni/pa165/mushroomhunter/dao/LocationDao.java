package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.springframework.stereotype.Repository;

/**
 * The interface of a data object for the entity Location.
 *
 * @author Roman Smékal
 */
@Local
@Repository
public interface LocationDao {

    /**
     * Saves location into database.
     *
     * @param location The location to be saved.
     * @return ID of the saved location.
     */
    public long save(Location location);

    /**
     * Updates given location in database.
     *
     * @param location The location to be updated.
     * @return Updated location.
     */
    public Location update(Location location);

    /**
     * Deletes given location from the database.
     *
     * @param location The location to be deleted.
     */
    public void delete(Location location);

    /**
     * Finds a location by ID.
     *
     * @param id The ID of the searched location.
     * @return The found location.
     */
    public Location find(long id);

    /**
     * Finds all the locations near the given city.
     *
     * @param nearCity String with near city name.
     * @return The list of all locations near the given city.
     */
    public List<Location> findByNearCity(String nearCity);

    /**
     * Finds all locations where the given mushroom occurs.
     *
     * @param mushroom The mushroom according to which the search is performed.
     * @return The list of all locations with the occurence of given mushroom.
     */
    public List<Location> findByMushroom(Mushroom mushroom);

    /**
     * This method is used to get a list of locations ordered by the quantity of
     * mushroom occurence.
     *
     * @param ascending True, if the list should be ordered ascending, false
     * otherwise.
     * @return The list of all locations sorted in ascending/descending order by
     * the quantity of mushroom occurence.
     */
    public List<Location> findByOccurence(boolean ascending);

    /**
     * This method is used to get a map of locations ordered by the quantity of
     * mushroom occurence.
     *
     * @param ascending True, if the result map should be ordered ascending, false
     * otherwise.
     * @return The map of all locations and sums of mushroom occurence sorted in ascending/descending order by
     * the quantity of mushroom occurence.
     */
    public Map<Location, Integer> findByOccurenceWithSumOfMushrooms(boolean ascending);

    /**
     * Finds all locations in the database.
     *
     * @return The list of all locations.
     */
    public List<Location> findAll();
}
