package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.api.service.LocationService;
import cz.fi.muni.pa165.mushroomhunter.converter.LocationConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.MushroomConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.LocationDao;
import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.MushroomDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Simon Hochla
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationDao locationDao;
    @Autowired
    LocationConverter locationConverter;
    @Autowired
    MushroomConverter mushroomConverter;

    public LocationServiceImpl() {
    }
    
    @Transactional
    @Override
    public long save(LocationDto locationDto) {
        long id = locationDao.save(locationConverter.locationDtoToEntity(locationDto));
        locationDto.setId(id);
        return id;
    }
    
    @Transactional
    @Override
    public LocationDto update(LocationDto locationDto) {
        return locationConverter.locationEntityToDto(locationDao.update(locationConverter.locationDtoToEntity(locationDto)));
    }

    @Transactional
    @Override
    public void delete(LocationDto locationDto) {
            locationDao.delete(locationConverter.locationDtoToEntity(locationDto));
    }
    
    @Transactional
    @Override
    public LocationDto find(long id) {
            return locationConverter.locationEntityToDto(locationDao.find(id));
    }
        
    @Transactional
    @Override
    public List<LocationDto> findByOccurenceWithSumOfMushrooms(boolean ascending) {
        return locationConverter.locationEntityMapToDto(locationDao.findByOccurenceWithSumOfMushrooms(ascending));
    }
    
    @Transactional
    @Override
    public List<LocationDto> findAll() {
            return locationConverter.locationEntityToDtoList(locationDao.findAll());
    }
}
