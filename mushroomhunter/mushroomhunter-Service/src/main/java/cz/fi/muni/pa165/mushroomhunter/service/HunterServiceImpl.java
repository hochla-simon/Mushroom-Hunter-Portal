package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.api.service.HunterService;
import cz.fi.muni.pa165.mushroomhunter.converter.HunterConverter;

import cz.fi.muni.pa165.mushroomhunter.dao.HunterDao;
import cz.fi.muni.pa165.mushroomhunter.api.dto.HunterDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Radim Cejka
 */
@Service
public class HunterServiceImpl implements HunterService {

    @Autowired
    private HunterDao hunterDao;
    @Autowired
    private HunterConverter hunterConverter;
    
    @Transactional
    @Override
    public long save(HunterDto hunterDto) {
        if (hunterDto == null) {
            throw new NullPointerException();
        }

        //return hunterDao.save(mapper.map(hunterDto, Hunter.class));
        return hunterDao.save(hunterConverter.hunterDtoToEntity(hunterDto));

    }
    
    @Transactional
    @Override
    public HunterDto update(HunterDto hunterDto) {
        if (hunterDto == null) {
            throw new NullPointerException();
        }
        return hunterConverter.hunterEntityToDto(hunterDao.update(hunterConverter.hunterDtoToEntity(hunterDto)));
    }
    
    @Transactional
    @Override
    public void delete(HunterDto hunterDto) {
        if (hunterDto == null) {
            throw new NullPointerException();
        }
        hunterDao.delete(hunterConverter.hunterDtoToEntity(hunterDto));
    }
    
    @Transactional
    @Override
    public HunterDto find(long id) {
        return hunterConverter.hunterEntityToDto(hunterDao.find(id));
    }
    
    @Transactional
    @Override
    public List<HunterDto> findByName(String firstName) {
        if (firstName == null) {
            throw new NullPointerException();
        }
        return hunterConverter.hunterEntityToDtoList(hunterDao.findByName(firstName));
    }
    
    @Transactional
    @Override
    public List<HunterDto> findBySurname(String surname) {
        if (surname == null) {
            throw new NullPointerException();
        }
        return hunterConverter.hunterEntityToDtoList(hunterDao.findBySurname(surname));
    }
    
    @Transactional
    @Override
    public List<HunterDto> findByNick(String nick) {
        if (nick == null) {
            throw new NullPointerException();
        }
        return hunterConverter.hunterEntityToDtoList(hunterDao.findByNick(nick));
    }
    
    @Transactional
    @Override
    public List<HunterDto> findAll() {
        return hunterConverter.hunterEntityToDtoList(hunterDao.findAll());
    }

}
