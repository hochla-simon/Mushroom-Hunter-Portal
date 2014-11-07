/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.Service;

import cz.fi.muni.pa165.mushroomhunter.converter.LocationConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.MushroomConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.LocationDaoImpl;
import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.dto.MushroomDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Type;
import cz.fi.muni.pa165.mushroomhunter.service.LocationServiceImpl;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import static org.mockito.Mockito.verify;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Simon
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class LocationServiceImplTest {
    
    @InjectMocks
    LocationServiceImpl lsi;
    @Mock
    LocationDaoImpl locationDao;
    @Mock
    LocationConverter lc = new LocationConverter();
    @Mock
    MushroomConverter mc = new MushroomConverter();
    public LocationServiceImplTest() {
    }
    
    /**
     * Test of save method, of class LocationServiceImpl.
     */
    @Test
    public void testSave() {
       LocationDto locationDto = this.createLocation();
       lsi.save(locationDto);
       verify(locationDao).save(lc.locationDtoToEntity(locationDto));
    }

    /**
     * Test of update method, of class LocationServiceImpl.
     */
    @Test
    public void testUpdate() {
        LocationDto locationDto = this.createLocation();
        lsi.delete(locationDto);
        verify(locationDao).delete(lc.locationDtoToEntity(locationDto));
    }

    /**
     * Test of delete method, of class LocationServiceImpl.
     */
    @Test
    public void testDelete() {
       LocationDto locationDto = this.createLocation();
        lsi.update(locationDto);
        verify(locationDao).update(lc.locationDtoToEntity(locationDto));
    }

    /**
     * Test of find method, of class LocationServiceImpl.
     */
    @Test
    public void testFind() {
       LocationDto locationDto = this.createLocation();
       lsi.save(locationDto);
       lsi.find(locationDto.getId());
       verify(locationDao).find(locationDto.getId());
    }

    /**
     * Test of findByNearCity method, of class LocationServiceImpl.
     */
    @Test
    public void testFindByNearCity() {
       lsi.findByNearCity("Brno");
       verify(locationDao).findByNearCity("Brno");
    }

    /**
     * Test of findByMushroom method, of class LocationServiceImpl.
     */
    @Test
    public void testFindByMushroom() {
        LocationDto locationDto = this.createLocation();
         MushroomDto mushroomDto = this.createMushroom("Mochomurka", new Date(1, 4, 1), new Date(1, 8, 1), Type.POISONOUS);
        lsi.findByMushroom(mushroomDto);
        verify(locationDao).findByMushroom(mc.mushroomDtoToEntity(mushroomDto));
    }

    /**
     * Test of findByOccurence method, of class LocationServiceImpl.
     */
    @Test
    public void testFindByOccurence() {
        lsi.findByOccurence(true);
        verify(locationDao).findByOccurence(true);
    }

    /**
     * Test of findAll method, of class LocationServiceImpl.
     */
    @Test
    public void testFindAll() {
       lsi.findAll();
       verify(locationDao).findAll();
    }
    
    public LocationDto createLocation() {
        LocationDto locationDto = new LocationDto();
        
        locationDto.setDescription("desc");
        locationDto.setName("name");
        locationDto.setNearCity("city");
        
        return locationDto;
    }
    
     public MushroomDto createMushroom(String name, Date startOfOcc, Date enDate, Type type) {
        MushroomDto m = new MushroomDto();
        m.setName(name);
        m.setStartOfOccurence(startOfOcc);
        m.setEndOfOccurence(enDate);
        m.setType(type);
        return m;
    }
}