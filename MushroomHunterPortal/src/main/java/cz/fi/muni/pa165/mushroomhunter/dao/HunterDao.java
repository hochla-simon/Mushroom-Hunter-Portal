/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import java.util.List;

/**
 *
 * @author Lukáš Valach
 */
public interface HunterDao {
    public int save(Hunter hunter);
    public void update(Hunter hunter);
    public void delete(Hunter hunter);
    public int find(int id);
    public void findByName(String name);
    public void findBySurname(String surname);
    public List<Hunter> findAll();
}
