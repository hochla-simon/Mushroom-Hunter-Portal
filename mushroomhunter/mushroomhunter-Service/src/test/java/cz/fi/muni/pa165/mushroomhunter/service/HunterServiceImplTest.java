/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.api.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.converter.HunterConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.HunterDaoImpl;
import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Radim Cejka
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class HunterServiceImplTest {
   
   
    private HunterDto hunterDto;
    //@Autowired
    @InjectMocks
    private HunterServiceImpl hunterService;
    //@Autowired
    @Mock
    private HunterConverter hunterConverter = new HunterConverter();
    @Mock
    HunterDaoImpl hunterDao;
    
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
        hunterDto.setRole("ROLE_USER");
    }
   

    /**
     * Test of save method, of class HunterServiceImpl.
     */
    @Test (expected = NullPointerException.class)
    public void testSave() {
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
        hunterService.find(hunterDto.getId());
        verify(hunterDao).find(hunterDto.getId());
    }

    /**
     * Test of findAll method, of class HunterServiceImpl.
     */
    @Test
    public void testFindAll() {
        hunterService.findAll();
        verify(hunterDao).findAll();
    }
}