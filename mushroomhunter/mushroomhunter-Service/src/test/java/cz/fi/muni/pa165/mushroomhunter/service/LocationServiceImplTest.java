/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.converter.LocationConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.MushroomConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.LocationDaoImpl;
import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.MushroomDto;
import cz.fi.muni.pa165.mushroomhunter.api.Type;
import cz.fi.muni.pa165.mushroomhunter.service.LocationServiceImpl;
import java.util.Date;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Simon Hochla
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
     * Test of findAll method, of class LocationServiceImpl.
     */
    @Test
    public void testFindAll() {
       lsi.findAll();
       verify(locationDao).findAll();
    }
    
    /**
     * A method used to create the Location's DTO object.
     *
     * @param name Location's name string.
     * @param description Location's description.
     * @param nearCity Name of city near to location.
     * @return Created location with the given attributes.
     */
    public LocationDto createLocation() {
        LocationDto locationDto = new LocationDto();
        
        locationDto.setDescription("desc");
        locationDto.setName("name");
        locationDto.setNearCity("city");
        
        return locationDto;
    }
    
     /**
     * A method used to create the Mushroom's DTO object.
     *
     * @param name Mushroom's name string.
     * @param startOfOcc Date Mushroom's start of occurence.
     * @param enDate Date Mushroom's end of occurence.
     * @param type Type Mushroom's type.
     * @return Created mushroom with the given attributes.
     */
     public MushroomDto createMushroom(String name, Date startOfOcc, Date enDate, Type type) {
        MushroomDto m = new MushroomDto();
        m.setName(name);
        m.setStartOfOccurence(startOfOcc);
        m.setEndOfOccurence(enDate);
        m.setType(type);
        return m;
    }
}