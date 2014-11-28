package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Visit;
import java.util.List;
import javax.ejb.Local;
import org.springframework.stereotype.Repository;

/**
 * The interface of a data object for the entity Visit.
 * 
 * @author Radim Cejka 
 */
@Local
@Repository
public interface VisitDao {
    
    /**
    * Saves visit into database.
    * 
    * @param visit The visit to be saved.
    * @return ID of the saved visit.
    */
    public long save(Visit visit);
    
    /**
    * Updates given visit in database.
    * 
    * @param visit The visit to be updated.
    * @return Updated visit.
    */
    public Visit update(Visit visit);
    
    /**
     * Deletes given visit from the database.
     * 
     * @param visit The visit to be deleted.
     */
    public void delete(Visit visit);
    
    /**
     * Finds a location by ID.
     * 
     * @param id The ID of the searched visit.
     * @return The found location.
     */
    public Visit find(long id);
    
    /**
     * Finds all the visits of the given hunter.
     * 
     * @param hunter String with the near city name.
     * @return The list of all locations near the given city.
     */
    public List<Visit> findByHunter(Hunter hunter);
    
    /**
     * Finds all the visits in the given location.
     * 
     * @param location String with the name of location.
     * @return The list of all visits in the given location.
     */
    public List<Visit> findByLocation(Location location);
    
    /**
     * Finds all locations in the database.
     * 
     * @return The list of all locations.
     */
    public List<Visit> findAll();
}

