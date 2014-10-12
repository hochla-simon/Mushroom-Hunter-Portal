package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Roman Smékal
 */
public class HunterDaoImplTest {
    
    private HunterDaoImpl hunterDaoImpl;
    
    public HunterDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        hunterDaoImpl = new HunterDaoImpl();
        EntityManager em = Persistence.createEntityManagerFactory("TestPU").createEntityManager();
        em.getTransaction().begin();
        ReflectionTestUtils.setField(this.hunterDaoImpl, "em", em);
    }

    @After
    public void tearDown() {
        ((EntityManager) ReflectionTestUtils.getField(this.hunterDaoImpl, "em")).close();
    }

    public Hunter createHunter(String firstName, String surname, String description, String nick) {
        Hunter h = new Hunter();
        h.setFirstName(firstName);
        h.setSurname(surname);
        h.setNick(nick);
        h.setDescription(description);
        return h;
    }
    
    private void compareHunterAttributes(Hunter h1, Hunter h2) {
        assertEquals(h1.getId(), h2.getId());
        assertEquals(h1.getFirstName(), h2.getFirstName());
        assertEquals(h1.getSurname(), h2.getSurname());
        assertEquals(h1.getNick(), h2.getNick());
        assertEquals(h1.getDescription(), h2.getDescription());
    }
    
    /**
     * Test for save method of the HunterDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("Hunter save test");
        
        Hunter hunter = this.createHunter("Franta", "Voprsalek", "Short description.", "Frantisek");
        hunterDaoImpl.save(hunter);
        
        Long persistedHunterId = null;
        if(hunter.getId() != null && hunter.getId() > 0){
            persistedHunterId = hunter.getId();
        }
        
         assertNotNull(persistedHunterId);
    }
    
    /**
     * Test for update method of the HunterDaoImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("Hunter update test");
        
        Hunter hunter = this.createHunter("Franta", "Voprsalek", "Short description.", "Frantisek");
        hunterDaoImpl.save(hunter);
        
        hunter.setFirstName("Pepa");
        Hunter updatedHunter = hunterDaoImpl.update(hunter); 
        this.compareHunterAttributes(hunter, updatedHunter);
    }
    
    /**
     * Test for delete method of the HunterDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("Hunter delete Test");
        
        Hunter hunter = this.createHunter("Franta", "Voprsalek", "Short description.", "Frantisek");
        hunterDaoImpl.save(hunter);       
        hunterDaoImpl.delete(hunter);
        
        EntityManager em = (EntityManager) ReflectionTestUtils.getField(hunterDaoImpl, "em");
        final Query query = em.createQuery("SELECT h FROM hunter h WHERE id = :id");
        query.setParameter("id", hunter.getId());
        
        Hunter newHunter = null;
        try{
            newHunter = (Hunter)query.getSingleResult();
        } catch (Exception e){
        }
        
        assertNull(newHunter);
    }
    
    /**
     * Test for find method of the HunterDaoImpl.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("Mushroom findById test");
        
        Hunter hunter1 = this.createHunter("Franta", "Voprsalek", "Short description.", "Frantisek");
        Hunter hunter2 = this.createHunter("Rodan", "Tapetovic", "Short description 2.", "Rod");
        Hunter hunter3 = this.createHunter("Hynek", "Matyas", "Short description 2.", "Matej");
        
        hunterDaoImpl.save(hunter1);
        hunterDaoImpl.save(hunter2);
        hunterDaoImpl.save(hunter3);
        
        Hunter searchedHunter = hunterDaoImpl.find(hunter2.getId());
        this.compareHunterAttributes(hunter2, searchedHunter);
    }
    
    /**
     * Test for findByName method of the HunterDaoImpl.
     */
    @Test
    public void testFindByName() throws Exception {
        System.out.println("Hunter findByName test");
        
        Hunter hunter1 = this.createHunter("Franta", "Voprsalek", "Short description.", "Frantisek");
        Hunter hunter2 = this.createHunter("Rodan", "Tapetovic", "Short description 2.", "Rod");
        Hunter hunter3 = this.createHunter("Hynek", "Matyas", "Short description 2.", "Matej");
        
        hunterDaoImpl.save(hunter1);
        hunterDaoImpl.save(hunter2);
        hunterDaoImpl.save(hunter3);
        
        List<Hunter> searchedHunters = hunterDaoImpl.findByName(hunter2.getFirstName());
        for (int i = 0; searchedHunters.size() > i; i++){
            assertEquals(hunter2.getFirstName(), searchedHunters.get(i).getFirstName());
            i++;
        }
    }
    
    /**
     * Test for findBySurname method of the HunterDaoImpl.
     */
    @Test
    public void testFindSurname() throws Exception {
        System.out.println("Hunter findBySurname test");
        
        Hunter hunter1 = this.createHunter("Franta", "Voprsalek", "Short description.", "Frantisek");
        Hunter hunter2 = this.createHunter("Rodan", "Tapetovic", "Short description 2.", "Rod");
        Hunter hunter3 = this.createHunter("Hynek", "Matyas", "Short description 2.", "Matej");
        
        hunterDaoImpl.save(hunter1);
        hunterDaoImpl.save(hunter2);
        hunterDaoImpl.save(hunter3);
        
        List<Hunter> searchedHunters = hunterDaoImpl.findBySurname(hunter2.getSurname());
        for (int i = 0; searchedHunters.size() > i; i++){
            assertEquals(hunter2.getSurname(), searchedHunters.get(i).getSurname());
            i++;
        }
    }
    
    /**
     * Test for findAll method of the HunterDaoImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("Hunter findAll test");
        
        Hunter hunter1 = this.createHunter("Franta", "Voprsalek", "Short description.", "Frantisek");
        Hunter hunter2 = this.createHunter("Rodan", "Tapetovic", "Short description 2.", "Rod");
        Hunter hunter3 = this.createHunter("Hynek", "Matyas", "Short description 2.", "Matej");

        hunterDaoImpl.save(hunter1);
        hunterDaoImpl.save(hunter2);
        hunterDaoImpl.save(hunter3);
        
        List<Hunter> expectedHunterList = new ArrayList<>();
        
        expectedHunterList.add(hunter1);
        expectedHunterList.add(hunter2);
        expectedHunterList.add(hunter3);
        
        List<Hunter> storedHunterList = hunterDaoImpl.findAll();
        
        assertEquals(expectedHunterList.size(), storedHunterList.size());
        for (int i = 0; expectedHunterList.size() > i && storedHunterList.size() > i; i++){
            this.compareHunterAttributes(expectedHunterList.get(i), storedHunterList.get(i));
            i++;
        }
    }
}
