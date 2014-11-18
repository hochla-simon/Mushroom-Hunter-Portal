/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.Service;

import cz.fi.muni.pa165.mushroomhunter.converter.HunterConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.LocationConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.VisitConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.VisitDaoImpl;
import cz.fi.muni.pa165.mushroomhunter.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.dto.VisitDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import cz.fi.muni.pa165.mushroomhunter.service.VisitServiceImpl;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.verify;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Lukáš Valach
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class VisitServiceImplTest {

    @Autowired
    @InjectMocks
    VisitServiceImpl vsi;
    @Mock
    VisitDaoImpl visitDao;
    @Mock
    VisitConverter vc = new VisitConverter();
    @Mock
    HunterConverter hc = new HunterConverter();
    @Mock
    LocationConverter lc = new LocationConverter();

    public VisitServiceImplTest() {
    }
    
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
        VisitDto visitDto = this.createVisit();
        vsi.saveVisit(visitDto);
        verify(visitDao).save(vc.visitDtoToEntity(visitDto));
    }

    /**
     * Test of deleteVisit method, of class VisitServiceImpl.
     */
    @Test
    public void testDeleteVisit() {
        VisitDto visitDto = this.createVisit();
        vsi.deleteVisit(visitDto);
        verify(visitDao).delete(vc.visitDtoToEntity(visitDto));
    }

    /**
     * Test of updateVisit method, of class VisitServiceImpl.
     */
    @Test
    public void testUpdateVisit() {
        VisitDto visitDto = this.createVisit();
        vsi.updateVisit(visitDto);
        verify(visitDao).update(vc.visitDtoToEntity(visitDto));
    }

    /**
     * Test of findAllVisits method, of class VisitServiceImpl.
     */
    @Test
    public void testFindAllVisits() {
        vsi.findAllVisits();
        verify(visitDao).findAll();
    }

    /**
     * Test of findVisitByLocation method, of class VisitServiceImpl.
     */
    @Test
    public void testFindVisitByLocation() {
        LocationDto locationDto = this.createLocation();
        vsi.findVisitByLocation(locationDto);
        verify(visitDao).findByLocation(lc.locationDtoToEntity(locationDto));
    }

    /**
     * Test of findVisitByHunter method, of class VisitServiceImpl.
     */
    @Test
    public void testFindVisitByHunter() {
        HunterDto hunterDto = this.createHunter();
        vsi.findVisitByHunter(hunterDto);
        verify(visitDao).findByHunter(hc.hunterDtoToEntity(hunterDto));
    }

    public VisitDto createVisit() {
        HunterDto hunter = this.createHunter("Pepa", "Novák", "Pepa je starý houbař.", "PepaN");
        LocationDto location = this.createLocation("LesŘáholec", "Les Řáholec, kde lišky dávají dobrou noc.", "Jičín");
        Map<Long, Integer> mushroomMap = this.createMushroomMap();
        Date date = new Date(2000, 1, 1);
        return this.createVisit(hunter, location, date, mushroomMap);
    }

    public VisitDto createVisit(HunterDto hunter, LocationDto location, Date date, Map<Long, Integer> foundMushrooms) {
        VisitDto visitDto = new VisitDto();
        visitDto.setDate(date);
        visitDto.setHunter(hunter);
        visitDto.setFoundMushrooms(this.createMushroomMap());
        visitDto.setLocation(location);
        return visitDto;
    }

    /**
     * A method used to create the Map of Long-Integer to simulate map
     * mushroom-quantity.
     *
     * @return Created map of Long-Integer
     */
    public Map<Long, Integer> createMushroomMap() {
        Map map = new HashMap<Mushroom, Integer>();
        map.put(1L, 1);
        map.put(2L, 2);
        map.put(3L, 3);
        return map;
    }

    /**
     * A method used to create the Location's DTO object.
     *
     * @return Created location with some default attributes.
     */
    public LocationDto createLocation() {
        return this.createLocation("LesŘáholec", "Les Řáholec, kde lišky dávají dobrou noc.", "Jičín");
    }

    /**
     * A method used to create the Location's DTO object.
     *
     * @param name Location's name string.
     * @param description Location's description.
     * @param nearCity Name of city near to location.
     * @return Created location with the given attributes.
     */
    public LocationDto createLocation(String name, String description, String nearCity) {
        LocationDto locationDto = new LocationDto();
        locationDto.setName(name);
        locationDto.setDescription(description);
        locationDto.setNearCity(nearCity);
        return locationDto;
    }

    /**
     * A method used to create the Hunter's DTO object.
     *
     * @return Created hunter with some default attributes.
     */
    public HunterDto createHunter() {
        return this.createHunter("Pepa", "Novák", "Pepa je starý houbař.", "PepaN");
    }

    /**
     * A method used to create the Hunter's entity object.
     *
     * @param firstName Hunter's first name String.
     * @param surname Hunter's surname String.
     * @param description Hunter's description String.
     * @param nick Hunter's nick String.
     * @return Created hunter with the given attributes.
     */
    public HunterDto createHunter(String firstName, String surname, String description, String nick) {
        HunterDto h = new HunterDto();
        h.setFirstName(firstName);
        h.setSurname(surname);
        h.setNick(nick);
        h.setDescription(description);
        return h;
    }
}
