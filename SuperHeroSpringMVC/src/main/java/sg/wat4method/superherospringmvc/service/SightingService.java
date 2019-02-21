/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.service;

import java.util.List;
import sg.wat4method.superherospringmvc.model.Location;
import sg.wat4method.superherospringmvc.model.Sighting;

/**
 *
 * @author Jun
 */
public interface SightingService {

    //location methods
    List<Location> getAllLocations() throws DataNotFoundException;

    Location getLocationById(int locationId) throws DataNotFoundException;

    void editLocation(Location location) throws DataIntegrityViolationException;

    void deleteLocation(int locationId) throws DataIntegrityViolationException;

    //sighting methods
    Sighting addSighting(Sighting sighting) throws DataIntegrityViolationException;

    List<Sighting> getAllSightings() throws DataNotFoundException;

    Sighting getSightingById(int sightingId) throws DataNotFoundException;

    void updateSighting(Sighting sighting) throws DataIntegrityViolationException;

    void deleteSighting(int sightingId) throws DataIntegrityViolationException;

    //bridge table methods
    Location addLocation(Location location) throws DataIntegrityViolationException;

    public List<Sighting> getLatestSightings() throws DataNotFoundException;

}
