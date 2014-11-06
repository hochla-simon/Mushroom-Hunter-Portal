package cz.fi.muni.pa165.mushroomhunter.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Location entity.
 *
 * @author LukĂˇĹˇ Valach
 */
@Entity
public class Location {
    
    /**
     * The ID of the Location.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The name of the location.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * The description of the location.
     */
    @Column(nullable = false)
    private String description;
    
    /**
     * The city near the location.
     */
    @Column(nullable = false)
    private String nearCity;

    /**
     * @return the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of location. Name must be unique.
     *
     * @param name The name of the location.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.nearCity);
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
        final Location other = (Location) obj;
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

    /**
     * @return the nearCity.
     */
    public String getNearCity() {
        return nearCity;
    }

    /**
     * @param nearCity the nearCity to be set.
     */
    public void setNearCity(String nearCity) {
        this.nearCity = nearCity;
    }

}
