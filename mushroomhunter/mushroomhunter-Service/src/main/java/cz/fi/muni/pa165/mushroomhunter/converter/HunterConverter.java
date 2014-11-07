/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.converter;

import cz.fi.muni.pa165.mushroomhunter.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.dto.LocationDto;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukáš Valach
 */
@Component
public class HunterConverter {
    
    //@Autowired
    //private Mapper mapper;

    public Hunter hunterDtoToEntity(HunterDto hunterDto) {
        
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(hunterDto, Hunter.class);
                }

    public HunterDto hunterEntityToDto(Hunter hunter) {
        
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(hunter, HunterDto.class);
    }

    public List<HunterDto> hunterEntityToDtoList(List<Hunter> hunterList) {
        List<HunterDto> hunterDaoList = new ArrayList<>();
        for(Hunter hunter : hunterList)
		hunterDaoList.add(this.hunterEntityToDto(hunter));
        return hunterDaoList;
    }
}
