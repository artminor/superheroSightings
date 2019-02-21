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
import sg.wat4method.superherospringmvc.model.Organization;

public class OrganizationDaoDbImpl implements OrganizationDao {

    private static final String SQL_INSERT_ORGANIZATION = "insert into superherosightings.organization "
            + "(name, description, locationId) VALUES (?,?,?)";
    private static final String SQL_SELECT_ORGANIZATION = "select * from organization where organizationId = ?";
    private static final String SQL_SELECT_ALL_ORGANIZATIONS = "select * from organization order by organizationId desc";
    private static final String SQL_UPDATE_ORGANIZATION = "update organization set name = ?, description = ?, "
            + "locationId = ? where organizationId = ?";
    private static final String SQL_DELETE_ORGANIZATION = "delete from organization where organizationId = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Organization> getAll() throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrgMapper());
    }

    @Override
    public Organization getById(int organizationId) throws PersistenceException {
        return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                new OrgMapper(), organizationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization addOrg(Organization org) throws PersistenceException {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                org.getName(),
                org.getDescription(),
                org.getLocationId());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the hero object and return it
        org.setOrganizationId(newId);
        return org;
    }

    @Override
    public void editOrg(Organization org) throws PersistenceException {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                org.getName(),
                org.getDescription(),
                org.getLocationId(),
                org.getOrganizationId());
    }

    @Override
    public void deleteOrg(int organizationId) throws PersistenceException {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
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

}
