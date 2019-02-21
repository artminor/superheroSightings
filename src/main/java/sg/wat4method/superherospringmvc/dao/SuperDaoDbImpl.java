/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

import sg.wat4method.superherospringmvc.model.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SuperDaoDbImpl implements SuperDao {

    private static final String SQL_SELECT_ALL_SUPER
            = "select * from super order by superId DESC";
    private static final String SQL_SELECT_SUPER
            = "select * from super where superId = ? ";
    private static final String SQL_SELECT_SUPER_BY_ISHERO_VILLAIN
            = "select * from super where isHero = 0";
    private static final String SQL_SELECT_SUPER_BY_ISHERO_HERO
            = "select * from super where isHero = 1";
    private static final String SQL_INSERT_SUPER
            = "insert into super "
            + "(name, description, isHero) "
            + "values (?, ?, ?)";
    private static final String SQL_UPDATE_SUPER
            = "update super set "
            + "name = ?, description = ?, isHero = ? "
            + "where superId = ?";
    private static final String SQL_DELETE_SUPER
            = "delete from super where superId = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Super> getAll() throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPER,
                new SuperMapper());
    }

    @Override
    public List<Super> getHeroes() throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_SUPER_BY_ISHERO_HERO,
                new SuperMapper());
    }

    @Override
    public List<Super> getVillains() throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_SUPER_BY_ISHERO_VILLAIN,
                new SuperMapper());
    }

    @Override
    public Super getSuperById(int superId) throws PersistenceException {
        return jdbcTemplate.queryForObject(SQL_SELECT_SUPER,
                new SuperMapper(), superId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Super addSuper(Super hero) throws PersistenceException {
        jdbcTemplate.update(SQL_INSERT_SUPER,
                hero.getName(),
                hero.getDescription(),
                hero.getIsHero());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the hero object and return it
        hero.setSuperId(newId);

        return hero;
    }

    @Override
    public void editSuper(Super hero) throws PersistenceException {
        jdbcTemplate.update(SQL_UPDATE_SUPER,
                hero.getName(),
                hero.getDescription(),
                hero.getIsHero(),
                hero.getSuperId());
    }

    @Override
    public void deleteSuper(int superId) throws PersistenceException {
        jdbcTemplate.update(SQL_DELETE_SUPER, superId);
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
