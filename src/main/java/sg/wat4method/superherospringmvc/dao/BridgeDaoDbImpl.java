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
import sg.wat4method.superherospringmvc.model.Organization;
import sg.wat4method.superherospringmvc.model.Power;
import sg.wat4method.superherospringmvc.model.Super;

public class BridgeDaoDbImpl implements BridgeDao {

    private static final String SQL_SELECT_POWERS_BY_SUPERID
            = "select p.powerId, p.name "
            + "from power p "
            + "Join superpower sps on sps.powerId = p.powerId "
            + "where superId = ?";
    private static final String SQL_SELECT_ORGS_BY_SUPERID
            = "select o.* "
            + "from organization o "
            + "Join superorganization sorg on sorg.organizationId = o.organizationId "
            + "where superId = ?";
    private static final String SQL_SELECT_SUPERS_BY_ORGANIZATION_ID
            = "select * "
            + "from super s "
            + "join superorganization so on s.superId = so.superId "
            + "where so.organizationId = ? ";
    private static final String SQL_SELECT_SUPERS_BY_POWERID
            = "select * "
            + "from super s "
            + "join superpower sp on s.superId = sp.superId "
            + "where sp.powerId = ? ";

    //prepare statement for power table
    private static final String SQL_SELECT_ALL_POWERS
            = "select * from power order by powerId desc";
    private static final String SQL_SELECT_POWER
            = "select * from power where powerId = ? ";
    private static final String SQL_INSERT_POWER
            = "insert into power "
            + "(name) "
            + "values (?)";
    private static final String SQL_UPDATE_POWER
            = "update power set "
            + "name = ? "
            + "where powerId = ?";
    private static final String SQL_DELETE_POWER
            = "delete from power where powerId = ? ;";
    private static final String SQL_CONSTRAINT_NOCHECK = "update power SET FOREIGN_KEY_CHECKS=0;";
    private static final String SQL_CONSTRAINT_CHECK = "update power SET FOREIGN_KEY_CHECKS=1;";

    //prepare statments for bridge tables
    private static final String SQL_DELETE_SUPERORG = "delete from superorganization where superId = ?";

    private static final String SQL_INSERT_SUPERORG = "insert into superorganization "
            + "(superId, organizationId) values (?,?)";
    private static final String SQL_INSERT_SUPERPOWER
            = "insert into superpower "
            + "(superId, powerId) values (?, ?)";
    private static final String SQL_DELETE_SUPERPOWER
            = "delete from superpower where superId = ?";

//    private static final String SQL_SELECT_LOCATION_BY_ORGANIZATION_ID = "select loc.locationId, loc.latitude, loc.longitude, loc.addressId, loc.description "
//            + "from location loc join organization on loc.locationId = organization.locationId "
//            + "where organization.organizationId = ?";
    private static final String SQL_SELECT_SUPERS_BY_SIGHTINGDATE
            = "select s.superId, s.name, s.description, s.isHero "
            + "from super s "
            + "join Sightings st on s.superId = st.superId "
            + "where st.sightingDate = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //powers dao CRRUD
    @Override
    public Power addPower(Power power) throws PersistenceException {
        jdbcTemplate.update(SQL_INSERT_POWER,
                power.getName());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        power.setPowerId(newId);
        return power;
    }

    @Override
    public List<Power> getAllPowers() throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS,
                new PowerMapper());
    }

    @Override
    public List<Power> getAllPowerBySuperId(int superId) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_POWERS_BY_SUPERID,
                new PowerMapper(), superId);
    }

    @Override
    public Power getPowerById(int powerId) throws PersistenceException {
        return jdbcTemplate.queryForObject(SQL_SELECT_POWER,
                new PowerMapper(), powerId);
    }

    @Override
    public void editPower(Power power) throws PersistenceException {
        jdbcTemplate.update(SQL_UPDATE_POWER,
                power.getName(),
                power.getPowerId());
    }

    @Override
    public void deletePower(int powerId) throws PersistenceException {

        jdbcTemplate.update(SQL_DELETE_POWER, powerId);

    }

    @Override
    public List<Organization> getAllOrgsBySuper(int superId) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ORGS_BY_SUPERID,
                new OrgMapper(), superId);
    }

    @Override
    public List<Super> getAllSupersByOrgId(int organizationId) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_SUPERS_BY_ORGANIZATION_ID,
                new SuperMapper(), organizationId);
    }

    @Override
    public void deleteSuperPower(int superId) throws PersistenceException {
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER,
                superId);
    }

    @Override
    public void deleteSuperOrg(int superId) throws PersistenceException {
        jdbcTemplate.update(SQL_DELETE_SUPERORG,
                superId);
    }

    @Override
    public void addSuperPower(Super hero) throws PersistenceException {
        hero.getPowers().forEach((currentpw) -> {
            jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                    hero.getSuperId(), currentpw.getPowerId());
        });
    }

    @Override
    public void addSuperOrg(Super hero) throws PersistenceException {
        hero.getOrgs().forEach((currentorg) -> {
            jdbcTemplate.update(SQL_INSERT_SUPERORG,
                    hero.getSuperId(), currentorg.getOrganizationId());
        });
    }

    @Override
    public List<Super> getAllSupersByPowerId(int powerId) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_SUPERS_BY_POWERID,
                new SuperMapper(), powerId);
    }


    private static final class PowerMapper implements RowMapper<Power> {

        public Power mapRow(ResultSet rs, int rowNum) throws SQLException {
            Power power = new Power();
            power.setPowerId(rs.getInt("powerId"));
            power.setName(rs.getString("name"));
            return power;
        }
    }

    private static final class OrgMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("organizationId"));
            org.setName(rs.getString("name"));
            org.setDescription(rs.getString("description"));
            org.setLocationId(rs.getInt("locationId"));
            return org;
        }
    }

    private static final class SuperMapper implements RowMapper<Super> {

        public Super mapRow(ResultSet rs, int rowNum) throws SQLException {
            Super hero = new Super();
            hero.setSuperId(rs.getInt("superId"));
            hero.setName(rs.getString("name"));
            hero.setDescription(rs.getString("description"));
            hero.setIsHero(rs.getBoolean("isHero"));
            return hero;
        }
    }
}
