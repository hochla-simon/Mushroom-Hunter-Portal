/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.rest;

import cz.fi.muni.pa165.mushroomhunter.api.dto.HunterDto;
import cz.fi.muni.pa165.mushroomhunter.api.service.HunterService;
import cz.fi.muni.pa165.mushroomhunter.api.service.SecurityService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import org.springframework.security.access.AccessDeniedException;
/**
 *
 * @author Simon
 */
@RestController
@RequestMapping("/hunter")
public class HunterRest {
    @Autowired
    HunterService hunterService;
    
    @Autowired
    SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<HunterDto> getHunterList() {
	List<HunterDto> hunterList = hunterService.findAll();
        if (hunterList == null) {
            hunterList = new ArrayList<>();
        }
        return hunterList;
    }
    
    @RequestMapping(value = "{hunterId}", method = RequestMethod.GET)
    public HunterDto getHunterDetail(@PathVariable Long hunterId) {
        return hunterService.find(hunterId);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public List<Long> createHunter(@RequestBody @Valid HunterDto hunter) {
  	List<Long> resultList = new ArrayList<>();
        resultList.add(hunterService.save(hunter));
        return resultList;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public HunterDto updateHunter(@RequestBody @Valid HunterDto hunter) {
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        if (!securityService.hasPermissionToModifyEntity(hunter.getId())) {
            throw new AccessDeniedException("Access denied: User " + currentUserId + " cannot update hunter " + hunter.getId());
        }
        return hunterService.update(hunter);
    }

    @RequestMapping(value = "{hunterId}", method = RequestMethod.DELETE)
    public void deleteHunter(@PathVariable Long hunterId) {
	HunterDto hunter = hunterService.find(hunterId);
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        if (!securityService.hasPermissionToModifyEntity(hunter.getId())) {
            throw new AccessDeniedException("Access denied: User " + currentUserId + " cannot delete hunter " + hunter.getId());
        }
        hunterService.delete(hunter);
    }
}
