package cz.fi.muni.pa165.mushroomhunter.rest;

import cz.fi.muni.pa165.mushroomhunter.api.dto.VisitDto;
import cz.fi.muni.pa165.mushroomhunter.api.service.VisitService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.fi.muni.pa165.mushroomhunter.api.service.SecurityService;
import org.springframework.security.access.AccessDeniedException;

/**
 *
 * @author Roman Smékal
 */
@RestController
@RequestMapping("/visit")
public class VisitRest {

    @Autowired
    VisitService visitService;

    @Autowired
    SecurityService securityService;

    private static final Logger logger = LoggerFactory.getLogger(VisitRest.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<VisitDto> getVisitList() {
        List<VisitDto> visitList = visitService.findAllVisits();
        if (visitList == null) {
            visitList = new ArrayList<VisitDto>();
        }
        return visitList;
    }

    @RequestMapping(value = "{visitId}", method = RequestMethod.GET)
    public VisitDto getVisitDetail(@PathVariable Long visitId) {
        return visitService.findVisit(visitId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Long> createVisit(@RequestBody @Valid VisitDto visit) {
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        visit.setOwnerId(currentUserId);
        List<Long> resultList = new ArrayList<>();
        resultList.add(visitService.saveVisit(visit));
        return resultList;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public VisitDto updateVisit(@RequestBody @Valid VisitDto visit) {
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        if (!securityService.hasPermissionToModifyEntity(visit.getOwnerId())) {
            throw new AccessDeniedException("Access denied: User " + currentUserId + " cannot update visit " + visit.getId());
        }
        System.out.println("Pred");
        return visitService.updateVisit(visit);
    }

    @RequestMapping(value = "{visitId}", method = RequestMethod.DELETE)
    public void deleteVisit(@PathVariable Long visitId) {
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        VisitDto visit = visitService.findVisit(visitId);
        if (!securityService.hasPermissionToModifyEntity(visit.getOwnerId())) {
            throw new AccessDeniedException("Access denied: User " + currentUserId + " cannot update location " + visit.getId());
        }
        visitService.deleteVisit(visit);
    }
}
