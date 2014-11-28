package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * The implementation of a data object for the entity Hunter.
 * 
 * @author Lukáš Valach
 */
@Stateless
@Repository
public class HunterDaoImpl implements HunterDao {

    /**
     * Entity manager.
     */
    @PersistenceContext
    private EntityManager em;

    @Override
    public long save(Hunter hunter) {
        em.persist(hunter);
        return hunter.getId();
    }

    @Override
    public Hunter update(Hunter hunter) {
        return em.merge(hunter);
    }

    @Override
    public void delete(Hunter hunter) {
		if (!em.contains(hunter)){
            hunter = em.merge(hunter);
        }
        em.remove(hunter);
    }

    @Override
    public Hunter find(long id) {
        final Query query = em.createQuery("from Hunter where id = :id");
        query.setParameter("id", id);
        return (Hunter) query.getSingleResult();
    }

    @Override
    public List<Hunter> findByName(String firstName) {
        final Query query = em.createQuery("from Hunter where firstName = :firstName");
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }

    @Override
    public List<Hunter> findBySurname(String surname) {
        final Query query = em.createQuery("from Hunter where surname = :surname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<Hunter> findByNick(String nick) {
        final Query query = em.createQuery("from Hunter where nick = :nick");
        query.setParameter("nick", nick);
        return query.getResultList();
    }
    
    @Override
    public List<Hunter> findAll() {
        final Query query = em.createQuery("from Hunter");
        return query.getResultList();
    }

}
