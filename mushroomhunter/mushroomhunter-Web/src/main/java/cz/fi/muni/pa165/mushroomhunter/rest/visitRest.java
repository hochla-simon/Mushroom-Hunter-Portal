package cz.fi.muni.pa165.mushroomhunter.rest;

import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.fi.muni.pa165.mushroomhunter.api.dto.VisitDto;
import cz.fi.muni.pa165.mushroomhunter.api.service.LocationService;
import cz.fi.muni.pa165.mushroomhunter.api.service.VisitService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roman Smékal
 */
@RestController
@RequestMapping("/visit")
public class visitRest {
    
    @Autowired
    VisitService visitService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<VisitDto> getVisitList() {
        List<VisitDto> visitList = visitService.findAllVisits();
        if (visitList == null)
            visitList = new ArrayList<VisitDto>();
        return visitList;
    }
    
    @RequestMapping(value = "{visitId}", method = RequestMethod.GET)
    public VisitDto getVisitDetail(@PathVariable Long visitId) {
        return visitService.findVisit(visitId);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public List<Long> createVisit(@RequestBody VisitDto visit) {
        if(visit.getLocation() != null) {
            System.out.println("obsahuje lokaci");
        } else {
            System.out.println("neobsahuje lokaci");
        }
        
        List<Long> resultList = new ArrayList<>();
        resultList.add(visitService.saveVisit(visit));
        return resultList;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public VisitDto updateVisit(@RequestBody VisitDto visit) {
        return visitService.updateVisit(visit);
    }
    
    @RequestMapping(value = "{visitId}", method = RequestMethod.DELETE)
    public void deleteVisit(@PathVariable Long visitId) {
        VisitDto visit = visitService.findVisit(visitId);
        visitService.deleteVisit(visit);
    }
}
