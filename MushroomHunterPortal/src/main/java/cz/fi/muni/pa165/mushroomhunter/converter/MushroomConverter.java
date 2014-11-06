/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.converter;

import cz.fi.muni.pa165.mushroomhunter.dto.MushroomDto;
import cz.fi.muni.pa165.mushroomhunter.entity.Mushroom;

/**
 *
 * @author Simon
 */
public class MushroomConverter {
    
      public Mushroom mushroomDtoToEntity(MushroomDto mushroomDto) {
          return new Mushroom();
      }
}