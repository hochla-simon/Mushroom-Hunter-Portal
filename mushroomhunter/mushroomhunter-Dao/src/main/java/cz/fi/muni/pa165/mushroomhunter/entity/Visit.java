package cz.fi.muni.pa165.mushroomhunter.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Visit Entity.
 * 
 * @author Roman Smékal
 */
@Entity
public class Visit implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    /**
     * The ID of the visit.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * The hunter who made the visit.
     */
    @ManyToOne
    @JoinColumn(name = "HUNTER_ID")
    private Hunter hunter;
    
    /**
     * The date when the visit was made.
     */
    @Temporal(TemporalType.DATE)
    private Date date;
    
    /**
     * The location where the visit was made.
     */
    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;
    
    /**
     * The HashMap containing the ID's of mushrooms and their number of occurences
     * at the visit.
     */
    @ElementCollection
    @MapKeyColumn(name="mushroom_id")
    @Column(name="quantity")
    @CollectionTable(name="occurence", joinColumns=@JoinColumn(name="visit_id"))
    private Map<Long,Integer> foundMushrooms = new HashMap<Long,Integer>();
    
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
     * @return the hunter.
     */
    public Hunter getHunter() {
        return hunter;
    }

    /**
     * @param hunter the hunter to be set.
     */
    public void setHunter(Hunter hunter) {
        this.hunter = hunter;
    }

    /**
     * @return the date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to be set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to be set.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the foundMushrooms.
     */
    public Map<Long,Integer> getFoundMushrooms() {
        return foundMushrooms;
    }

    /**
     * @param foundMushrooms the foundMushrooms to be set.
     */
    public void setFoundMushrooms(Map<Long,Integer> foundMushrooms) {
        this.foundMushrooms = foundMushrooms;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.hunter);
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + Objects.hashCode(this.location);
        hash = 79 * hash + Objects.hashCode(this.foundMushrooms);
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
        final Visit other = (Visit) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.hunter, other.hunter)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.foundMushrooms, other.foundMushrooms)) {
            return false;
        }
        return true;
    }
    
    /**
     * @return the String describing the Visit.
     */
    @Override
    public String toString() {
            return "Visit [id=" + id + ", hunter=" + hunter.getNick()
                            + ", location=" + location.getName()
                            + ", date: " + date.toString() + "]";
    }
}
