
package cz.fi.muni.pa165.mushroomhunter.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Mushroom Entity
 * 
 * @author Radim Cejka
 */

@Entity
public class Mushroom implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * The ID of the mushroom.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * The name of the mushroom.
     */
    @Column(nullable = false, unique = true)
    private String name;
    
    /**
     * The type enum of the mushroom.
     */
    @Enumerated(EnumType.ORDINAL)
    private Type type;
    
    /**
     * The beginning of the occurence.
     */
    @Temporal(TemporalType.DATE)
    private Date startOfOccurence;
    
    /**
     * The end of the occurence.
     */
    @Temporal(TemporalType.DATE)
    private Date endOfOccurence;


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
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }
    
   /**
     * @return the type.
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to be set.
     */
    public void setType(Type type) {
        this.type = type;
    }
    
       /**
     * @return the startOfOccurence.
     */
    public Date getStartOfOccurence() {
        return startOfOccurence;
    }

    /**
     * @param startOfOccurence the startOfOccurence to be set.
     */
    public void setStartOfOccurence(Date startOfOccurence) {
        this.startOfOccurence = startOfOccurence;
    }

    /**
     * @return the endOfOccurence.
     */
    public Date getEndOfOccurence() {
        return endOfOccurence;
    }

    /**
     * @param endOfOccurence the endOfOccurence to be set.
     */
    public void setEndOfOccurence(Date endOfOccurence) {
        this.endOfOccurence = endOfOccurence;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.startOfOccurence);
        hash = 37 * hash + Objects.hashCode(this.endOfOccurence);
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
        final Mushroom other = (Mushroom) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.startOfOccurence, other.startOfOccurence)) {
            return false;
        }
        if (!Objects.equals(this.endOfOccurence, other.endOfOccurence)) {
            return false;
        }
        return true;
    }
    
    /**
     * The string describing the mushroom.
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "cz.fi.muni.pa165.mushroomhunter.entity.Mushroom[ id=" + getId() + " ]";
    }
}
