/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.dto.HunterDTO;
import java.util.List;

/**
 *
 * @author Radim Čejka
 */
public interface HunterService {
    /**
    * Saves hunter into database.
    * 
    * @param hunter The hunter to be saved.
    * @return ID of the saved hunter.
    */
    public long save(HunterDTO hunter);
    
    /**
    * Updates given hunter in database.
    * 
    * @param hunter The location to be updated.
    * @return Updated hunter.
    */
    public HunterDTO update(HunterDTO hunter);
    
    /**
     * Deletes given hunter from the database.
     * 
     * @param hunter The hunter to be deleted.
     */
    public void delete(HunterDTO hunter);

    /**
     * Finds a hunter by ID.
     * 
     * @param id The ID of the searched hunter.
     * @return The found hunter.
     */
    public HunterDTO find(long id);

    /**
     * Finds a hunter by firstName.
     * 
     * @param firstName The firstName of the searched hunter.
     * @return The found hunter.
     */
    public List<HunterDTO> findByName(String firstName);

    /**
     * Finds a hunter by surname.
     * 
     * @param surname The surname of the searched hunter.
     * @return The found hunter.
     */
    public List<HunterDTO> findBySurname(String surname);
    
    /**
     * Finds a hunter by nick.
     * 
     * @param nick The nick of the searched hunter.
     * @return The found hunter
     */
    public List<HunterDTO> findByNick(String nick);

    /**
     * Finds all hunters in the database.
     * 
     * @return The list of all hunters.
     */
    public List<HunterDTO> findAll();
}
