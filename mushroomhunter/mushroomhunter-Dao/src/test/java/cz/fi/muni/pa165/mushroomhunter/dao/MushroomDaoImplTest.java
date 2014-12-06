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
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Unit tests of the Mushroom's DAO class.
 *
 * @author Lukáš Valach
 */
public class MushroomDaoImplTest {

    //Implementation of Mushroom's DAO class.
    private MushroomDaoImpl mushroomDao;
    //Entity manager of Mushroom's DAO class.
    EntityManager em = null;

    /**
     * Constructor.
     */
    public MushroomDaoImplTest() {
    }

    /**
     * Initializes stuff before every test.
     */
    @Before
    public void setUp() {
        mushroomDao = new MushroomDaoImpl();
        em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        em.getTransaction().begin();
        ReflectionTestUtils.setField(this.mushroomDao, "em", em);
    }

    /**
     * Closes entity manager after every test.
     */
    @After
    public void tearDown() {
        if (em != null) {
            if (em.getTransaction() != null && em.getTransaction().isActive()){
                em.getTransaction().commit();
            }
            em.close();
        }
    }

    /**
     * A method used to create the Mushroom's entity object.
     *
     * @param name The name of the mushroom.
     * @param startOfOcc When the occurence of mushroom begins.
     * @param enDate When the occurence of mushroom ends
     * @param type The type of the mushroom.
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
     * Compares attributes of two Mushroom's entity instances.
     *
     * @param m1 The first mushroom to be compared.
     * @param m2 The second mushroom to be compared.
     */
    private void compareMushroomAttributes(Mushroom m1, Mushroom m2) {
        assertEquals(m1.getId(), m2.getId());
        assertEquals(m1.getName(), m2.getName());
        assertEquals(m1.getStartOfOccurence(), m2.getStartOfOccurence());
        assertEquals(m1.getEndOfOccurence(), m2.getEndOfOccurence());
        assertEquals(m1.getType(), m2.getType());
    }

    /**
     * Test of save method, of class MushroomDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("Mushroom Save Test");
        Mushroom mushroom = this.createMushroom("Mochomurka", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        mushroomDao.save(mushroom);

        Long persistedMushroomId = null;
        if (mushroom.getId() != null && mushroom.getId() > 0) {
            persistedMushroomId = mushroom.getId();
        }
        assertNotNull(persistedMushroomId);
    }

    /**
     * Test of update method, of class MushroomDaoImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("Mushroom Update Test");
        Mushroom mushroom = this.createMushroom("Mochomurka", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        mushroomDao.save(mushroom);
        mushroom.setType(Type.EDIBLE);
        Mushroom updatedMushroom = mushroomDao.update(mushroom);
        this.compareMushroomAttributes(mushroom, updatedMushroom);
    }

    /**
     * Test of delete method, of class MushroomDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("Mushroom Delete Test");
        Mushroom mushroom = this.createMushroom("Mochomurka", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        mushroomDao.save(mushroom);

        mushroomDao.delete(mushroom);

        EntityManager em = (EntityManager) ReflectionTestUtils.getField(mushroomDao, "em");
        final Query query = em.createQuery("SELECT m FROM Mushroom m WHERE id = :id");
        query.setParameter("id", mushroom.getId());

        Mushroom newMushroom = null;
        try {
            newMushroom = (Mushroom) query.getSingleResult();
        } catch (Exception e) {
        }
        assertNull(newMushroom);
    }

    /**
     * Test of find method, of class MushroomDaoImpl.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("Mushroom find by ID test");
        Mushroom mushroom1 = this.createMushroom("Mochomurka", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("Hrib", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("Liska obecna", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);

        Mushroom searchedMushroom = mushroomDao.find(mushroom2.getId());
        this.compareMushroomAttributes(mushroom2, searchedMushroom);
    }

    /**
     * Test of findAll method, of class MushroomDaoImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("Mushroom find all test");
        Mushroom mushroom1 = this.createMushroom("Mochomurka", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("Hrib", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("Liska obecna", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);

        List<Mushroom> exceptedMushroomList = new ArrayList<>();
        exceptedMushroomList.add(mushroom1);
        exceptedMushroomList.add(mushroom2);
        exceptedMushroomList.add(mushroom3);

        List<Mushroom> storedMushroomList = mushroomDao.findAll();

        assertEquals(exceptedMushroomList.size(), storedMushroomList.size());
        for (int i = 0; exceptedMushroomList.size() > i && storedMushroomList.size() > i; i++) {
            this.compareMushroomAttributes(exceptedMushroomList.get(i), storedMushroomList.get(i));
            i++;
        }
    }

    /**
     * Test of findByName method, of class MushroomDaoImpl.
     */
    @Test
    public void testFindByName() throws Exception {
        System.out.println("Mushroom find by name test");
        Mushroom mushroom1 = this.createMushroom("Mochomurka", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("Hrib", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("Liska obecna", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);

        List<Mushroom> searchedMushrooms = mushroomDao.findByName(mushroom2.getName());
        for (int i = 0; searchedMushrooms.size() > i && searchedMushrooms.size() > i; i++) {
            assertEquals(mushroom2.getName(), searchedMushrooms.get(i).getName());
            i++;
        }
    }

    /**
     * Test of findByLocation method, of class MushroomDaoImpl.
     */
    @Test
    public void testFindByLocation() throws Exception {
        System.out.println("Mushroom find by location test");
        
        //Create HunterDao
        HunterDaoImpl hunterDao = new HunterDaoImpl();
        ReflectionTestUtils.setField(hunterDao, "em", em);

        //Create LocationDao
        LocationDaoImpl locationDao = new LocationDaoImpl();
        ReflectionTestUtils.setField(locationDao, "em", em);

        //Create VisitDao
        VisitDaoImpl visitDao = new VisitDaoImpl();
        ReflectionTestUtils.setField(visitDao, "em", em);

        //Create Mushroom
        Mushroom mushroom1 = this.createMushroom("Mochomurka", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("Hrib", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("Liska obecna", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);

        //Create Hunter entity
        Hunter hunter = new Hunter();
        hunter.setFirstName("Pepa");
        hunter.setSurname("NovĂˇk");
        hunter.setDescription("Popis");
        hunter.setNick("PNovak");
        hunterDao.save(hunter);
        
        //Create Location entity
        Location location = new Location();
        location.setName("Brnensky les");
        location.setDescription("Popis");
        location.setNearCity("Brno");
        locationDao.save(location);
        //Add mushrooms into the location
        HashMap<Long, Integer> foundMushrooms = new HashMap<>();
        foundMushrooms.put(mushroom2.getId(), 10);

        //Add Visit entity
        Visit visit = new Visit();
        visit.setHunter(hunter);
        visit.setLocation(location);
        visit.setDate(new Date(1, 7, 1));
        visit.setFoundMushrooms(foundMushrooms);
        visitDao.save(visit);

        //Find mushroom by location and test the result
        List<Mushroom> searchedMushrooms = mushroomDao.findByLocation(location);
        assertEquals(1, searchedMushrooms.size());
        if (searchedMushrooms.size() > 0) {
            this.compareMushroomAttributes(mushroom2, searchedMushrooms.get(0));
        }
    }

    /**
     * Test of findByType method, of class MushroomDaoImpl.
     */
    @Test
    public void testFindByType() throws Exception {
        System.out.println("Mushroom find by type test");
        Mushroom mushroom1 = this.createMushroom("Mochomurka", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("Hrib", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("Liska obecna", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);

        List<Mushroom> searchedMushrooms = mushroomDao.findByType(mushroom2.getType());
        for (int i = 0; searchedMushrooms.size() > i && searchedMushrooms.size() > i; i++) {
            assertEquals(mushroom2.getType(), searchedMushrooms.get(i).getType());
            i++;
        }
    }

    /**
     * Test of findByOccurenceDate method, of class MushroomDaoImpl.
     */
    @Test
    public void testFindByOccurenceDate() throws Exception {
        System.out.println("Mushroom find by type test");
        Mushroom mushroom1 = this.createMushroom("Mochomurka", new Date(1, 1, 1), new Date(1, 3, 1), Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("Hrib", new Date(1, 2, 1), new Date(1, 6, 1), Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("Liska obecna", new Date(1, 6, 1), new Date(1, 8, 1), Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);

        List<Mushroom> searchedMushrooms = mushroomDao.findByOccurenceDate(new Date(1, 4, 1), new Date(1, 5, 1));
        assertEquals(1, searchedMushrooms.size());
        if (searchedMushrooms.size() > 0) {
            this.compareMushroomAttributes(mushroom2, searchedMushrooms.get(0));
        }
    }

}
