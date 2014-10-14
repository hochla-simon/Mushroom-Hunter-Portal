package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import cz.fi.muni.pa165.mushroomhunter.entity.Type;
import cz.fi.muni.pa165.mushroomhunter.entity.Visit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.test.util.ReflectionTestUtils;


/**
 * Unit tests of the Location's DAO class.
 * 
 * @author Radim Cejka
 */


public class LocationDaoImplTest
{
    /**
     * Implementation of Location's DAO class.
     */
    private LocationDaoImpl locDimpl;
    private EntityManager em;
         
    /**
     * Initializes stuff before every test.
     */
    @Before
    public void setUp() {
        locDimpl = new LocationDaoImpl();
        em = Persistence.createEntityManagerFactory("TestPU").createEntityManager();
        em.getTransaction().begin();
        ReflectionTestUtils.setField(this.locDimpl, "em", em);

/*
        Location location = new Location();
        location.setDescription("Under big spruce on blue tourinst way");
        location.setNearCity("Jedlova");
        location.setName("Pod smrkem");
         */
    }
    
    /**
     * Closes entity manager after every test.
     */ 
    @After
    public void tearDown() {
         if(em != null) {
            if (em.getTransaction() != null && em.getTransaction().isActive()){
                em.getTransaction().commit();
            }
            em.close();
        }
    }

    /**
     * A method used to create the Mushroom's entity object.
     * 
     * @param name Mushroom's name string.
     * @param startOfOcc Mushroom's start of the occurence.
     * @param enDate Mushroom's end of the occurence.
     * @param type Mushroom's type.
     * @return Created mushroom with the given attributes.
     */
    public Mushroom createMushroom(String name, Date startOfOcc, Date enDate, Type type) {
        Mushroom m = new Mushroom();
        m.setName(name);
        m.setStartOfOccurence(startOfOcc);
        m.setEndOfOccurence(enDate);
        m.setType(type);
        return m;
    }
    
    /**
     * Compares attributes of two Location's entity instances.
     * 
     * @param oldLocation The first location to be compared.
     * @param newLocation The second location to be compared.
     */
    private void compareLocation(Location oldLocation, Location newLocation)
    {
        assertEquals(oldLocation.getId(), newLocation.getId());
        assertEquals(oldLocation.getName(), newLocation.getName());
        assertEquals(oldLocation.getDescription(), newLocation.getDescription());
        assertEquals(oldLocation.getNearCity(), newLocation.getNearCity());
       
    }
    /**
     * Test of save method, of class LocationDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Location location = new Location();
        location.setDescription("Near by lake, right side of dirty road");
        location.setNearCity("Nova ves");
        location.setName("Hribova u vody");
        
        locDimpl.save(location);
        Long savedLocId = null;
        if(location.getId() != null && location.getId() > 0){
            savedLocId = location.getId();
        }
        assertNotNull(savedLocId);
        
        }

    /**
     * Test of update method, of class LocationDaoImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
       Location location = new Location();
        Location updateLoc;
        location.setDescription("Near by lake, right side of dirty road");
        location.setNearCity("Nova ves");
        location.setName("Hribova u vody");
        
        locDimpl.save(location);
        
        location.setDescription("Under big spruce on blue tourinst way");
        updateLoc = locDimpl.update(location);
        compareLocation(location,updateLoc);
        location.setNearCity("Jedlova");
        updateLoc = locDimpl.update(location);
        compareLocation(location,updateLoc);
        location.setName("Pod smrkem");
        updateLoc = locDimpl.update(location);
        compareLocation(location,updateLoc);

    }

    /**
     * Test of delete method, of class LocationDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Location location = new Location();
        location.setDescription("Near by lake, right side of dirty road");
        location.setNearCity("Nova ves");
        location.setName("Hribova u vody");
        Location location2 = new Location();
        location2.setDescription("Under big spruce on blue tourinst way");
        location2.setNearCity("Jedlova");
        location2.setName("Pod smrkem");
        
        locDimpl.save(location);
        locDimpl.save(location2);
        
        locDimpl.delete(location);
        
        em = (EntityManager) ReflectionTestUtils.getField(locDimpl, "em");
        final Query query = em.createQuery("SELECT m FROM Location m WHERE id = :id");
        query.setParameter("id", location.getId());
        
        Location newLocation = null;
        try{
            newLocation = (Location)query.getSingleResult();
        } catch (Exception e){
        }
        assertNull(newLocation);
    }


    /**
     * Test of find method, of class LocationDaoImpl.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Location location = new Location();
        location.setDescription("Near by lake, right side of dirty road");
        location.setNearCity("Nova ves");
        location.setName("Hribova u vody");
        
        Location location2 = new Location();
        location2.setDescription("Under big spruce on blue tourinst way");
        location2.setNearCity("Jedlova");
        location2.setName("Pod smrkem");
        
        Location location3 = new Location();
        location3.setDescription("Around big stone");
        location3.setNearCity("Pecky");
        location3.setName("Kamen");
        
        locDimpl.save(location);
        locDimpl.save(location2);
        locDimpl.save(location3);
        
        Location findLoc = locDimpl.find(location2.getId());
        compareLocation(location2, findLoc);
    }

    /**
     * Test of findByNearCity method, of class LocationDaoImpl.
     */
    @Test
    public void testFindByNearCity() throws Exception {
        System.out.println("findByNearCity");
        String nearCity = "Pecky";
        Location location = new Location();
        location.setDescription("Near by lake, right side of dirty road");
        location.setNearCity("Nova ves");
        location.setName("Hribova u vody");
        
        Location location2 = new Location();
        location2.setDescription("Under big spruce on blue tourinst way");
        location2.setNearCity("Jedlova");
        location2.setName("Pod smrkem");
        
        Location location3 = new Location();
        location3.setDescription("Around big stone");
        location3.setNearCity("Pecky");
        location3.setName("Kamen");
        
        locDimpl.save(location);
        locDimpl.save(location2);
        locDimpl.save(location3);
        
        List<Location> locationList = locDimpl.findByNearCity(nearCity);
        assertEquals(locationList.size(), 1);
        compareLocation(location3, locationList.get(0));
    }

    /**
     * Test of findByMushroom method, of class LocationDaoImpl.
     */
    @Test
    public void testFindByMushroom() throws Exception {
        System.out.println("findByMushroom");
        HunterDaoImpl hunterDao = new HunterDaoImpl();
        ReflectionTestUtils.setField(hunterDao, "em", em);
        
        MushroomDaoImpl mushroomDao = new MushroomDaoImpl();
        ReflectionTestUtils.setField(mushroomDao, "em", em);
        
        VisitDaoImpl visitDao = new VisitDaoImpl();
        ReflectionTestUtils.setField(visitDao, "em", em);
        
        Mushroom mushroom1 = new Mushroom();
        mushroom1.setName("Mochomůrka");
        mushroom1.setStartOfOccurence(new Date(1, 4, 1));
        mushroom1.setEndOfOccurence(new Date(1, 8, 1));
        mushroom1.setType( Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("Hřib", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("Liška obecná", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);
        
        Hunter hunter = new Hunter();
        hunter.setFirstName("Pepa");
        hunter.setSurname("Novák");
        hunter.setDescription("Popis");
        hunter.setNick("PNovak");
        hunterDao.save(hunter);
        
        Location location = new Location();
        location.setName("Brno");
        location.setNearCity("Brno");
        location.setDescription("Popis");
        locDimpl.save(location);
        
        HashMap<Long, Integer> foundMushrooms = new HashMap<>();
        foundMushrooms.put(mushroom2.getId(), 10);
        
        Visit visit = new Visit();
        visit.setHunter(hunter);
        visit.setLocation(location);
        visit.setDate(new Date(1, 7, 1));
        visit.setFoundMushrooms(foundMushrooms);
        visitDao.save(visit);
        
        List<Location> findLocations = locDimpl.findByMushroom(mushroom2);
        assertEquals(1, findLocations.size());
        if (findLocations.size() > 0){
            compareLocation(location, findLocations.get(0));
        }
    }

    /**
     * Test of findByOccurence method, of class LocationDaoImpl.
     */
    @Test
    public void testFindByOccurence() throws Exception {
        System.out.println("findByOccurence");
        HunterDaoImpl hunterDao = new HunterDaoImpl();
        ReflectionTestUtils.setField(hunterDao, "em", em);
        
        MushroomDaoImpl mushroomDao = new MushroomDaoImpl();
        ReflectionTestUtils.setField(mushroomDao, "em", em);
        
        VisitDaoImpl visitDao = new VisitDaoImpl();
        ReflectionTestUtils.setField(visitDao, "em", em);
        
        Mushroom mushroom1 = new Mushroom();
        mushroom1.setName("Mochomůrka");
        mushroom1.setStartOfOccurence(new Date(1, 4, 1));
        mushroom1.setEndOfOccurence(new Date(1, 8, 1));
        mushroom1.setType( Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("Hřib", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("Liška obecná", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);
        
        Hunter hunter = new Hunter();
        hunter.setFirstName("Pepa");
        hunter.setSurname("Novák");
        hunter.setDescription("Popis");
        hunter.setNick("PNovak");
        hunterDao.save(hunter);
        
        Location location = new Location();
        location.setName("Brno");
        location.setNearCity("Brno");
        location.setDescription("Popis");
        locDimpl.save(location);
        
        Location location2 = new Location();
        location2.setDescription("Under big spruce on blue tourinst way");
        location2.setNearCity("Jedlova");
        location2.setName("Pod smrkem");
        
        HashMap<Long, Integer> foundMushrooms = new HashMap<>();
        foundMushrooms.put(mushroom2.getId(), 10);
        
        Visit visit = new Visit();
        visit.setHunter(hunter);
        visit.setLocation(location);
        visit.setDate(new Date(1, 7, 1));
        visit.setFoundMushrooms(foundMushrooms);
        visitDao.save(visit);
        
        HashMap<Long, Integer> foundMushrooms2 = new HashMap<>();
        foundMushrooms2.put(mushroom1.getId(), 5);
        
        Visit visit2 = new Visit();
        visit2.setHunter(hunter);
        visit2.setLocation(location2);
        visit2.setDate(new Date(1, 8, 1));
        visit2.setFoundMushrooms(foundMushrooms2);
        visitDao.save(visit2);
        
        List<Location> findLocations = locDimpl.findByOccurence(true);
        assertEquals(2, findLocations.size());
        if (findLocations.size() > 0){
            compareLocation(location2, findLocations.get(0));
            compareLocation(location, findLocations.get(1));
        }
    }

    /**
     * Test of findAll method, of class LocationDaoImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
       Location location = new Location();
        location.setDescription("Near by lake, right side of dirty road");
        location.setNearCity("Nova ves");
        location.setName("Hribova u vody");
        
        Location location2 = new Location();
        location2.setDescription("Under big spruce on blue tourinst way");
        location2.setNearCity("Jedlova");
        location2.setName("Pod smrkem");
        
        Location location3 = new Location();
        location3.setDescription("Around big stone");
        location3.setNearCity("Pecky");
        location3.setName("Kamen");
        
        locDimpl.save(location);
        locDimpl.save(location2);
        locDimpl.save(location3);
        
        List<Location> exceptedList = new ArrayList<>();
        exceptedList.add(location);
        exceptedList.add(location2);
        exceptedList.add(location3);
        
        List<Location> locationList = locDimpl.findAll();
        assertEquals(locationList.size(), exceptedList.size());
        for (int i = 0; exceptedList.size() > i && locationList.size() > i; i++){
            compareLocation(exceptedList.get(i), locationList.get(i));
            i++;
        }    
    }  
}
