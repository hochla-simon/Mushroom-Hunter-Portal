package cz.fi.muni.pa165.mushroomhunter.converter;

import cz.fi.muni.pa165.mushroomhunter.api.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Radim Cejka
 */
@Component
public class HunterConverter {

    /**
     * Convert Hunter DTO to Hunter entity.
     *
     * @param hunterDto DTO
     * @return Hunter entity
     */
    public Hunter hunterDtoToEntity(HunterDto hunterDto) {
        Hunter hunter = new Hunter();
        hunter.setDescription(hunterDto.getDescription());
        hunter.setFirstName(hunterDto.getFirstName());
        hunter.setId(hunterDto.getId());
        hunter.setNick(hunterDto.getNick());
        hunter.setPassword(hunterDto.getPassword());
        hunter.setSurname(hunterDto.getSurname());
        return hunter;
    }

    /**
     * Convert Hunter entity to Hunter DTO.
     *
     * @param hunter entity
     * @return Hunter Dto
     */
    public HunterDto hunterEntityToDto(Hunter hunter) {
        HunterDto hunterDto = new HunterDto();
        hunterDto.setDescription(hunter.getDescription());
        hunterDto.setFirstName(hunter.getFirstName());
        hunterDto.setId(hunter.getId());
        hunterDto.setPassword(hunter.getPassword());
        hunterDto.setSurname(hunter.getSurname());
        hunterDto.setNick(hunter.getNick());
        return hunterDto;
    }

    /**
     * Convert Hunter entity List to Hunter DTO List.
     *
     * @param hunterList entities list
     * @return Hunter Dto list
     */
    public List<HunterDto> hunterEntityToDtoList(List<Hunter> hunterList) {
        List<HunterDto> hunterDaoList = new ArrayList<>();
        for (Hunter hunter : hunterList) {
            hunterDaoList.add(this.hunterEntityToDto(hunter));
        }
        return hunterDaoList;
    }
}
