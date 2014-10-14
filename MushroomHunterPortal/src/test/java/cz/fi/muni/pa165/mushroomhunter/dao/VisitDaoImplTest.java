package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import cz.fi.muni.pa165.mushroomhunter.entity.Type;
import cz.fi.muni.pa165.mushroomhunter.entity.Visit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Unit tests of the Visit's DAO class.
 * 
 * @author Simon
 */
public class VisitDaoImplTest {
    
    private MushroomDao mushroomDao;
    private VisitDao visitDao;
    private HunterDao hunterDao;
    private LocationDao locationDao;
    
    private Visit visit;
    private Hunter hunter;
    private Mushroom mushroom1;
    private Mushroom mushroom2;
    private Location location;
        
    /**
     * Initializes stuff before every test.
     */
    @Before
    public void setUp() throws ParseException {
        visitDao = new VisitDaoImpl();
        mushroomDao = new MushroomDaoImpl();
        hunterDao = new HunterDaoImpl();
        locationDao = new LocationDaoImpl();
        
        EntityManager em = Persistence.createEntityManagerFactory("TestPU").createEntityManager();
        em.getTransaction().begin();
        ReflectionTestUtils.setField(this.visitDao, "em", em);
        ReflectionTestUtils.setField(this.mushroomDao, "em", em);
        ReflectionTestUtils.setField(this.hunterDao, "em", em);
        ReflectionTestUtils.setField(this.locationDao, "em", em);
        
        location = new Location();
        location.setName("Zamilovaný háj");
        location.setDescription("Hríby nájdete na rozhraní lúky a lesa.");
        location.setNearCity("Brno");

        hunter = new Hunter();
        hunter.setNick("Lesný muž");
        hunter.setFirstName("Anton");
        hunter.setSurname("Hlaváček");
        hunter.setDescription("Vášnivý hubár.");
        
        Date startOfOccurence1 = new SimpleDateFormat("MM/dd/yyyy").parse("8/1/2014");
        Date endOfOccurence1 = new SimpleDateFormat("MM/dd/yyyy").parse("11/1/2014");

        mushroom1 = new Mushroom();
        mushroom1.setName("Bedla vysoka");
        mushroom1.setType(Type.EDIBLE);
        mushroom1.setStartOfOccurence(startOfOccurence1);
        mushroom1.setEndOfOccurence(endOfOccurence1);
        
        Date startOfOccurence2 = new SimpleDateFormat("MM/dd/yyyy").parse("9/1/2014");
        Date endOfOccurence2 = new SimpleDateFormat("MM/dd/yyyy").parse("12/1/2014");

        mushroom2 = new Mushroom();
        mushroom2.setName("Lievik trubkovitý");
        mushroom2.setType(Type.EDIBLE);
        mushroom2.setStartOfOccurence(startOfOccurence2);
        mushroom2.setEndOfOccurence(endOfOccurence2);
        
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        
        Map<Long,Integer> map = new HashMap();

        map.put(mushroom1.getId(), 10);
        map.put(mushroom2.getId(), 30);

        Date dateOfVisit = new SimpleDateFormat("MM/dd/yyyy").parse("10/18/2014");
        
        visit = new Visit(); 
        visit.setLocation(location);
        visit.setHunter(hunter);
        visit.setDate(dateOfVisit);
        visit.setFoundMushrooms(map);
        
        hunterDao.save(hunter);
        visitDao.save(visit);
        locationDao.save(location);
        
    }
    
    /**
     * Closes entity manager after every test.
     */    
    @After
    public void tearDown() {
          ((EntityManager) ReflectionTestUtils.getField(this.visitDao, "em")).close();
    }

    /**
     * Test of save method, of class VisitDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Long visitId = null;
        visitId = visit.getId();
        Assert.assertNotNull(visitId);
    }

    /**
     * Test of update method, of class VisitDaoImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Date dateOfVisit = new SimpleDateFormat("MM/dd/yyyy").parse("1/1/2011");
        visit.setDate(dateOfVisit);
        visitDao.update(visit);
        Visit visitRetrieved = visitDao.find(visit.getId().intValue());
        Assert.assertEquals(visit, visitRetrieved);
    }

    /**
     * Test of delete method, of class VisitDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Long visitId = visit.getId();
        visitDao.delete(visit);
        
        EntityManager em = (EntityManager) ReflectionTestUtils.getField(visitDao, "em");
        final Query query = em.createQuery("SELECT l FROM Visit l WHERE id = :id");
        query.setParameter("id", visitId);
        
        Visit visitRetrieved = null;
        try{
            visitRetrieved = (Visit)query.getSingleResult();
        } catch (Exception e){
        }
        
        Assert.assertNull(visitRetrieved);
    }

    /**
     * Test of find method, of class VisitDaoImpl.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        
        Location location2 = new Location();
        location2.setNearCity("Brno");
        location2.setName("Bystrc");
        location2.setDescription("Zmiešaný les.");

        Hunter hunter2 = new Hunter();
        hunter2.setNick("Peter231");
        hunter2.setFirstName("Peter");
        hunter2.setSurname("Kubáni");
        hunter2.setDescription("Náruživec.");

        Date dateOfVisit = new SimpleDateFormat("MM/dd/yyyy").parse("10/18/2014");
        
        Map<Long,Integer> map = new HashMap();

        map.put(mushroom1.getId(), 10);
        map.put(mushroom2.getId(), 30);
        
        Visit visit2 = new Visit();
        Visit visit3 = new Visit();
        
        visit2.setLocation(location2);
        visit2.setHunter(hunter);
        visit2.setDate(dateOfVisit);
        visit2.setFoundMushrooms(map);
        
        visit3.setLocation(location);
        visit3.setHunter(hunter2);
        visit3.setDate(dateOfVisit);
        visit3.setFoundMushrooms(map);
        
        hunterDao.save(hunter2);
        locationDao.save(location2);
        visitDao.save(visit2);
        visitDao.save(visit3);
        
        Visit visitRetrieved = visitDao.find(visit2.getId().intValue());
        assertEquals(visit2, visitRetrieved);
    }

    /**
     * Test of findByHunter method, of class VisitDaoImpl.
     */
    @Test
    public void testFindByHunter() throws Exception {
        System.out.println("find by hunter");

        Hunter hunter2 = new Hunter();
        hunter2.setNick("Peter231");
        hunter2.setFirstName("Peter");
        hunter2.setSurname("Kubáni");
        hunter2.setDescription("Náruživec.");
        
        Hunter hunter3 = new Hunter();
        hunter3.setNick("Vladino");
        hunter3.setFirstName("Vladimír");
        hunter3.setSurname("Drápal");
        hunter3.setDescription("Sokolie oko.");

        Date dateOfVisit = new SimpleDateFormat("MM/dd/yyyy").parse("10/18/2014");
        
        Map<Long,Integer> map = new HashMap();

        map.put(mushroom1.getId(), 10);
        map.put(mushroom2.getId(), 30);

        Visit visit2 = new Visit();
        Visit visit3 = new Visit();
        
        visit2.setLocation(location);
        visit2.setHunter(hunter2);
        visit2.setDate(dateOfVisit);
        visit2.setFoundMushrooms(map);
        
        visit3.setLocation(location);
        visit3.setHunter(hunter3);
        visit3.setDate(dateOfVisit);
        visit3.setFoundMushrooms(map);
        
        hunterDao.save(hunter2);
        hunterDao.save(hunter3);
        visitDao.save(visit2);
        visitDao.save(visit3);
        
        List<Visit> visits = visitDao.findByHunter(hunter2);
        for (Visit visitRetrieved: visits) {
            assertEquals(visitRetrieved.getHunter(), hunter2);
        }
    }

    /**
     * Test of findByLocation method, of class VisitDaoImpl.
     */
    @Test
    public void testFindByLocation() throws Exception {
        System.out.println("findByLocation");

        Hunter hunter2 = new Hunter();
        hunter2.setNick("Peter231");
        hunter2.setFirstName("Peter");
        hunter2.setSurname("Kubáni");
        hunter2.setDescription("Náruživec.");
        
        Location location2 = new Location();
        location2.setNearCity("Brno");
        location2.setName("Bystrc");
        location2.setDescription("Zmiešaný les.");
        
        Location location3 = new Location();
        location3.setNearCity("Adamov");
        location3.setName("Okolie Macochy.");
        location3.setDescription("Musí byť vlhké počasie.");

        Date dateOfVisit = new SimpleDateFormat("MM/dd/yyyy").parse("10/18/2014");
        
        Map<Long,Integer> map = new HashMap();

        map.put(mushroom1.getId(), 10);
        map.put(mushroom2.getId(), 30);

        Visit visit2 = new Visit();
        Visit visit3 = new Visit();
        
        visit2.setLocation(location2);
        visit2.setHunter(hunter2);
        visit2.setDate(dateOfVisit);
        visit2.setFoundMushrooms(map);
        
        visit3.setLocation(location3);
        visit3.setHunter(hunter);
        visit3.setDate(dateOfVisit);
        visit3.setFoundMushrooms(map);
        
        hunterDao.save(hunter2);
        locationDao.save(location2);
        locationDao.save(location3);
        visitDao.save(visit2);
        visitDao.save(visit3);
        
        List<Visit> visits = visitDao.findByLocation(location2);
        for (Visit visitRetrieved: visits) {
            assertEquals(visitRetrieved.getLocation(), location2);
        }
    }

    /**
     * Test of findAll method, of class VisitDaoImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");

        Hunter hunter2 = new Hunter();
        hunter2.setNick("Peter231");
        hunter2.setFirstName("Peter");
        hunter2.setSurname("Kubáni");
        hunter2.setDescription("Náruživec.");
        
        Location location2 = new Location();
        location2.setNearCity("Brno");
        location2.setName("Bystrc");
        location2.setDescription("Zmiešaný les.");
        
        Location location3 = new Location();
        location3.setNearCity("Adamov");
        location3.setName("Okolie Macochy.");
        location3.setDescription("Musí byť vlhké počasie.");

        Date dateOfVisit = new SimpleDateFormat("MM/dd/yyyy").parse("10/18/2014");
        
        Map<Long,Integer> map = new HashMap();

        map.put(mushroom1.getId(), 10);
        map.put(mushroom2.getId(), 30);

        Visit visit2 = new Visit();
        Visit visit3 = new Visit();
        
        visit2.setLocation(location2);
        visit2.setHunter(hunter2);
        visit2.setDate(dateOfVisit);
        visit2.setFoundMushrooms(map);
        
        visit3.setLocation(location3);
        visit3.setHunter(hunter);
        visit3.setDate(dateOfVisit);
        visit3.setFoundMushrooms(map);
        
        hunterDao.save(hunter2);
        locationDao.save(location2);
        locationDao.save(location3);
        visitDao.save(visit2);
        visitDao.save(visit3);
        
        List<Visit> allVisits = new ArrayList();
        allVisits.add(visit);
        allVisits.add(visit2);
        allVisits.add(visit3);
        
        List<Visit> retrievedVisits = visitDao.findAll();
        
        assertEquals(retrievedVisits, allVisits);
    }
    
}
