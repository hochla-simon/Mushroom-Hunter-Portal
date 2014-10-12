package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Visit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Radim Cejka
 */
@Stateless
public class VisitDaoImpl implements VisitDao {

    /**
     * Entity manager
     */
    @PersistenceContext
    private EntityManager em;

    @Override
    public long save(Visit visit) {
        em.persist(visit);
        return visit.getId();
    }

    @Override
    public Visit update(Visit visit) {
        return em.merge(visit);
    }

    @Override
    public void delete(Visit visit) {
        em.remove(visit);
    }

    @Override
    public Visit find(int id) {
     final Query query = em.createQuery("from Visit where id = :id");
        query.setParameter("id", id);
        return (Visit) query.getSingleResult();
    }

    @Override
    public List<Visit> findByHunter(Hunter hunter) {
       final Query query = em.createQuery("from Visit where hunter = :hunter");
        query.setParameter("hunter", hunter.getId());
        return query.getResultList();
    }

    @Override
    public List<Visit> findByLocation(Location location) {
        final Query query = em.createQuery("from Visit where location = :location");
        query.setParameter("location", location.getId());
        return query.getResultList();
    }

    @Override
    public List<Visit> findAll() {
        final Query query = em.createQuery("from Visit");
        return query.getResultList();
    }
}