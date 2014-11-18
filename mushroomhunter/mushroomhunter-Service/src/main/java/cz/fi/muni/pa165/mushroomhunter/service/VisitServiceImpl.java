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
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukáš Valach
 */
@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    VisitDao visitDao;
    @Autowired
    VisitConverter visitConverter;
    @Autowired
    HunterConverter hunterConverter;
    @Autowired
    LocationConverter locationConverter;

    public VisitServiceImpl() {
    }

    @Transactional
    @Override
    public long saveVisit(VisitDto visitDto) {
        try {
            return visitDao.save(visitConverter.visitDtoToEntity(visitDto));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error saving visit.", e);
        }
    }

    @Transactional
    @Override
    public void deleteVisit(VisitDto visitDto) {
        try {
            visitDao.delete(visitConverter.visitDtoToEntity(visitDto));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error deleting visit.", e);
        }
    }

    @Transactional
    @Override
    public VisitDto updateVisit(VisitDto visitDto) {
        try {
            Visit visit = visitDao.update(visitConverter.visitDtoToEntity(visitDto));
            return visitConverter.visitEntityToDto(visit);
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error updating visit.", e);
        }
    }

    @Transactional
    @Override
    public List<VisitDto> findAllVisits() {
        try {
            return visitConverter.visitEntityToDtoList(visitDao.findAll());
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error during findAllVisits.", e);
        }
    }

    @Transactional
    @Override
    public List<VisitDto> findVisitByLocation(LocationDto locationDto) {
        try {
            return visitConverter.visitEntityToDtoList(visitDao.findByLocation(locationConverter.locationDtoToEntity(locationDto)));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error during findVisitByLocation.", e);
        }
    }

    @Transactional
    @Override
    public List<VisitDto> findVisitByHunter(HunterDto hunterDto) {
        try {
            return visitConverter.visitEntityToDtoList(visitDao.findByHunter(hunterConverter.hunterDtoToEntity(hunterDto)));
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Error during findVisitByHunter.", e);
        }
    }
}
