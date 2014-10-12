package cz.fi.muni.pa165.mushroomhunter.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Hunter Entity.
 * 
 * @author Šimon Hochla
 */
@Entity
public class Hunter implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /**
     * The ID of the Hunter
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * The hunter's nick.
     */
    @Column(nullable = false, unique = true)
    private String nick;

    /**
     * The hunter's first name.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * The hunter's surname.
     */
    @Column(nullable = false)
    private String surname;

    /**
     * The description of the hunter.
     */
    @Column(nullable = true)
    private String description;
    
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
     * @return the id.
     */
    public String getNick() {
        return nick;
    }
    
    /**
     * @param nick the nick to be set.
     */
    public void setNick(String nick) {
        this.nick = nick;
    }
    
    /**
     * @return the id.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * @param firstName the firstName to be set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * @return the surname.
     */
    public String getSurname() {
        return surname;
    }
    
    /**
     * @param surname the surname to be set.
     */
    public void setSurname(String surname) {
        this.surname = surname;
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
    
    /** 
     * @return the result hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    
    /**
     * @param obj The object to be compared with current instance of the hunter.
     * @return true, if objects are equals, false otherwise.
     */
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
        Hunter other = (Hunter) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }
}
