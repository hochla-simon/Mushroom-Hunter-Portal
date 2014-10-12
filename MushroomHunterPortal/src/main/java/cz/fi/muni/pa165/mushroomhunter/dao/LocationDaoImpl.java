package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * The implementation of a data object for the entity Location.
 * 
 * @author Roman Smékal
 */
@Stateless
public class LocationDaoImpl implements LocationDao {

    /**
     * Entity manager.
     */
    @PersistenceContext
    private EntityManager em;

    @Override
    public long save(Location location) {
        em.persist(location);
        return location.getId();
    }
    
    @Override
    public Location update(Location location) {
        return em.merge(location);
    }

    @Override
    public void delete(Location location) {
        em.remove(location);
    }

    @Override
    public Location find(long id) {
        final Query query = em.createQuery("FROM Location WHERE id = :id");
        query.setParameter("id", id);
        return (Location) query.getSingleResult();
    }

    @Override
    public List<Location> findByNearCity(String nearCity) {
        final Query query = em.createQuery("FROM location WHERE nearcity = :nearCity");
        query.setParameter("nearCity", nearCity);
        return query.getResultList();
    }

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

    @Override
    public List<Location> findAll() {
        final Query query = em.createQuery("FROM location");
        return query.getResultList();
    }    
}
