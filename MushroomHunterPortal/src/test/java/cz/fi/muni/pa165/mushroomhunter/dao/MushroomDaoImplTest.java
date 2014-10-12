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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author LukĂˇĹˇ Valach
 */
public class MushroomDaoImplTest {

    private MushroomDaoImpl mushroomDao;

    public MushroomDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mushroomDao = new MushroomDaoImpl();
        EntityManager em = Persistence.createEntityManagerFactory("TestPU").createEntityManager();
        em.getTransaction().begin();
        ReflectionTestUtils.setField(this.mushroomDao, "em", em);
    }

    @After
    public void tearDown() {
        ((EntityManager) ReflectionTestUtils.getField(this.mushroomDao, "em")).close();
    }

    public Mushroom createMushroom(String name, Date startOfOcc, Date enDate, Type type) {
        Mushroom m = new Mushroom();
        m.setName(name);
        m.setStartOfOccurence(startOfOcc);
        m.setEndOfOccurence(enDate);
        m.setType(type);
        return m;
    }

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
        Mushroom mushroom = this.createMushroom("MochomĹŻrka", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        mushroomDao.save(mushroom);
        
        Long persistedMushroomId = null;
        if(mushroom.getId() != null && mushroom.getId() > 0){
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
        Mushroom mushroom = this.createMushroom("MochomĹŻrka", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
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
        Mushroom mushroom = this.createMushroom("MochomĹŻrka", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        mushroomDao.save(mushroom);
        
        mushroomDao.delete(mushroom);
        
        EntityManager em = (EntityManager) ReflectionTestUtils.getField(mushroomDao, "em");
        final Query query = em.createQuery("SELECT m FROM Mushroom m WHERE id = :id");
        query.setParameter("id", mushroom.getId());
        
        Mushroom newMushroom = null;
        try{
            newMushroom = (Mushroom)query.getSingleResult();
        } catch (Exception e){
        }
        assertNull(newMushroom);
    }

    /**
     * Test of find method, of class MushroomDaoImpl.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("Mushroom find by ID test");
        Mushroom mushroom1 = this.createMushroom("MochomĹŻrka", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("HĹ™ib", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("LiĹˇka obecnĂˇ", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
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
        Mushroom mushroom1 = this.createMushroom("MochomĹŻrka", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("HĹ™ib", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("LiĹˇka obecnĂˇ", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);
        
        List<Mushroom> exceptedMushroomList = new ArrayList<>();
        exceptedMushroomList.add(mushroom1);
        exceptedMushroomList.add(mushroom2);
        exceptedMushroomList.add(mushroom3);
        
        List<Mushroom> storedMushroomList = mushroomDao.findAll();
        
        assertEquals(exceptedMushroomList.size(), storedMushroomList.size());
        for (int i = 0; exceptedMushroomList.size() > i && storedMushroomList.size() > i; i++){
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
        Mushroom mushroom1 = this.createMushroom("MochomĹŻrka", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("HĹ™ib", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("LiĹˇka obecnĂˇ", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);
        
        List<Mushroom> searchedMushrooms = mushroomDao.findByName(mushroom2.getName());
        for (int i = 0; searchedMushrooms.size() > i && searchedMushrooms.size() > i; i++){
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
        
        HunterDaoImpl hunterDao = new HunterDaoImpl();
        EntityManager emh = Persistence.createEntityManagerFactory("TestPU").createEntityManager();
        emh.getTransaction().begin();
        ReflectionTestUtils.setField(hunterDao, "em", emh);
        
        LocationDaoImpl locationDao = new LocationDaoImpl();
        EntityManager eml = Persistence.createEntityManagerFactory("TestPU").createEntityManager();
        eml.getTransaction().begin();
        ReflectionTestUtils.setField(locationDao, "em", eml);
        
        VisitDaoImpl visitDao = new VisitDaoImpl();
        EntityManager emv = Persistence.createEntityManagerFactory("TestPU").createEntityManager();
        emv.getTransaction().begin();
        ReflectionTestUtils.setField(visitDao, "em", emv);
        
        Mushroom mushroom1 = this.createMushroom("MochomĹŻrka", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("HĹ™ib", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("LiĹˇka obecnĂˇ", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);
        
        Hunter hunter = new Hunter();
        hunter.setFirstName("Pepa");
        hunter.setSurname("NovĂˇk");
        hunter.setDescription("Popis");
        hunter.setNick("PNovak");
        hunterDao.save(hunter);
        
        Location location = new Location();
        location.setName("Brno");
        location.setDescription("Popis");
        locationDao.save(location);
        
        HashMap<Long, Integer> foundMushrooms = new HashMap<>();
        foundMushrooms.put(mushroom2.getId(), 10);
        
        Visit visit = new Visit();
        visit.setHunter(hunter);
        visit.setLocation(location);
        visit.setDate(new Date(1, 7, 1));
        visit.setFoundMushrooms(foundMushrooms);
        visitDao.save(visit);
        
        List<Mushroom> searchedMushrooms = mushroomDao.findByLocation(location);
        assertEquals(1, searchedMushrooms.size());
        if (searchedMushrooms.size() > 0){
            this.compareMushroomAttributes(mushroom2, searchedMushrooms.get(0));
        }
    }

    /**
     * Test of findByType method, of class MushroomDaoImpl.
     */
    @Test
    public void testFindByType() throws Exception {
        System.out.println("Mushroom find by type test");
        Mushroom mushroom1 = this.createMushroom("MochomĹŻrka", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("HĹ™ib", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("LiĹˇka obecnĂˇ", new Date(1, 4, 1), new Date(1, 8, 1) , Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);
        
        List<Mushroom> searchedMushrooms = mushroomDao.findByType(mushroom2.getType());
        for (int i = 0; searchedMushrooms.size() > i && searchedMushrooms.size() > i; i++){
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
        Mushroom mushroom1 = this.createMushroom("MochomĹŻrka", new Date(1, 1, 1), new Date(1, 3, 1) , Type.POISONOUS);
        Mushroom mushroom2 = this.createMushroom("HĹ™ib", new Date(1, 2, 1), new Date(1, 6, 1) , Type.POISONOUS);
        Mushroom mushroom3 = this.createMushroom("LiĹˇka obecnĂˇ", new Date(1, 6, 1), new Date(1, 8, 1) , Type.POISONOUS);
        mushroomDao.save(mushroom1);
        mushroomDao.save(mushroom2);
        mushroomDao.save(mushroom3);
        
        List<Mushroom> searchedMushrooms = mushroomDao.findByOccurenceDate(new Date(1, 4, 1), new Date(1, 5, 1));
        assertEquals(1, searchedMushrooms.size());
        if (searchedMushrooms.size() > 0){
            this.compareMushroomAttributes(mushroom2, searchedMushrooms.get(0));
        }
    }

}
