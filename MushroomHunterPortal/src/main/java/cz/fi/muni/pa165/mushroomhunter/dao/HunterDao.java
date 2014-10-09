package cz.fi.muni.pa165.mushroomhunter.dao;

import cz.fi.muni.pa165.mushroomhunter.entity.Hunter;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lukáš Valach
 */
@Local
public interface HunterDao {

    public long save(Hunter hunter);

    public Hunter update(Hunter hunter);

    public void delete(Hunter hunter);

    public Hunter find(int id);

    public List<Hunter> findByName(String firstName);

    public List<Hunter> findBySurname(String surname);

    public List<Hunter> findAll();
}
