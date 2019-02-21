/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.wat4method.superherospringmvc.model.Location;

public class LocationDaoDbImpl implements LocationDao {

    private static final String SQL_SELECT_ALL_LOCATION
            = "select * from location order by locationId desc;";
    private static final String SQL_SELECT_LOCATION
            = "select * from location where locationId = ?";
    private static final String SQL_INSERT_LOCATION
            = "insert into location "
            + "(name, latitude, longitude, street, city, state, zip, description) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_LOCATION
            = "update location set "
            + "name = ?, latitude=?, longitude=?, street=?, city=?, state=?, zip=?, description=? "
            + "where locationId = ?";
    private static final String SQL_DELETE_LOCATION
            = "delete from location where locationId = ?";
    private static final String SQL_SELECT_LOCATION_BY_NAME
            = "select * from location where name = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Location> getAll() throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATION,
                new LocationMapper());
    }

    @Override
    public Location getById(int locationId) throws PersistenceException {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                new LocationMapper(), locationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location addLocation(Location location) throws PersistenceException {

        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getDescription());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the hero object and return it
        location.setLocationId(newId);
        return location;
    }

    @Override
    public void editLocation(Location location) throws PersistenceException {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getDescription(),
                location.getLocationId());
    }

    @Override
    public void deleteLocation(int locationId) throws PersistenceException {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public Location getLocationByName(String name) throws PersistenceException {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_NAME,
                new LocationMapper(), name);
    }

    private static final class LocationMapper implements RowMapper<Location> {

        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            sg.wat4method.superherospringmvc.model.Location location = new Location();
            location.setLocationId(rs.getInt("locationId"));
            location.setName(rs.getString("name"));
            location.setLatitude(rs.getBigDecimal("latitude"));
            location.setLongitude(rs.getBigDecimal("longitude"));
            location.setStreet(rs.getString("street"));
            location.setCity(rs.getString("city"));
            location.setState(rs.getString("state"));
            location.setZip(rs.getString("zip"));
            location.setDescription(rs.getString("description"));
            return location;
        }
    }

}
