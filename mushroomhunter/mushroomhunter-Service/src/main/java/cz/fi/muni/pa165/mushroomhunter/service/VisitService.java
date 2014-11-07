/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.dto.VisitDto;
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
