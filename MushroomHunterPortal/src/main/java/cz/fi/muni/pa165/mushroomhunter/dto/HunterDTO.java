/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.dto;

import java.util.Objects;

/**
 *
 * @author Radim Cejka
 */
public class HunterDTO {
    
    /**
     * The ID of the Hunter
     */
    private Long id;
    
    /**
     * The hunter's nick.
     */
    private String nick;

    /**
     * The hunter's first name.
     */
    private String firstName;

    /**
     * The hunter's surname.
     */
    private String surname;

    /**
     * The description of the hunter.
     */
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.nick);
        hash = 23 * hash + Objects.hashCode(this.firstName);
        hash = 23 * hash + Objects.hashCode(this.surname);
        hash = 23 * hash + Objects.hashCode(this.description);
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
        final HunterDTO other = (HunterDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nick, other.nick)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    
    
    
}
