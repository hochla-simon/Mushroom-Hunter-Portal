package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.converter.HunterConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.LocationConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.MushroomConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.MushroomDao;
import cz.fi.muni.pa165.mushroomhunter.dto.MushroomDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Type;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roman Smékal
 */
@Component
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

    @Transactional
    @Override
    public long save(MushroomDto mushroomDto) {
        try {
            return mushroomDao.save(mushroomConverter.mushroomDtoToEntity(mushroomDto));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error while saving mushroom.", e);
        }
    }

    @Transactional
    @Override
    public MushroomDto update(MushroomDto mushroomDto) {
        try {
            return mushroomConverter.mushroomEntityToDto(mushroomDao.update(mushroomConverter.mushroomDtoToEntity(mushroomDto)));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error while updating mushroom.", e);
        }
    }

    @Transactional
    @Override
    public void delete(MushroomDto mushroomDto) {
        try {
            mushroomDao.delete(mushroomConverter.mushroomDtoToEntity(mushroomDto));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error while deleting mushroom.", e);
        }
    }

    @Transactional
    @Override
    public MushroomDto find(long id) {
        try {
            return mushroomConverter.mushroomEntityToDto(mushroomDao.find(id));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error while retrieving the mushroom by ID.", e);
        }
    }

    @Transactional
    @Override
    public List<MushroomDto> findByName(String name) {
        try {
            return mushroomConverter.mushroomEntityToDtoList(mushroomDao.findByName(name));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error while retrieving the mushroom by name.", e);
        }
    }

    @Transactional
    @Override
    public List<MushroomDto> findByLocation(LocationDto locationDto) {
        try {
            return mushroomConverter.mushroomEntityToDtoList(mushroomDao.findByLocation(locationConverter.locationDtoToEntity(locationDto)));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error while retrieving the mushroom by location.", e);
        }
    }

    @Transactional
    @Override
    public List<MushroomDto> findByType(Type type) {
        try {
            return mushroomConverter.mushroomEntityToDtoList(mushroomDao.findByType(type));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error while retrieving the mushroom by type.", e);
        }
    }
    
    @Transactional
    @Override
    public List<MushroomDto> findByOccurenceDate(Date startOfOccurence, Date endOfOccurence) {
        try {
            return mushroomConverter.mushroomEntityToDtoList(mushroomDao.findByOccurenceDate(startOfOccurence, endOfOccurence));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error while retrieving the mushroom by occurence date.", e);
        }
    }

    @Transactional
    @Override
    public List<MushroomDto> findAll() {
        try {
            return mushroomConverter.mushroomEntityToDtoList(mushroomDao.findAll());
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error while all mushrooms.", e);
        }
    }
}
