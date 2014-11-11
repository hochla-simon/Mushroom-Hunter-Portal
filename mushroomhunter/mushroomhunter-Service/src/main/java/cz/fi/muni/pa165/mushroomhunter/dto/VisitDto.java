/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lukáš Valach
 */
public class VisitDto {
    
    /**
     * The ID of the visit.
     */
    private Long id;
    
    /**
     * The hunter who made the visit.
     */
    private HunterDto hunter;
    
    /**
     * The date when the visit was made.
     */
    private Date date;
    
    /**
     * The location where the visit was made.
     */
    private LocationDto location;
    
    /**
     * The HashMap containing the ID's of mushrooms and their number of occurences
     * at the visit.
     */
    private Map<Long,Integer> foundMushrooms = new HashMap<Long,Integer>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HunterDto getHunter() {
        return hunter;
    }

    public void setHunter(HunterDto hunter) {
        this.hunter = hunter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public Map<Long, Integer> getFoundMushrooms() {
        return foundMushrooms;
    }

    public void setFoundMushrooms(Map<Long, Integer> foundMushrooms) {
        this.foundMushrooms = foundMushrooms;
    }
    
}


