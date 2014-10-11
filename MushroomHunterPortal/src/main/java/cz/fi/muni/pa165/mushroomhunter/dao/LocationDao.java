package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Roman Smékal
 */
@Local
public interface LocationDao {
    
    public long save(Location location);
    
    public Location update(Location location);
    
    public void delete(Location location);
    
    public Location find(long id);
    
    public List<Location> findByNearCity(String nearCity);
    
    public List<Location> findByMushroom(Mushroom mushroom);
    
    public List<Location> findByOccurence(boolean ascending);
    
    public List<Location> findAll();
}
