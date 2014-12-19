package cz.fi.muni.pa165.mushroomhunter.converter;

import cz.fi.muni.pa165.mushroomhunter.api.dto.MushroomDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Roman Smékal
 */
@Component
public class MushroomConverter {

    /**
     * Converts Mushroom DTO to Mushroom entity.
     *
     * @param mushroomDto MushroomDto object to be converted.
     * @return Mushroom entity object.
     */
    public Mushroom mushroomDtoToEntity(MushroomDto mushroomDto) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(mushroomDto, Mushroom.class);
    }

    /**
     * Converts Mushroom entity to Mushroom DTO.
     *
     * @param mushroom Mushroom entity object to be converted.
     * @return Location DTO object.
     */
    public MushroomDto mushroomEntityToDto(Mushroom mushroom) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(mushroom, MushroomDto.class);
    }

    /**
     * Converts list of Mushroom entities to list of Mushroom DTOs.
     *
     * @param mushroomList List of Mushroom entity objects to be converted.
     * @return List<MushroomDto> List of DTO objects.
     */
    public List<MushroomDto> mushroomEntityToDtoList(List<Mushroom> mushroomList) {
        List<MushroomDto> mushroomDtoList = new ArrayList<>();
        for (Mushroom mushroom : mushroomList) {
            mushroomDtoList.add(this.mushroomEntityToDto(mushroom));
        }
        return mushroomDtoList;
    }

    /**
     * Converts list of Mushroom DTOs to list of Mushroom entities.
     *
     * @param mushroomDtoList List of Mushroom DTO objects to be converted.
     * @return List<Mushroom> List of entity objects.
     */
    public List<Mushroom> mushroomDtoToEntityList(List<MushroomDto> mushroomDtoList) {
        List<Mushroom> mushroomList = new ArrayList<>();
        for (MushroomDto mushroomDto : mushroomDtoList) {
            mushroomList.add(this.mushroomDtoToEntity(mushroomDto));
        }
        return mushroomList;
    }
}
