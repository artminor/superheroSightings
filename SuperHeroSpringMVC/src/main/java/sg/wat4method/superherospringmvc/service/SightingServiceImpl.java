/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.service;

import sg.wat4method.superherospringmvc.dao.PersistenceException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.inject.Inject;
import sg.wat4method.superherospringmvc.dao.LocationDao;
import sg.wat4method.superherospringmvc.dao.SightingDao;
import sg.wat4method.superherospringmvc.model.Location;
import sg.wat4method.superherospringmvc.model.Sighting;

public class SightingServiceImpl implements SightingService {

    LocationDao dao;
    SightingDao stDao;

    @Inject
    public SightingServiceImpl(LocationDao dao, SightingDao stDao) {
        this.dao = dao;
        this.stDao = stDao;
    }

    @Override
    public List<Location> getAllLocations() throws DataNotFoundException {
        try {
            return dao.getAll();
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public Location getLocationById(int locationId) throws DataNotFoundException {
        try {
            return dao.getById(locationId);
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public void editLocation(Location location) throws DataIntegrityViolationException {
        try {
            dao.editLocation(location);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public void deleteLocation(int locationId) throws DataIntegrityViolationException {
        try {
            dao.deleteLocation(locationId);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }
    
//     @Override
//    public void deleteLocation(int locationId) {
//        List<Sighting> del = this.stDao.getAll();
//        del = del.stream().filter(f-> f.getLocationId() == locationId).collect(Collectors.toList());
//        for (Sighting f: del){
//           if(f.getLocationId()== locationId){
//               collect(Collector<f, del>);
//               f.collect(Collectors.toList());
//           }
//            SightingDao.deleteSighting(f.getSightingId());
//        }
//
//        LocationDao.deleteLocation(locationId);
//    }

    @Override 
    public List<Sighting> getAllSightings() throws DataNotFoundException {
        try {
            return stDao.getAll();
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public Sighting getSightingById(int sightingId) throws DataNotFoundException {
        try {
            return stDao.getById(sightingId);
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public void deleteSighting(int sightingId) throws DataIntegrityViolationException {
        try {
            stDao.deleteSighting(sightingId);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public Location addLocation(Location location) throws DataIntegrityViolationException {
        try {
            return dao.addLocation(location);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public Sighting addSighting(Sighting sighting)
            throws DataIntegrityViolationException {
        try {
            return stDao.addSighting(sighting);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public void updateSighting(Sighting sighting) throws DataIntegrityViolationException {
        try {
            stDao.editSighting(sighting);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public List<Sighting> getLatestSightings() throws DataNotFoundException {
        try {
            return stDao.getLatestSighting();
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

}
