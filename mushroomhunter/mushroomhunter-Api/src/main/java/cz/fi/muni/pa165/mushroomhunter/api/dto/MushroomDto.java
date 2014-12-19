package cz.fi.muni.pa165.mushroomhunter.api.dto;

import cz.fi.muni.pa165.mushroomhunter.api.Type;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Roman Smékal
 */
public class MushroomDto {
    /**
     * The ID of the Location.
     */
    private Long id;

    /**
     * The name of the mushroom.
     */
    @NotBlank
    @Length(min = 1, max = 40)
    private String name;

    /**
     * The type enum of the mushroom.
     */
    @NotNull
    private Type type;
    
    /**
     * The beginning of the occurence.
     */
    private Date startOfOccurence;
    
    /**
     * The end of the occurence.
     */
    private Date endOfOccurence;

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

    public Type getType() {
        return type;
    }

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
        final MushroomDto other = (MushroomDto) obj;
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
       
        return true;
    }
}
