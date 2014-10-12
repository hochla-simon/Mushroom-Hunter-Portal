package cz.fi.muni.pa165.mushroomhunter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Location entity.
 *
 * @author Lukáš Valach
 */
@Entity
public class Location {

    @Id
    @GeneratedValue
    private long id = 0;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private String nearCity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /**
     * Set name of location. Name must be unique.
     *
     * @param name name of location
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 57;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Location other = (Location) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * @return the nearCity
     */
    public String getNearCity() {
        return nearCity;
    }

    /**
     * @param nearCity the nearCity to set
     */
    public void setNearCity(String nearCity) {
        this.nearCity = nearCity;
    }

}
