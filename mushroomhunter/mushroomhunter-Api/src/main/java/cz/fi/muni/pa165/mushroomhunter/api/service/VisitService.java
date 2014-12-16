package cz.fi.muni.pa165.mushroomhunter.api.service;

import cz.fi.muni.pa165.mushroomhunter.api.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.VisitDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukáš Valach
 */
public interface VisitService {

    @Transactional
    void deleteVisit(VisitDto visitDto);

    @Transactional
    public VisitDto findVisit(long id);
    
    @Transactional
    List<VisitDto> findAllVisits();

    @Transactional
    List<VisitDto> findVisitByHunter(HunterDto hunterDto);

    @Transactional
    List<VisitDto> findVisitByLocation(LocationDto locationDto);

    @Transactional
    long saveVisit(VisitDto visitDto);

    @Transactional
    VisitDto updateVisit(VisitDto visitDto);
    
}
