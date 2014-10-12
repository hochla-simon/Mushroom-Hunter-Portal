package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Roman Smékal
 */
@Stateless
public class LocationDaoImpl implements LocationDao {

    /**
     * Entity manager
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Saves location into database.
     * 
     * @param location The location to be saved.
     * @return ID of the saved location.
     */
    @Override
    public long save(Location location) {
        em.persist(location);
        return location.getId();
    }
    
    /**
     * Updates given location in database.
     * 
     * @param location The location to be updated.
     * @return Updated location.
     */
    @Override
    public Location update(Location location) {
        return em.merge(location);
    }

    /**
     * Deletes given location from the database.
     * 
     * @param location The location to be deleted.
     */
    @Override
    public void delete(Location location) {
        em.remove(location);
    }

    /**
     * Finds a location by ID.
     * 
     * @param id The ID of the searched location
     * @return 
     */
    @Override
    public Location find(long id) {
        final Query query = em.createQuery("FROM location WHERE id = :id");
        query.setParameter("id", id);
        return (Location) query.getSingleResult();
    }

    /**
     * Finds locations which are near the given city.
     * 
     * @param nearCity String with near city name.
     * @return The list of locations near to given city.
     */
    @Override
    public List<Location> findByNearCity(String nearCity) {
        final Query query = em.createQuery("FROM location WHERE nearcity = :nearCity");
        query.setParameter("nearCity", nearCity);
        return query.getResultList();
    }

    /**
     * Finds locations where given mushroom occurs.
     * 
     * @param mushroom The mushroom according to the search is performed.
     * @return The list of locations with the occurence of given mushroom.
     */
    @Override
    public List<Location> findByMushroom(Mushroom mushroom) {
        final Query query = em.createQuery("SELECT DISTINCT location.id, location.name, "
                + "location.nearCity, location.place FROM ((location"
                + "JOIN visit ON location.id = visit.location_id)"
                + "JOIN occurence ON visit.id = occurence.visit_id)"
                + "WHERE occurence.mushroom_id = :id");
        query.setParameter("id", mushroom.getId());
        return query.getResultList();
    }

    /**
     * This method is used to get a list of locations ordered by the quantity of mushroom occurence.
     * 
     * @param ascending True, if the list should be ordered ascending, false otherwise.
     * @return The list of locations sorted in ascending/descending order  by the quantity of mushroom occurence.
     */
    @Override
    public List<Location> findByOccurence(boolean ascending) {
        final Query query = em.createQuery("SELECT * FROM location JOIN (COUNT(occurence.quantity) AS occ_quantity FROM ((location"
                + "JOIN visit ON location.id=visit.location_id)"
                + "JOIN occurence ON visit.id=occurence.visit_id)"
                + "GROUP BY location.id :asc)");
        if(ascending)
            query.setParameter("asc", "ASC");
        else 
            query.setParameter("asc", "DESC");
        
        return query.getResultList();
    }

    /**
     * Finds all locations in database.
     * 
     * @return A list of the locations.
     */
    @Override
    public List<Location> findAll() {
        final Query query = em.createQuery("FROM location");
        return query.getResultList();
    }    
}
