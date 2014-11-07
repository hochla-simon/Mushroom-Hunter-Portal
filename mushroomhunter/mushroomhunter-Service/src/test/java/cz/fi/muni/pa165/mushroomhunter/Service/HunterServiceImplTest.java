/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.Service;

import cz.fi.muni.pa165.mushroomhunter.dao.HunterDao;
import cz.fi.muni.pa165.mushroomhunter.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.converter.HunterConverter;
import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import cz.fi.muni.pa165.mushroomhunter.service.HunterService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Radim Cejka
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class HunterServiceImplTest {
   
    private HunterDto hunterDto;
    @Autowired
    private HunterService hunterService;
    @Autowired
    private HunterConverter hunterConverter;
   
    HunterDao hunterDao;
    
    public HunterServiceImplTest() {
    }
    
    @Before
    public void setUp() {
        hunterDto = new HunterDto();
        hunterDto.setId(new Long(42));
        hunterDto.setFirstName("Franta");
        hunterDto.setSurname("Voprsalek");
        hunterDto.setDescription("Short description.");
        hunterDto.setNick("Frantisek");
        
        hunterDao = mock(HunterDao.class);
        hunterService.setDao(hunterDao);
        /*   
     * 
        hunterService = new HunterServiceImpl();
        hunterConverter = new HunterConverter();
        * */
    }
    
    @After
    public void tearDown() {
        /*
        hunterDto = null;
        hunterService = null;
        * */
    }

    /**
     * Test of save method, of class HunterServiceImpl.
     */
    @Test (expected = NullPointerException.class)
    public void testSave() {
        Hunter h = hunterConverter.hunterDtoToEntity(hunterDto);
        hunterService.save(hunterDto);
        verify(hunterDao).save(hunterConverter.hunterDtoToEntity(hunterDto));
        //doThrow(DataRetrievalFailureException.class).when(hunterDao).save(any(Hunter.class));
        hunterService.save(null);
    }

    /**
     * Test of update method, of class HunterServiceImpl.
     */
    @Test
    public void testUpdate() {
        Hunter hunter = hunterConverter.hunterDtoToEntity(hunterDto);
        when(hunterDao.update(hunter)).thenReturn(hunter);
        hunterService.update(hunterDto);
        verify(hunterDao).update(hunterConverter.hunterDtoToEntity(hunterDto));
    }

    /**
     * Test of delete method, of class HunterServiceImpl.
     */
    @Test
    public void testDelete() {
        ArgumentCaptor<Hunter> captor = ArgumentCaptor.forClass(Hunter.class);
        hunterService.delete(hunterDto);
        verify(hunterDao).delete(captor.capture());
        assertEquals(hunterConverter.hunterDtoToEntity(hunterDto), captor.getValue());
    }

    /**
     * Test of find method, of class HunterServiceImpl.
     */
    @Test
    public void testFind() {
        Hunter hunter = hunterConverter.hunterDtoToEntity(hunterDto);
        when(hunterDao.find(42)).thenReturn(hunter);
        HunterDto foundHunter = hunterService.find(42);
        assertEquals(hunter, hunterConverter.hunterDtoToEntity(foundHunter));
    }

    /**
     * Test of findByName method, of class HunterServiceImpl.
     */
    @Test
    public void testFindByName() {
        Hunter hunter2 = new Hunter();
        hunter2.setId(new Long(22));
        hunter2.setFirstName("Franta");
        hunter2.setSurname("Voprsalek");
        hunter2.setDescription("Short description.");
        hunter2.setNick("Frantisek");
        HunterDto hunter2Dto = hunterConverter.hunterEntityToDto(hunter2);
        Hunter hunter = hunterConverter.hunterDtoToEntity(hunterDto);
        
        when(hunterDao.findByName("Franta")).thenReturn(new ArrayList((Collection)Arrays.asList(hunter,hunter2)));
        List<HunterDto> foundHunter = hunterService.findByName("Franta");
        assertEquals(foundHunter.size(), 2);
        assertEquals(foundHunter.get(0), hunterDto);
        assertEquals(foundHunter.get(1), hunter2Dto);
    }

    /**
     * Test of findBySurname method, of class HunterServiceImpl.
     */
    @Test
    public void testFindBySurname() {
        Hunter hunter2 = new Hunter();
        hunter2.setId(new Long(22));
        hunter2.setFirstName("Franta");
        hunter2.setSurname("Voprsalek");
        hunter2.setDescription("Short description.");
        hunter2.setNick("Frantisek");
        HunterDto hunter2Dto = hunterConverter.hunterEntityToDto(hunter2);
        Hunter hunter = hunterConverter.hunterDtoToEntity(hunterDto);
        
        when(hunterDao.findBySurname("Voprsalek")).thenReturn(new ArrayList((Collection)Arrays.asList(hunter,hunter2)));
        List<HunterDto> foundHunter = hunterService.findBySurname("Voprsalek");
        assertEquals(foundHunter.size(), 2);
        assertEquals(foundHunter.get(0), hunterDto);
        assertEquals(foundHunter.get(1), hunter2Dto);
    }

    /**
     * Test of findByNick method, of class HunterServiceImpl.
     */
    @Test
    public void testFindByNick() {
        Hunter hunter2 = new Hunter();
        hunter2.setId(new Long(22));
        hunter2.setFirstName("Franta");
        hunter2.setSurname("Voprsalek");
        hunter2.setDescription("Short description.");
        hunter2.setNick("Frantisek");
        HunterDto hunter2Dto = hunterConverter.hunterEntityToDto(hunter2);
        Hunter hunter = hunterConverter.hunterDtoToEntity(hunterDto);
        
        when(hunterDao.findByNick("Frantisek")).thenReturn(new ArrayList((Collection)Arrays.asList(hunter,hunter2)));
        List<HunterDto> foundHunter = hunterService.findByNick("Frantisek");
        assertEquals(foundHunter.size(), 2);
        assertEquals(foundHunter.get(0), hunterDto);
        assertEquals(foundHunter.get(1), hunter2Dto);
    }

    /**
     * Test of findAll method, of class HunterServiceImpl.
     */
    @Test
    public void testFindAll() {
        Hunter hunter2 = new Hunter();
        hunter2.setId(new Long(22));
        hunter2.setFirstName("Franta");
        hunter2.setSurname("Voprsalek");
        hunter2.setDescription("Short description.");
        hunter2.setNick("Frantisek");
        HunterDto hunter2Dto = hunterConverter.hunterEntityToDto(hunter2);
        Hunter hunter = hunterConverter.hunterDtoToEntity(hunterDto);
        
        when(hunterDao.findAll()).thenReturn(new ArrayList((Collection)Arrays.asList(hunter,hunter2)));
        List<HunterDto> foundHunter = hunterService.findAll();
        assertEquals(foundHunter.size(), 2);
        assertEquals(foundHunter.get(0), hunterDto);
        assertEquals(foundHunter.get(1), hunter2Dto);
    }
}