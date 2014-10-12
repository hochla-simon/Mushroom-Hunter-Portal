/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.DaoContext;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.jpa.internal.util.PersistenceUtilHelper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 *
 * @author Radim Cejka
 */

@ContextConfiguration(classes=DaoContext.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)

public class LocationDaoImplTest extends AbstractTestNGSpringContextTests
{
	@PersistenceUnit
	public EntityManagerFactory emf;
	//private long locId;
        private LocationDaoImpl locDimpl;
        private EntityManager em;
	 
   @BeforeMethod
	public void setup(){
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
                locDimpl = new LocationDaoImpl();
                
                Location location = new Location();
                location.setDescription("Under big spruce on blue tourinst way");
                location.setNearCity("Jedlova");
                location.setName("Pod smrkem");
        
                em.persist(location);
                em.getTransaction().commit();
                
                /*
                Calendar cal = Calendar.getInstance();
		
		Pet pet = new Pet();
		pet.setName("FILIP");		
		pet.setBirthDate(cal.getTime());
		pet.setColor(PetColor.WHITE);
		
		
		em.persist(pet);
		pet1Id = pet.getId();
		em.getTransaction().commit();
                */
		em.close();
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

        Location savedLocation = em.find(Location.class, location.getId());
        assertNotNull(savedLocation.getId());
        assertEquals(location.getId(), savedLocation.getId());
        
       
        em.remove(location);
        }

    /**
     * Test of update method, of class LocationDaoImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Location location = null;
        /*
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        LocationDao instance = (LocationDao)container.getContext().lookup("java:global/classes/LocationDaoImpl");
        Location expResult = null;
        Location result = instance.update(location);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of delete method, of class LocationDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Location location = null;
        /*
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        LocationDao instance = (LocationDao)container.getContext().lookup("java:global/classes/LocationDaoImpl");
        instance.delete(location);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of find method, of class LocationDaoImpl.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        long id = 0L;
        /*
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        LocationDao instance = (LocationDao)container.getContext().lookup("java:global/classes/LocationDaoImpl");
        Location expResult = null;
        Location result = instance.find(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of findByNearCity method, of class LocationDaoImpl.
     */
    @Test
    public void testFindByNearCity() throws Exception {
        System.out.println("findByNearCity");
        String nearCity = "";
        /*
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        LocationDao instance = (LocationDao)container.getContext().lookup("java:global/classes/LocationDaoImpl");
        List<Location> expResult = null;
        List<Location> result = instance.findByNearCity(nearCity);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of findByMushroom method, of class LocationDaoImpl.
     */
    @Test
    public void testFindByMushroom() throws Exception {
        System.out.println("findByMushroom");
        Mushroom mushroom = null;
        /*
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        LocationDao instance = (LocationDao)container.getContext().lookup("java:global/classes/LocationDaoImpl");
        List<Location> expResult = null;
        List<Location> result = instance.findByMushroom(mushroom);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of findByOccurence method, of class LocationDaoImpl.
     */
    @Test
    public void testFindByOccurence() throws Exception {
        System.out.println("findByOccurence");
        /*
        boolean ascending = false;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        LocationDao instance = (LocationDao)container.getContext().lookup("java:global/classes/LocationDaoImpl");
        List<Location> expResult = null;
        List<Location> result = instance.findByOccurence(ascending);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of findAll method, of class LocationDaoImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        /*
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        LocationDao instance = (LocationDao)container.getContext().lookup("java:global/classes/LocationDaoImpl");
        List<Location> expResult = null;
        List<Location> result = instance.findAll();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }
    
}
