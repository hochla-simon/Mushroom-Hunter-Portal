/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.Service;

import cz.fi.muni.pa165.mushroomhunter.dto.VisitDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cz.fi.muni.pa165.mushroomhunter.service.VisitServiceImpl;

/**
 *
 * @author Lukáš Valach
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class VisitServiceImplTest {

    @Autowired
    VisitServiceImpl vsi;

    public VisitServiceImplTest() {
    }

//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of saveVisit method, of class VisitServiceImpl.
     */
    @Test
    public void testSaveVisit() {
        vsi.saveVisit(new VisitDto());
    }

    /**
     * Test of deleteVisit method, of class VisitServiceImpl.
     */
    @Test
    public void testDeleteVisit() {
    }

    /**
     * Test of updateVisit method, of class VisitServiceImpl.
     */
    @Test
    public void testUpdateVisit() {
    }

    /**
     * Test of findAllVisits method, of class VisitServiceImpl.
     */
    @Test
    public void testFindAllVisits() {
    }

    /**
     * Test of findVisitByLocation method, of class VisitServiceImpl.
     */
    @Test
    public void testFindVisitByLocation() {
    }

    /**
     * Test of findVisitByHunter method, of class VisitServiceImpl.
     */
    @Test
    public void testFindVisitByHunter() {
    }

}
