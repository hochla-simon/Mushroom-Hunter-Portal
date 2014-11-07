/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.converter;

import cz.fi.muni.pa165.mushroomhunter.dto.VisitDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Visit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukáš Valach
 */
@Component
public class VisitConverter {

    /**
     * Convert Visit DTO to Visit entity.
     * @param Visit DTO
     * @return Visit entity
     */
    public Visit visitDtoToEntity(VisitDto dto) {
        Visit entity = new Visit();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setFoundMushrooms(dto.getFoundMushrooms());
        entity.setHunter(dto.getHunter());
        entity.setLocation(dto.getLocation());
        return entity;
    }

    /**
     * Convert Visit entity to Visit DTO.
     * @param Visit entity
     * @return Visit DTO
     */
    public VisitDto visitEntityToDto(Visit entity) {
        VisitDto dto = new VisitDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setFoundMushrooms(entity.getFoundMushrooms());
        dto.setHunter(entity.getHunter());
        dto.setLocation(entity.getLocation());
        return dto;
    }

    public List<VisitDto> visitEntityToDtoList(List<Visit> visits) {
        List<VisitDto> visitDaoList = new ArrayList<>();
        Iterator<Visit> iterator = visits.iterator();
        while (iterator.hasNext()) {
		visitDaoList.add(this.visitEntityToDto(iterator.next()));
	}
        return visitDaoList;
    }
}
