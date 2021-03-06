package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.api.service.MushroomService;
import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.converter.HunterConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.LocationConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.MushroomConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.MushroomDao;
import cz.fi.muni.pa165.mushroomhunter.api.dto.MushroomDto;
import cz.fi.muni.pa165.mushroomhunter.api.Type;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roman Smékal
 */
@Service
public class MushroomServiceImpl implements MushroomService {

    @Autowired
    MushroomDao mushroomDao;
    @Autowired
    MushroomConverter mushroomConverter;
    @Autowired
    HunterConverter hunterConverter;
    @Autowired
    LocationConverter locationConverter;
    @PersistenceContext
    private EntityManager em;

    public MushroomServiceImpl() {
    }
    
    public MushroomDao getMushroomDao() {
        return mushroomDao;
    }

    public void setMushroomDao(MushroomDao mushroomDao) {
        this.mushroomDao = mushroomDao;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Transactional
    @Override
    public long save(MushroomDto mushroomDto) {
            return mushroomDao.save(mushroomConverter.mushroomDtoToEntity(mushroomDto));
    }

    @Transactional
    @Override
    public MushroomDto update(MushroomDto mushroomDto) {
            return mushroomConverter.mushroomEntityToDto(mushroomDao.update(mushroomConverter.mushroomDtoToEntity(mushroomDto)));
        
    }

    @Transactional
    @Override
    public void delete(MushroomDto mushroomDto) {
            mushroomDao.delete(mushroomConverter.mushroomDtoToEntity(mushroomDto));
    }

    @Transactional
    @Override
    public MushroomDto find(long id) {
            return mushroomConverter.mushroomEntityToDto(mushroomDao.find(id));
    }

   

    @Transactional
    @Override
    public List<MushroomDto> findAll() {
            return mushroomConverter.mushroomEntityToDtoList(mushroomDao.findAll());
    }
}
