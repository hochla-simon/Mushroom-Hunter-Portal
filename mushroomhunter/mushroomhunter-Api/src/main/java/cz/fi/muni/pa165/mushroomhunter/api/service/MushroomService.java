package cz.fi.muni.pa165.mushroomhunter.api.service;

import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.MushroomDto;
import cz.fi.muni.pa165.mushroomhunter.api.Type;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Roman Smékal
 */
public interface MushroomService {

    void delete(MushroomDto mushroomDto);

    MushroomDto find(long id);

    List<MushroomDto> findAll();

    long save(MushroomDto mushroomDto);

    MushroomDto update(MushroomDto mushroomDto);
    
}
