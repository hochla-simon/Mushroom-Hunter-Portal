/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.mushroomhunter.rest;

/**
 *
 * @author Simon
 */
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashCode {

 public static String getHashPassword(String password) {
  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  String hashedPassword = passwordEncoder.encode(password);

  System.out.println(hashedPassword);
  return hashedPassword;
 }

}


