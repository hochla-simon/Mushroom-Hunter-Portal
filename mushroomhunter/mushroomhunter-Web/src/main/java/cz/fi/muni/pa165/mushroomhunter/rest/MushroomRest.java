/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cz.fi.muni.pa165.mushroomhunter.api.dto.MushroomDto;
import cz.fi.muni.pa165.mushroomhunter.api.service.MushroomService;
import cz.fi.muni.pa165.mushroomhunter.api.service.SecurityService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.AccessDeniedException;

/**
 *
 * @author Radim Čejka
 */
@RestController
@RequestMapping("/mushroom")
public class MushroomRest {

    @Autowired
    MushroomService mushroomService;
    
    @Autowired
    SecurityService securityService;

    @RequestMapping(method = RequestMethod.GET)
    public List<MushroomDto> getMushroomList() {
        List<MushroomDto> mushroomList = mushroomService.findAll();
        if (mushroomList == null)
			mushroomList = new ArrayList<MushroomDto>();
        return mushroomList;
    }

    
    @RequestMapping(value = "{mushroomId}", method = RequestMethod.GET)
    public MushroomDto getMushroomDetail(@PathVariable Long mushroomId) {
        return mushroomService.find(mushroomId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Long> createMushroom(@RequestBody @Valid MushroomDto mushroom) {
        List<Long> resultList = new ArrayList<>();
        resultList.add(mushroomService.save(mushroom));
        return resultList;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public MushroomDto updateMushroom(@RequestBody @Valid MushroomDto mushroom) {
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        if (!securityService.isCurrentlyLoggedUserAdmin()){
            throw new AccessDeniedException("Access denien: User "+currentUserId+" is not admin so he cannot update mushroom "+mushroom.getId());
        }
        return mushroomService.update(mushroom);
    }

    @RequestMapping(value = "{mushroomId}", method = RequestMethod.DELETE)
    public void deleteMushroom(@PathVariable Long mushroomId) {
        MushroomDto mushroom = mushroomService.find(mushroomId);
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        if (!securityService.isCurrentlyLoggedUserAdmin()){
            throw new AccessDeniedException("Access denien: User "+currentUserId+" is not admin so he cannot delete mushroom "+mushroom.getId());
        }
        mushroomService.delete(mushroom);
    }

}
