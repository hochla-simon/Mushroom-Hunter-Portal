
package cz.fi.muni.pa165.mushroomhunter.entity;

import java.io.Serializable;
import java.util.Date;
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
 *
 * @author Radim Cejka
 */

@Entity
public class Mushroom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Enumerated(EnumType.ORDINAL)
    private Type type;
    
    @Temporal(TemporalType.DATE)
    private Date startOfOccurence;
    
    @Temporal(TemporalType.DATE)
    private Date endOfOccurence;


       /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
   /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }
    
       /**
     * @return the startOfOccurence
     */
    public Date getStartOfOccurence() {
        return startOfOccurence;
    }

    /**
     * @param startOfOccurence the startOfOccurence to set
     */
    public void setStartOfOccurence(Date startOfOccurence) {
        this.startOfOccurence = startOfOccurence;
    }

    /**
     * @return the endOfOccurence
     */
    public Date getEndOfOccurence() {
        return endOfOccurence;
    }

    /**
     * @param endOfOccurence the endOfOccurence to set
     */
    public void setEndOfOccurence(Date endOfOccurence) {
        this.endOfOccurence = endOfOccurence;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mushroom)) {
            return false;
        }
        Mushroom other = (Mushroom) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.fi.muni.pa165.mushroomhunter.entity.Mushroom[ id=" + getId() + " ]";
    }
}
