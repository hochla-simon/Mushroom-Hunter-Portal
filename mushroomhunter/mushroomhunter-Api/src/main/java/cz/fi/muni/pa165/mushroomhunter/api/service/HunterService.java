/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.api.service;

import cz.fi.muni.pa165.mushroomhunter.api.dto.HunterDto;
import java.util.List;

/**
 *
 * @author Radim Čejka
 */
public interface HunterService {

    public long save(HunterDto hunterDto);

    /**
     * Updates given hunter in database.
     *
     * @param hunter The location to be updated.
     * @return Updated hunter.
     */
    public HunterDto update(HunterDto hunterDto);

    /**
     * Deletes given hunter from the database.
     *
     * @param hunter The hunter to be deleted.
     */
    public void delete(HunterDto hunterDto);

    /**
     * Finds a hunter by ID.
     *
     * @param id The ID of the searched hunter.
     * @return The found hunter.
     */
    public HunterDto find(long id);

    /**
     * Finds all hunters in the database.
     *
     * @return The list of all hunters.
     */
    public List<HunterDto> findAll();

    /**
     * Finds a hunter by login name.
     *
     * @param nick The login name.
     * @return The found hunter.
     */
    public HunterDto findByNick(String nick);
}
