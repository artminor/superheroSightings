/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import sg.wat4method.superherospringmvc.model.Location;

/**
 *
 * @author Jun
 */
public class LocationDaoDbImplTest {

    LocationDao locationDaoTest;

    public LocationDaoDbImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        locationDaoTest = ctx.getBean("locationDaoTest", LocationDao.class);
    }

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationConext.xml");
//        locationDaoTest = ctx.getBean("locationDaoTest", LocationDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class LocationDaoDbImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
    }

    /**
     * Test of getAll method, of class LocationDaoDbImpl.
     */
    @Test
    public void testGetAll() {
    }

    /**
     * Test of getById method, of class LocationDaoDbImpl.
     */
    @Test
    public void testGetById() {
    }

    /**
     * Test of addLocation method, of class LocationDaoDbImpl.
     */
    @Test
    public void testAddLocation() {
//        Location loc = new Location();
//        loc.setName("Macy");
//        loc.setLatitude(new BigDecimal("22.333"));
//        loc.setLongitude(new BigDecimal("32.333"));
//        loc.setStreet("123 E bway");
//        loc.setCity("NYC");
//        loc.setState("NY");
//        loc.setZip("10002");
//        loc.setDescription("none");
    }

    /**
     * Test of editLocation method, of class LocationDaoDbImpl.
     */
    @Test
    public void testEditLocation() {
    }

    /**
     * Test of deleteLocation method, of class LocationDaoDbImpl.
     */
    @Test
    public void testDeleteLocation() {
    }

    /**
     * Test of getLocationByName method, of class LocationDaoDbImpl.
     */
    @Test
    public void testGetLocationByName() {
    }

}
