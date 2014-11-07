package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;

/**
 * The implementation of a data object for the entity Location.
 * 
 * @author Roman Smékal
 */
@Stateless
@Component
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
        final Query query = em.createQuery("FROM Location WHERE nearcity = :nearCity");
        query.setParameter("nearCity", nearCity);
        return query.getResultList();
    }

    @Override
    public List<Location> findByMushroom(Mushroom mushroom) {
        final Query query = em.createQuery("SELECT DISTINCT loc FROM Location loc "
                + "WHERE loc.id = (SELECT v.location FROM Visit v WHERE :id  = "
                + "(SELECT key(map) FROM v.foundMushrooms map))");
        query.setParameter("id", mushroom.getId());
        return query.getResultList();
    }

    @Override
    public List<Location> findByOccurence(boolean ascending) {
        final Query query = em.createQuery("SELECT Location.id, Location.name, "
                + "Location.nearCity, Location.place FROM Location JOIN (COUNT(occurence.quantity) AS occ_quantity FROM ((Location "
                + "JOIN visit ON Location.id=visit.Location_id) "
                + "JOIN occurence ON visit.id=occurence.visit_id) "
                + "GROUP BY Location.id :asc)");
        if(ascending)
            query.setParameter("asc", "ASC");
        else 
            query.setParameter("asc", "DESC");
        
        return query.getResultList();
    }

    @Override
    public List<Location> findAll() {
        final Query query = em.createQuery("FROM Location");
        return query.getResultList();
    }    
}
