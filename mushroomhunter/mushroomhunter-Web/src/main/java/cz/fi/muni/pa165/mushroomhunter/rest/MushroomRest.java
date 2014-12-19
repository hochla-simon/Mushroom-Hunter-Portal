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
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Radim Čejka
 */
@RestController
@RequestMapping("/mushroom")
public class MushroomRest {

    @Autowired
    MushroomService mushroomService;

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
    public List<Long> createMushroom(@RequestBody MushroomDto mushroom) {
        List<Long> resultList = new ArrayList<>();
        resultList.add(mushroomService.save(mushroom));
        return resultList;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public MushroomDto updateMushroom(@RequestBody MushroomDto mushroom) {
        return mushroomService.update(mushroom);
    }

    @RequestMapping(value = "{mushroomId}", method = RequestMethod.DELETE)
    public void deleteMushroom(@PathVariable Long mushroomId) {
        MushroomDto mushroom = mushroomService.find(mushroomId);
        mushroomService.delete(mushroom);
    }

}
