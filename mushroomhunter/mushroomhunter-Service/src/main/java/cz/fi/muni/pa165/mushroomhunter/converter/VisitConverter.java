package cz.fi.muni.pa165.mushroomhunter.converter;

import cz.fi.muni.pa165.mushroomhunter.api.dto.VisitDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Visit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lukáš Valach
 */
@Component
public class VisitConverter {

    @Autowired
    private Mapper mapper;

    /**
     * Convert Visit DTO to Visit entity.
     *
     * @param dto Visit dto
     * @return Visit entity
     */
    public Visit visitDtoToEntity(VisitDto dto) {
        return mapper.map(dto, Visit.class);
    }

    /**
     * Convert Visit entity to Visit DTO.
     *
     * @param entity Visit entity
     * @return Visit Dto
     */
    public VisitDto visitEntityToDto(Visit entity) {
        return mapper.map(entity, VisitDto.class);
    }

     /**
     * Convert list of Visit entities to list of Visit DTO objects
     *
     * @param visits List<Visit>
     * @return visitDtoList List<VisitDto>
     */
    public List<VisitDto> visitEntityToDtoList(List<Visit> visits) {
        List<VisitDto> visitDtoList = new ArrayList<>();
        Iterator<Visit> iterator = visits.iterator();
        while (iterator.hasNext()) {
            visitDtoList.add(this.visitEntityToDto(iterator.next()));
        }
        return visitDtoList;
    }
}
