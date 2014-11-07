/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.converter.HunterConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.HunterDao;
import cz.fi.muni.pa165.mushroomhunter.dto.HunterDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

/**
 *
 * @author RaD
 */
@Service
public class HunterServiceImpl implements HunterService{

    @Autowired
    private HunterDao hunterDao;
    @Autowired
    private HunterConverter hunterConverter;
    
    @Override
    public long save(HunterDto hunterDto) {
        if (hunterDto == null) {
            throw new NullPointerException();
        }
        try{
        //return hunterDao.save(mapper.map(hunterDto, Hunter.class));
         return  hunterDao.save(hunterConverter.hunterDtoToEntity(hunterDto));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error saving data.", e);
        }
        
    }

    @Override
    public HunterDto update(HunterDto hunterDto) {
         if (hunterDto == null) {
            throw new NullPointerException();
        }
        try {
            return hunterConverter.hunterEntityToDto(hunterDao.update
                (hunterConverter.hunterDtoToEntity(hunterDto)));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error updating data.", e);
        }
    }

    @Override
    public void delete(HunterDto hunterDto) {
         if (hunterDto == null) {
            throw new NullPointerException();
        }
     try {
            hunterDao.delete(hunterConverter.hunterDtoToEntity(hunterDto));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error deleting data.", e);
        }    
    }

    @Override
    public HunterDto find(long id) {
        try {
            return hunterConverter.hunterEntityToDto(hunterDao.find(id));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error retrieving data.", e);
        }
    }

    @Override
    public List<HunterDto> findByName(String firstName) {
         try {
            return hunterConverter.hunterEntityToDtoList(hunterDao.findByName(firstName));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error retrieving data.", e);
        }
    }

    @Override
    public List<HunterDto> findBySurname(String surname) {
         try {
            return hunterConverter.hunterEntityToDtoList(hunterDao.findBySurname(surname));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error retrieving data.", e);
        }
    }

    @Override
    public List<HunterDto> findByNick(String nick) {
         try {
            return hunterConverter.hunterEntityToDtoList(hunterDao.findByNick(nick));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error retrieving data.", e);
        }
    }

    @Override
    public List<HunterDto> findAll() {
         try {
            return hunterConverter.hunterEntityToDtoList(hunterDao.findAll());
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error retrieving data.", e);
        }
    }
    
}
