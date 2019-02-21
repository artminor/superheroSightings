/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.wat4method.superherospringmvc.model.Sighting;

public class SightingDaoDbImpl implements SightingDao {

    LocationDaoDbImpl locationDao;

    private static final String SLQ_GET_LAST_10
            = "SELECT * FROM sightings ORDER BY SightingDate DESC LIMIT 10;";
    private static final String SQL_SELECT_SIGHTING_BY_DATE
            = " select * from sightings where sightingDate = ? ORDER BY sightingId DESC";
    private static final String SQL_SELECT_SIGHTING_BY_ID
            = " select * from sightings where sightingId = ? ";
    private static final String SQL_DELETE_SIGHTING_BY_DATE
            = "delete from sightings where sightingDate = ?";
    private static final String SQL_DELETE_SIGHTING_BY_LOCATION
            = "delete from sightings where locationId= ?";
    private static final String SQL_INSERT_SIGHTING
            = "insert into sightings(sightingDate, locationId, superId)values(?, ?, ?)";
    private static final String SQL_UPDATE_SIGHTING
            = "update sightings set sightingDate = ?, locationId = ?, superId = ? "
            + "where sightingId = ?";
    private static final String SQL_SELECT_ALL_LOCATION_BY_SIGHTING
            = "select loc.* "
            + "from location loc "
            + "inner join sightings s "
            + "on s.locationId = loc.locationId "
            + "where s.sightingId = ? ";
    private static final String SQL_SELECT_SIGHTING_FOR_LOCATION
            = "select s.*"
            + "from sightings s "
            + "inner join location loc on loc.locationId = s.locationId "
            + "where loc.locationId = ? ";
    private static final String SQL_SELECT_ORGANIZATION_FOR_LOCATION
            = "select o.* from organization o "
            + " inner join location loc on loc.locationId = o.locationId "
            + " where loc.locationId= ?";
    private static final String SQL_SELECT_All_SIGHTING_BY_LOCATION
            = "select s.* "
            + "from sightings s "
            + "inner join location loc "
            + "on loc.sightingId = s.sighting_id "
            + "where loc.locationId = ? ";
    private static final String SQL_SELECT_SUPER_BY_SIGHTING
            = " select h.* "
            + " from super h "
            + " inner join  super_person_sighting sps "
            + " on h.superId = sps.superId "
            + " inner join sightings "
            + " on sps.sightingId = sightings.sightingId "
            + " where sightings.sightingId =  ?";
    private static final String SQL_SELECT_ALL_SIGHTING_BY_DATE
            = "select * from sightings "
            + "where date = ?";
    private static final String SQL_DELETE_SIGHTING_SUPER
            = "delete from sightings where superId = ?";
    private static final String SQL_DELETE_SIGHTING
            = "delete from sightings where sightingId = ?";
    private static final String SQL_SELECT_ALL_SIGHTING
            = " select * from sightings ORDER BY sightingDate desc";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sighting> getAll() throws PersistenceException {
        List<Sighting> allSightings = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTING,
                new SightingMapper());
        return allSightings;
    }

    @Override
    public Sighting getById(int sightingId) throws PersistenceException {
        return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_BY_ID,
                new SightingMapper(), sightingId);
    }

    @Override
    public List<Sighting> getByDate(LocalDate sightingDate) throws PersistenceException {
        List<Sighting> sightingsByDate = jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_DATE,
                new SightingMapper());
        return sightingsByDate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(Sighting sighting) throws PersistenceException {

        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getSightingDate().toString(),
                sighting.getLocationId(),
                sighting.getSuperId());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        sighting.setSightingId(newId);
        return sighting;

    }

    @Override
    public void deleteSightingByLocation(int locationId) throws PersistenceException {
        jdbcTemplate.update(SQL_DELETE_SIGHTING_BY_LOCATION, locationId);
    }

    @Override
    public void deleteSightingBySuper(int superId) throws PersistenceException {
        jdbcTemplate.update(SQL_DELETE_SIGHTING_SUPER, superId);
    }

    @Override
    public void deleteSighting(int sightingId) throws PersistenceException {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void editSighting(Sighting sighting) throws PersistenceException {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getSightingDate().toString(),
                sighting.getLocationId(),
                sighting.getSuperId(),
                sighting.getSightingId());
    }

    @Override
    public List<Sighting> getLatestSighting() throws PersistenceException {
        List<Sighting> last10 = jdbcTemplate.query(SLQ_GET_LAST_10,
                new SightingMapper());
        return last10;
    }

//    private void superForSighting(Sighting sighting) {
//        final LocalDate sightingDate = sighting.getSightingDate();
//        final List<Super> superHeroes = sighting.getSupers();
//        final Location location = 
//
//        for (Super hero : superHeroes) {
//            jdbcTemplate.update(SQL_INSERT_SIGHTING,
//            sightingDate, locationId, hero.getSuperId());
//        }
//    }
//    private Location findLocationForSighting(Sighting sighting) {
//        return jdbcTemplate.queryForObject(SQL_SELECT_ALL_LOCATION_BY_SIGHTING,
//                new LocationMapper(), sighting.getSightingId());
//    }
//
//    private List<Sighting> findSuperForSighting(Sighting sighting) {
//        return jdbcTemplate.query(SQL_SELECT_SUPER_BY_SIGHTING,
//                new SightingMapper(), sighting.getSightingId());
//    }
//
//    private List<Sighting> joinSightingAndLocation(List<Sighting> sightingList) {
//        for (Sighting currentSighting : sightingList) {
//            currentSighting.setSupers(jdbcTemplate.query(SQL_INSERT_SUPER_BY_SIGHTING,
//                    new SuperMapper());
//            currentSighting.setLocation(jdbcTemplate.query(SQL_SELECT_SUPER_BY_SIGHTING,
//                    new SightingMapper(), sighting.getSightingId()));
//        }
//        return sightingList;
//    }
    private static final class SightingMapper implements RowMapper<Sighting> {

        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting st = new Sighting();
            st.setSightingId(rs.getInt("sightingId"));
            st.setSightingDate(rs.getTimestamp("sightingDate").toLocalDateTime().toLocalDate());
            st.setLocationId(rs.getInt("locationId"));
            st.setSuperId(rs.getInt("superId"));
            return st;
        }

    }

//    private static final class SuperMapper implements RowMapper<Super> {
//
//        public Super mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Super hero = new Super();
//            hero.setSuperId(rs.getInt("superId"));
//            hero.setName(rs.getString("name"));
//            hero.setDescription(rs.getString("description"));
//            hero.setIsHero(rs.getBoolean("isHero"));
//            return hero;
//        }
//    }
}
