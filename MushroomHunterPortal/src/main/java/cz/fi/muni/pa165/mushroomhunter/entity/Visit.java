package cz.fi.muni.pa165.mushroomhunter.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Roman Smékal
 * @date 10/8/2014
 */
@Entity
public class Visit {
    
    @Id
    @GeneratedValue
    private long id = 0;
    
    @OneToOne
    @JoinColumn(name = "HUNTER_ID")
    private Hunter hunter;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @OneToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;
    
    @ElementCollection
    @MapKeyColumn(name="mushroom_id")
    @Column(name="quantity")
    @CollectionTable(name="occurence", joinColumns=@JoinColumn(name="visit_id"))
    private Map<Long,Integer> foundMushrooms = new HashMap<Long,Integer>();
    
    /**
     * @param id the id to set
     */
    public long getId() {
        return id;
    }
    
    /**
     * @return the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the hunter
     */
    public Hunter getHunter() {
        return hunter;
    }

    /**
     * @param hunter the hunter to set
     */
    public void setHunter(Hunter hunter) {
        this.hunter = hunter;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the foundMushrooms
     */
    public Map<Long,Integer> getFoundMushrooms() {
        return foundMushrooms;
    }

    /**
     * @param foundMushrooms the foundMushrooms to set
     */
    public void setFoundMushrooms(Map<Long,Integer> foundMushrooms) {
        this.foundMushrooms = foundMushrooms;
    }
    
    /**
     * 
     * @return the result hashCode 
     */
    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (id ^ (id >>> 32));
            return result;
    }
    
    /**
     * 
     * @return the String describing the Visit 
     */
    @Override
    public String toString() {
            return "Visit [id=" + id + ", hunter=" + hunter.getNick()
                            + ", location=" + location.getName()
                            + ", date: " + date.toString() + "]";
    }
}
