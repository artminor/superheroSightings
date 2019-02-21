/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

import java.sql.SQLException;
import java.util.List;
import sg.wat4method.superherospringmvc.model.Location;

/**
 *
 * @author Jun
 */
public interface LocationDao {

    List<Location> getAll() throws PersistenceException;

    Location getById(int locationId) throws PersistenceException;

    Location getLocationByName(String name) throws PersistenceException;

    Location addLocation(Location location) throws PersistenceException;

    void editLocation(Location location) throws PersistenceException;

    void deleteLocation(int locationId) throws PersistenceException;

}
