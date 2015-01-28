/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import cz.fi.muni.pa165.mushroomhunter.entity.HunterRole;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Simon
 */
public class HunterRoleDaoImpl implements HunterRoleDao {
   /**
     * Entity manager.
     */
    @PersistenceContext
    private EntityManager em;

    @Override
    public long save(HunterRole hunterRole) {
        em.persist(hunterRole);
        return hunterRole.getId();
    }

    @Override
    public HunterRole update(HunterRole hunterRole) {
        return em.merge(hunterRole);
    }

    @Override
    public void delete(HunterRole hunterRole) {
	if (!em.contains(hunterRole)){
            hunterRole = em.merge(hunterRole);
        }
        em.remove(hunterRole);
    }

    @Override
    public HunterRole find(long id) {
        final Query query = em.createQuery("from HunterRole where id = :id");
        query.setParameter("id", id);
        return (HunterRole) query.getSingleResult();
    }
    
    @Override
    public HunterRole findByHunter(Hunter hunter) {
        final Query query = em.createQuery("from HunterRole where hunter = :hunter");
        query.setParameter("hunter", hunter);
        return (HunterRole) query.getSingleResult();
    }
}
