package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * The implementation of a data object for the entity Location.
 *
 * @author Roman Smékal
 */
@Repository
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
        if (!em.contains(location)){
            location = em.merge(location);
        }
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

    /**
     * Returns list of all Locations ordered by number of found mushrooms.
     *
     * @param ascending - True for ascending sorting, false for descending
     * sorting.
     * @return
     */
    @Override
    public List<Location> findByOccurence(boolean ascending) {
        Map<Location, Integer> relustlMap = this.findByOccurenceWithSumOfMushrooms(ascending);
        List<Location> resultList = new ArrayList<Location>();
        for (Location l : relustlMap.keySet()) {
            resultList.add(l);
        }
        return resultList;
    }

    /**
     * Returns map of all Locations ordered by number of found mushrooms.
     *
     * @param ascending - True for ascending sorting, false for descending
     * sorting.
     * @return
     */
    public Map<Location, Integer> findByOccurenceWithSumOfMushrooms(boolean ascending) {
        //Get ordered list of location with mushrooms
        String order = ascending ? "ASC" : "DESC";
        final Query query = em.createQuery("SELECT SUM(fm), v.location.id, v.location.name, v.location.description, v.location.nearCity FROM Visit v LEFT JOIN v.foundMushrooms fm RIGHT JOIN v.location l GROUP BY v.location.id, v.location.name, v.location.description, v.location.nearCity ORDER BY SUM(fm) " + order);
        List<Object[]> locationWihtMushroomsList = query.getResultList();
        Map<Location, Integer> resultMap = new HashMap<Location, Integer>();
        for (Object[] location : locationWihtMushroomsList) {
            {
                Integer sumOfMushrooms = new Integer((int) (long) location[0]);

                Location l = new Location();
                l.setId((Long) location[1]);
                l.setName((String) location[2]);
                l.setDescription((String) location[3]);
                l.setNearCity((String) location[4]);

                resultMap.put(l, sumOfMushrooms);
            }

        }
        //Get all mushrooms
        List<Location> allLocaitonsList = this.findAll();
        //Add location without mushrooms to result map
        for (Location location : allLocaitonsList) {
            if(!resultMap.containsKey(location)){
                resultMap.put(location, 0);
            }
        }
        return resultMap;
    }

    @Override
    public List<Location> findAll() {
        final Query query = em.createQuery("FROM Location");
        return query.getResultList();
    }
}
