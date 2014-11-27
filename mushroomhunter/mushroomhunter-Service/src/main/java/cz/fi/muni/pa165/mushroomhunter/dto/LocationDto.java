/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.dto;

import java.util.Objects;

/**
 *
 * @author Lukáš Valach
 */
public class LocationDto {
      /**
     * The ID of the Location.
     */
    private Long id;

    /**
     * The name of the location.
     */
    private String name;

    /**
     * The description of the location.
     */
    private String description;
    
    /**
     * The city near the location.
     */
    private String nearCity;
    
    /**
     * Sum of mushrooms which were found in this location.
     */
    private Integer mushroomOccurence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNearCity() {
        return nearCity;
    }

    public void setNearCity(String nearCity) {
        this.nearCity = nearCity;
    }

    public Integer getMushroomOccurence() {
        return mushroomOccurence;
    }

    public void setMushroomOccurence(Integer mushroomOccurence) {
        this.mushroomOccurence = mushroomOccurence;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.nearCity);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LocationDto other = (LocationDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.nearCity, other.nearCity)) {
            return false;
        }
        return true;
    }
}
