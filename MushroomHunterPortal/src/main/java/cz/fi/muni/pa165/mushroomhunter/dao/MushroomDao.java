/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Location;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;
import cz.fi.muni.pa165.mushroomhunter.entity.Type;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Simon Hochla
 */
public interface MushroomDao {
    public long save(Mushroom mushroom);
    
    public Mushroom update(Mushroom mushroom);
    
    public void delete(Mushroom mushroom);
    
    public Mushroom find(long id);
    
    public List<Mushroom> findByName(String name);
    
    public List<Mushroom> findByLocation(Location loc);
    
    public List<Mushroom> findByType(Type type);
    
    public List<Mushroom> findByOccurenceDate(Date startOfOccurence, Date endOfOccurence);
    
    public List<Mushroom> findAll();
}
