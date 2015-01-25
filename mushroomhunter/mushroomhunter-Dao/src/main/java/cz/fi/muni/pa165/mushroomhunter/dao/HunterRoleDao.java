/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import cz.fi.muni.pa165.mushroomhunter.entity.HunterRole;

/**
 *
 * @author Simon Hochla
 */
public interface HunterRoleDao {

    void delete(HunterRole hunterRole);

    HunterRole find(long id);

    long save(HunterRole hunterRole);

    HunterRole update(HunterRole hunterRole);
    
    public HunterRole findByHunter(Hunter hunter);
}
