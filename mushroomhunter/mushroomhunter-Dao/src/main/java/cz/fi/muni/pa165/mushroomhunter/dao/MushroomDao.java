package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import cz.fi.muni.pa165.mushroomhunter.entity.Type;
import java.util.Date;
import java.util.List;

/**
 * The interface of a data object for the entity Mushroom.
 * 
 * @author Simon Hochla
 */
public interface MushroomDao {
    
    /**
    * Saves mushroom into database.
    * 
    * @param mushroom The location to be saved.
    * @return ID of the saved mushroom.
    */
    public long save(Mushroom mushroom);
    
    /**
    * Updates given mushroom in database.
    * 
    * @param mushroom The mushroom to be updated.
    * @return Updated mushroom.
    */
    public Mushroom update(Mushroom mushroom);
    
    /**
     * Deletes given mushroom from the database.
     * 
     * @param mushroom The mushroom to be deleted.
     */
    public void delete(Mushroom mushroom);
    
    /**
     * Finds a mushroom by ID.
     * 
     * @param id The ID of the searched mushroom.
     * @return The found mushroom.
     */
    public Mushroom find(long id);
    
    /**
     * Finds all mushrooms whose name contains the given String.
     * 
     * @param name The string which should appear in the mushroom's name
     * @return The list of all mushrooms with the given name.
     */
    public List<Mushroom> findByName(String name);
    
    /**
     * Finds all the mushrooms in the given location.
     * 
     * @param loc Location according to which the search is performed.
     * @return The list of all mushrooms in the given location.
     */
    public List<Mushroom> findByLocation(Location loc);
    
    /**
     * Finds all locations where the given mushroom occurs.
     * 
     * @param type The enum of type according to which the search is performed.
     * @return The list of all mushrooms of the given type.
     */
    public List<Mushroom> findByType(Type type);
    
    /**
     * Finds all mushrooms by the time of an occurence.
     * 
     * @param startOfOccurence The start of the occurence.
     * @param endOfOccurence The end of the occurence
     * @return The list of all mushrooms.
     */
    public List<Mushroom> findByOccurenceDate(Date startOfOccurence, Date endOfOccurence);
    
    /**
     * Finds all mushrooms in the database.
     * 
     * @return The list of all mushrooms.
     */
    public List<Mushroom> findAll();
}
