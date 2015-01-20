package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import java.util.List;

/**
 * The interface of a data object for the entity Hunter.
 * 
 * @author Lukáš Valach
 */
public interface HunterDao {
    
    /**
    * Saves hunter into database.
    * 
    * @param hunter The hunter to be saved.
    * @return ID of the saved hunter.
    */
    public long save(Hunter hunter);
    
    /**
    * Updates given hunter in database.
    * 
    * @param hunter The location to be updated.
    * @return Updated hunter.
    */
    public Hunter update(Hunter hunter);
    
    /**
     * Deletes given hunter from the database.
     * 
     * @param hunter The hunter to be deleted.
     */
    public void delete(Hunter hunter);

    /**
     * Finds a hunter by ID.
     * 
     * @param id The ID of the searched hunter.
     * @return The found hunter.
     */
    public Hunter find(long id);

    /**
     * Finds a hunter by firstName.
     * 
     * @param firstName The firstName of the searched hunter.
     * @return The found hunter.
     */
    public List<Hunter> findByName(String firstName);

    /**
     * Finds a hunter by surname.
     * 
     * @param surname The surname of the searched hunter.
     * @return The found hunter.
     */
    public List<Hunter> findBySurname(String surname);
    
    /**
     * Finds a hunter by nick.
     * 
     * @param nick The nick of the searched hunter.
     * @return The found hunter
     */
    public Hunter findByNick(String nick);

    /**
     * Finds all hunters in the database.
     * 
     * @return The list of all hunters.
     */
    public List<Hunter> findAll();
}
