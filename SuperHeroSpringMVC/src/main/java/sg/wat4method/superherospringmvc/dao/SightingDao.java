/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

import java.time.LocalDate;
import java.util.List;
import sg.wat4method.superherospringmvc.model.Sighting;

/**
 *
 * @author Jun
 */
public interface SightingDao {

    List<Sighting> getAll() throws PersistenceException;

    List<Sighting> getByDate(LocalDate sightingDate) throws PersistenceException;

    Sighting getById(int sightingId) throws PersistenceException;

    Sighting addSighting(Sighting sighting) throws PersistenceException;

    void editSighting(Sighting sighting) throws PersistenceException;

    void deleteSighting(int sightingId) throws PersistenceException;

    void deleteSightingBySuper(int superId) throws PersistenceException;

    void deleteSightingByLocation(int locationId) throws PersistenceException;

    List<Sighting> getLatestSighting() throws PersistenceException;

}
