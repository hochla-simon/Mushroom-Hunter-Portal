/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.service;

import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.converter.HunterConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.LocationConverter;
import cz.fi.muni.pa165.mushroomhunter.converter.VisitConverter;
import cz.fi.muni.pa165.mushroomhunter.dao.VisitDao;
import cz.fi.muni.pa165.mushroomhunter.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.dto.VisitDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Visit;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukáš Valach
 */
@Service
public class VisitServiceImpl {

    @Autowired
    VisitDao visitDao;
    @Autowired
    VisitConverter visitConverter;
    @Autowired
    HunterConverter hunterConverter;
    @Autowired
    LocationConverter locationConverter;
    @PersistenceContext
    private EntityManager em;


    public VisitServiceImpl() {
    }

    @Transactional
    public long saveVisit(VisitDto visitDto) {
        return visitDao.save(visitConverter.visitDtoToEntity(visitDto));
    }

    @Transactional
    public void deleteVisit(VisitDto visitDto) {
        visitDao.delete(visitConverter.visitDtoToEntity(visitDto));
    }

    @Transactional
    public VisitDto updateVisit(VisitDto visitDto) {
        Visit visit = visitDao.update(visitConverter.visitDtoToEntity(visitDto));
        return visitConverter.visitEntityToDto(visit);
    }

    @Transactional
    public List<VisitDto> findAllVisits(VisitDto visitDto) {
        return visitConverter.visitEntityToDtoList(visitDao.findAll());
    }

    @Transactional
    public List<VisitDto> findVisitByLocation(LocationDto locationDto) {
        return visitConverter.visitEntityToDtoList(visitDao.findByLocation(locationConverter.locationDtoToEntity(locationDto)));
    }

    @Transactional
    public List<VisitDto> findVisitByHunter(HunterDto hunterDto) {
        return visitConverter.visitEntityToDtoList(visitDao.findByHunter(hunterConverter.hunterDtoToEntity(hunterDto)));
    }
}
