package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.converter.HunterConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.LocationConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.MushroomConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.VisitConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.MushroomDaoImpl;
import cz.fi.muni.pa165.mushroomhunter.dao.VisitDaoImpl;
import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.MushroomDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.VisitDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import cz.fi.muni.pa165.mushroomhunter.api.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Roman Smékal
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class MushroomServiceImplTest {
    
    @Autowired
    @InjectMocks
    MushroomServiceImpl mushroomServiceImpl;
    @Mock
    MushroomDaoImpl mushroomDao;
    @Mock
    MushroomConverter mushroomConverter = new MushroomConverter();
    @Mock
    LocationConverter locationConverter = new LocationConverter();


    public MushroomServiceImplTest() {
    }
    
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private MushroomDto createMushroomDto() {
        MushroomDto mushroomDto = new MushroomDto();
        mushroomDto.setName("Mochomurka");
        mushroomDto.setType(Type.POISONOUS);
        mushroomDto.setStartOfOccurence(new Date(1, 4, 1));
        mushroomDto.setEndOfOccurence(new Date(1, 8, 1));
        return mushroomDto;
    }
    
    public Mushroom createMushroom(String name, Date startOfOcc, Date enDate, Type type) {
        Mushroom m = new Mushroom();
        m.setName(name);
        m.setStartOfOccurence(startOfOcc);
        m.setEndOfOccurence(enDate);
        m.setType(type);
        return m;
    }
    
    /**
     * Test of save method, of class MushroomServiceImpl.
     */
    @Test
    public void testSaveMushroom() {
        MushroomDto mushroomDto = this.createMushroomDto();
        mushroomServiceImpl.save(mushroomDto);
        verify(mushroomDao).save(mushroomConverter.mushroomDtoToEntity(mushroomDto));
    }
    
    /**
     * Test of update method, of class MushroomServiceImpl.
     */
    @Test
    public void testUpdateMushroom() {
        MushroomDto mushroomDto = this.createMushroomDto();
        mushroomServiceImpl.update(mushroomDto);
        verify(mushroomDao).update(mushroomConverter.mushroomDtoToEntity(mushroomDto));
    }
    
    /**
     * Test of delete method, of class MushroomServiceImpl.
     */
    @Test
    public void testDeleteMushroom() {
        MushroomDto mushroomDto = this.createMushroomDto();
        mushroomServiceImpl.delete(mushroomDto);
        verify(mushroomDao).delete(mushroomConverter.mushroomDtoToEntity(mushroomDto));
    }
    
    /**
     * Test of find method, of class MushroomServiceImpl.
     */
    @Test
    public void testFindMushroom() {
        MushroomDto mushroomDto = this.createMushroomDto();
        Mushroom mushroom = mushroomConverter.mushroomDtoToEntity(mushroomDto);
        when(mushroomDao.find(Mockito.anyLong())).thenReturn(mushroom);
        MushroomDto foundMushroom = mushroomServiceImpl.find(1L);
        assertEquals(mushroom, mushroomConverter.mushroomDtoToEntity(foundMushroom));  
    }

    /**
     * Test of findAll method, of class VisitServiceImpl.
     */
    @Test
    public void testFindAllMushrooms() {
        mushroomServiceImpl.findAll();
        verify(mushroomDao).findAll();
    }
    
    /**
     * Test of findByName method, of class MushroomServiceImpl.
     */
    @Test
    public void testFindMushroomByName() {
        mushroomServiceImpl.findByName("Mochomurka");
        verify(mushroomDao).findByName("Mochomurka");
    }
    
    
    /**
     * Test of findByOccurenceDate method, of class MushroomServiceImpl.
     */
    @Test
    public void testFindMushroomByOccurenceDate() {
        mushroomServiceImpl.findByOccurenceDate(new Date(1, 4, 1), new Date(2, 8, 1));
        verify(mushroomDao).findByOccurenceDate(new Date(1, 4, 1), new Date(2, 8, 1));
    }
    
    /**
     * Test of findByLocation method, of class MushroomServiceImpl.
     */
    @Test
    public void testFindMushroomByLocation() {
        LocationDto locationDto = new LocationDto();
        locationDto.setDescription("Near by lake, right side of dirty road");
        locationDto.setNearCity("Nova ves");
        locationDto.setName("Hribova u vody");
        mushroomServiceImpl.findByLocation(locationDto);
        verify(mushroomDao).findByLocation(locationConverter.locationDtoToEntity(locationDto));
    }
}
