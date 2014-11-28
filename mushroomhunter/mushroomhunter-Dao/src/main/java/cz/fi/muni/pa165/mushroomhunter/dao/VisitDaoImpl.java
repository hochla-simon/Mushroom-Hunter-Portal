package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Visit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * The implementation of a data object for the entity Visit. 
 * 
 * @author Radim Cejka
 */
@Stateless
@Repository
public class VisitDaoImpl implements VisitDao {

    /**
     * Entity manager.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Saves visit into database.
     * 
     * @param visit The visit to be saved.
     * @return ID of the saved visit.
     */
    @Override
    public long save(Visit visit) {
        em.persist(visit);
        return visit.getId();
    }

    /**
     * Updates given visit in database.
     * 
     * @param visit The location to be updated.
     * @return Updated visits.
     */
    @Override
    public Visit update(Visit visit) {
        return em.merge(visit);
    }

     /**
     * Deletes given visit from the database.
     * 
     * @param visit The visit to be deleted.
     */
    @Override
    public void delete(Visit visit) {
	if (!em.contains(visit)){
            visit = em.merge(visit);
        }
        em.remove(visit);
    }
    
    /**
     * Finds a visit by ID.
     * 
     * @param id The ID of the searched visit
     * @return 
     */

    @Override
    public Visit find(long id) {
     final Query query = em.createQuery("from Visit where id = :id");
        query.setParameter("id", id);
        return (Visit) query.getSingleResult();
    }
/**
     * Finds visits added the given hunter.
     * 
     * @param hunter 
     * @return The list of visits added the given hunter.
     */
    @Override
    public List<Visit> findByHunter(Hunter hunter) {
       final Query query = em.createQuery("from Visit where hunter = :hunter");
        query.setParameter("hunter", hunter);
        return query.getResultList();
    }

    /**
     * Finds visits of the given location.
     * 
     * @param location .
     * @return The list of visits of the given location.
     */
    @Override
    public List<Visit> findByLocation(Location location) {
        final Query query = em.createQuery("from Visit where location = :location");
        query.setParameter("location", location);
        return query.getResultList();
    }

    /**
     * Finds all visits in database.
     * 
     * @return A list of the visits.
     */
    @Override
    public List<Visit> findAll() {
        final Query query = em.createQuery("from Visit");
        return query.getResultList();
    }
}