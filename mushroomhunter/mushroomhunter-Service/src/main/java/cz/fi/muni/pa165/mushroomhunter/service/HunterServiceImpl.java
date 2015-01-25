package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.api.service.HunterService;
import cz.fi.muni.pa165.mushroomhunter.converter.HunterConverter;

import cz.fi.muni.pa165.mushroomhunter.dao.HunterDao;
import cz.fi.muni.pa165.mushroomhunter.api.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.dao.HunterRoleDao;
import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import cz.fi.muni.pa165.mushroomhunter.entity.HunterRole;
import java.util.ArrayList;
import static java.util.Collections.list;
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
    private HunterRoleDao hunterRoleDao;
    @Autowired
    private HunterConverter hunterConverter;
    
    @Transactional
    @Override
    public long save(HunterDto hunterDto) {
        if (hunterDto == null) {
            throw new NullPointerException();
        }
        HunterRole hunterRole = new HunterRole();
        Hunter hunter = hunterConverter.hunterDtoToEntity(hunterDto);
        hunterRole.setHunter(hunter);
        hunterRole.setRole(hunterDto.getRole());
        hunterRoleDao.save(hunterRole);
        return hunterDao.save(hunter);

    }
    
    @Transactional
    @Override
    public HunterDto update(HunterDto hunterDto) {
        if (hunterDto == null) {
            throw new NullPointerException();
        }
        HunterRole hunterRole = new HunterRole();
        Hunter hunter = hunterConverter.hunterDtoToEntity(hunterDto);
        hunterRole.setHunter(hunter);
        hunterRole.setRole(hunterDto.getRole());
        hunterRoleDao.update(hunterRole);
        return hunterConverter.hunterEntityToDto(hunterDao.update(hunter));
    }
    
    @Transactional
    @Override
    public void delete(HunterDto hunterDto) {
        if (hunterDto == null) {
            throw new NullPointerException();
        }
        Hunter hunter = hunterConverter.hunterDtoToEntity(hunterDto);
        HunterRole hunterRole = hunterRoleDao.findByHunter(hunter);
        hunterRoleDao.delete(hunterRole);
        hunterDao.delete(hunter);
    }
    
    @Transactional
    @Override
    public HunterDto find(long id) {
        Hunter hunter = hunterDao.find(id);
        HunterRole hunterRole = hunterRoleDao.findByHunter(hunter);
        HunterDto hunterDto = hunterConverter.hunterEntityToDto(hunter);
        hunterDto.setRole(hunterRole.getRole());
        return hunterDto;
    }
    
    
    @Transactional
    @Override
    public List<HunterDto> findAll() {
        List<Hunter> hunterList = hunterDao.findAll();
        List<HunterDto> resultList = new ArrayList();
        for (Hunter hunter: hunterList) {
            HunterDto hunterDto = hunterConverter.hunterEntityToDto(hunter);
            HunterRole hunterRole = hunterRoleDao.findByHunter(hunter);
            hunterDto.setRole(hunterRole.getRole());
            resultList.add(hunterDto);
        }
        return resultList;
    }
    
    @Transactional
    @Override
    public HunterDto findByNick(String nick) {
         Hunter hunter = hunterDao.findByNick(nick);
        HunterRole hunterRole = hunterRoleDao.findByHunter(hunter);
        HunterDto hunterDto = hunterConverter.hunterEntityToDto(hunter);
        hunterDto.setRole(hunterRole.getRole());
        return hunterDto;
    }
}
