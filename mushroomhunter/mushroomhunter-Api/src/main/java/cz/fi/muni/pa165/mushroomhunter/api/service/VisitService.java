package cz.fi.muni.pa165.mushroomhunter.api.service;

import cz.fi.muni.pa165.mushroomhunter.api.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.VisitDto;
import java.util.List;

/**
 *
 * @author Lukáš Valach
 */
public interface VisitService {

    void deleteVisit(VisitDto visitDto);

    public VisitDto findVisit(long id);
    
    List<VisitDto> findAllVisits();

   List<VisitDto> findVisitByHunter(HunterDto hunterDto);

   List<VisitDto> findVisitByLocation(LocationDto locationDto);

    long saveVisit(VisitDto visitDto);

    VisitDto updateVisit(VisitDto visitDto);
    
}
