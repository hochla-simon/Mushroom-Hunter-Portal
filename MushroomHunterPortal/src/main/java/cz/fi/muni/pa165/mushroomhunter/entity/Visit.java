package cz.fi.muni.pa165.mushroomhunter.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
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
    @OneToOne
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
    @OneToOne
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
    
    /** 
     * @return the result hashCode.
     */
    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (id ^ (id >>> 32));
            return result;
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
