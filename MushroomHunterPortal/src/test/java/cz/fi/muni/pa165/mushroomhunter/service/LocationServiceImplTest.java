/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.dto.MushroomDto;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Simon
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class LocationServiceImplTest {
    
    @Autowired
    LocationServiceImpl lsi;
        
    public LocationServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class LocationServiceImpl.
     */
    @Test
    public void testSave() {
       LocationDto location = new LocationDto();
       location.setDescription("desc");
       location.setName("name");
       location.setNearCity("city");
       lsi.save(location);
    }

    /**
     * Test of update method, of class LocationServiceImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
       
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class LocationServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        LocationDto locationDto = null;
        LocationServiceImpl instance = new LocationServiceImpl();
        instance.delete(locationDto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class LocationServiceImpl.
     */
    @Test
    public void testFind() {
        System.out.println("find");
    
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByNearCity method, of class LocationServiceImpl.
     */
    @Test
    public void testFindByNearCity() {
        System.out.println("findByNearCity");
        String nearCity = "";
        LocationServiceImpl instance = new LocationServiceImpl();
        List expResult = null;
        List result = instance.findByNearCity(nearCity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByMushroom method, of class LocationServiceImpl.
     */
    @Test
    public void testFindByMushroom() {
        System.out.println("findByMushroom");
        MushroomDto mushroomDto = null;
        LocationServiceImpl instance = new LocationServiceImpl();
        List expResult = null;
        List result = instance.findByMushroom(mushroomDto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByOccurence method, of class LocationServiceImpl.
     */
    @Test
    public void testFindByOccurence() {
        System.out.println("findByOccurence");
        boolean ascending = false;
        LocationServiceImpl instance = new LocationServiceImpl();
        List expResult = null;
        List result = instance.findByOccurence(ascending);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class LocationServiceImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        LocationServiceImpl instance = new LocationServiceImpl();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}