/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.api.dto;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

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
    @NotBlank
    @Length(min = 1, max = 20)
    private String name;

    /**
     * The description of the location.
     */
    @NotNull
    @Length(max = 200)
    private String description;

    /**
     * The city near the location.
     */
    @Length(max = 20)
    private String nearCity;

    /**
     * Sum of mushrooms which were found in this location.
     */
    private Integer mushroomOccurence;

    /**
     * Owner is a hunter who created this location. The user has permission to
     * manipulate with his locations.
     */
    @Column(nullable = true)
    private Long ownerId;

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
    
        /**
     * @return the owner of location.
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * @param owner is a hunter who created this location. The user has
     * permission to manipulate with his locations.
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.nearCity);
        hash = 37 * hash + Objects.hashCode(this.ownerId);
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
        if (!Objects.equals(this.ownerId, other.ownerId)) {
            return false;
        }
        return true;
    }
}
