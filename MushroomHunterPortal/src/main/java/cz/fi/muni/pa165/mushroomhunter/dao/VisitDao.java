/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Visit;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Radim Cejka 
 */
@Local
public interface VisitDao {
    
    public long save(Visit visit);

    public Visit update(Visit visit);

    public void delete(Visit visit);

    public Visit find(int id);

    public List<Visit> findByHunter(Hunter hunter);

    public List<Visit> findByLocation(Location location);

    public List<Visit> findAll();
}

