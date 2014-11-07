package cz.fi.muni.pa165.mushroomhunter.Service;

import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.dto.MushroomDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Type;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roman Smékal
 */
public interface MushroomService {

    @Transactional
    void delete(MushroomDto mushroomDto);

    @Transactional
    MushroomDto find(long id);

    @Transactional
    List<MushroomDto> findAll();

    @Transactional
    List<MushroomDto> findByLocation(LocationDto locationDto);

    @Transactional
    List<MushroomDto> findByName(String name);

    @Transactional
    List<MushroomDto> findByOccurenceDate(Date startOfOccurence, Date endOfOccurence);

    @Transactional
    List<MushroomDto> findByType(Type type);

    @Transactional
    long save(MushroomDto mushroomDto);

    @Transactional
    MushroomDto update(MushroomDto mushroomDto);
    
}
