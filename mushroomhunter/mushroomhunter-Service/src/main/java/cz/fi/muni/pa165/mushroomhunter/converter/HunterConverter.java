package cz.fi.muni.pa165.mushroomhunter.converter;

import cz.fi.muni.pa165.mushroomhunter.api.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Radim Cejka
 */
@Component
public class HunterConverter {
    
    @Autowired
    private Mapper mapper;

    /**
     * Convert Hunter DTO to Hunter entity.
     *
     * @param hunterDto DTO
     * @return Hunter entity
     */
    public Hunter hunterDtoToEntity(HunterDto hunterDto) {
        return mapper.map(hunterDto, Hunter.class);
                }
/**
     * Convert Hunter entity to Hunter DTO.
     *
     * @param hunter entity
     * @return Hunter Dto
     */
    public HunterDto hunterEntityToDto(Hunter hunter) {
        return mapper.map(hunter, HunterDto.class);
    }

    /**
     * Convert Hunter entity List to Hunter DTO List.
     *
     * @param hunterList entities list
     * @return Hunter Dto list
     */
    public List<HunterDto> hunterEntityToDtoList(List<Hunter> hunterList) {
        List<HunterDto> hunterDaoList = new ArrayList<>();
        for(Hunter hunter : hunterList)
		hunterDaoList.add(this.hunterEntityToDto(hunter));
        return hunterDaoList;
    }
}
