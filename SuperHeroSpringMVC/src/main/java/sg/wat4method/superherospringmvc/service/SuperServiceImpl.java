/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import sg.wat4method.superherospringmvc.dao.BridgeDao;
import sg.wat4method.superherospringmvc.dao.OrganizationDao;
import sg.wat4method.superherospringmvc.dao.PersistenceException;
import sg.wat4method.superherospringmvc.dao.SuperDao;
import sg.wat4method.superherospringmvc.model.Organization;
import sg.wat4method.superherospringmvc.model.Power;
import sg.wat4method.superherospringmvc.model.Super;

public class SuperServiceImpl implements SuperService {

    SuperDao dao;
    OrganizationDao orgDao;
    BridgeDao bridgeDao;

    @Inject
    public SuperServiceImpl(SuperDao dao, OrganizationDao orgDao, BridgeDao bridgeDao) {
        this.dao = dao;
        this.orgDao = orgDao;
        this.bridgeDao = bridgeDao;
    }

    @Override
    public List<Super> getAllSupers() throws DataNotFoundException {
        List<Super> superList;
        try {
            superList = dao.getAll();
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
        return superList;
    }

    @Override
    public List<Super> getAllHeroes() throws DataNotFoundException {
        try {
            return dao.getHeroes();
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public List<Super> getAllVillans() throws DataNotFoundException {
        try {
            return dao.getVillains();
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public Super getSuperById(int superId) throws DataNotFoundException {
        Super hero;
        try {
            hero = dao.getSuperById(superId);
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
        return hero;
    }

    @Override
    public void editSuper(Super hero) throws DataIntegrityViolationException {
        try {
            bridgeDao.deleteSuperOrg(hero.getSuperId());
            bridgeDao.deleteSuperPower(hero.getSuperId());
            dao.editSuper(hero);
            bridgeDao.addSuperOrg(hero);
            bridgeDao.addSuperPower(hero);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public void deleteSuper(int superId) throws DataIntegrityViolationException {
        try {
            bridgeDao.deleteSuperPower(superId);
            bridgeDao.deleteSuperOrg(superId);
            dao.deleteSuper(superId);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");

        }
    }

    @Override
    public Organization getOrgById(int organizationId) throws DataNotFoundException {
        try {
            return orgDao.getById(organizationId);
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public List<Organization> getAllOrgs() throws DataNotFoundException {
        try {
            return orgDao.getAll();
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public void editOrg(Organization org) throws DataIntegrityViolationException {
        try {
            orgDao.editOrg(org);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public void deleteOrg(int organizationId) throws DataIntegrityViolationException {
        try {
            orgDao.deleteOrg(organizationId);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public List<Power> getAllPowersBySuper(int superId) throws DataNotFoundException {
        try {
            return bridgeDao.getAllPowerBySuperId(superId);
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public Super createSuper(Super hero) throws DataIntegrityViolationException {
        try {
            Super returnHero = dao.addSuper(hero);
            bridgeDao.addSuperPower(hero);
            bridgeDao.addSuperOrg(hero);
            return returnHero;
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public List<Power> getAllPowers() throws DataNotFoundException {
        List<Power> powers;
        try {
            powers = bridgeDao.getAllPowers();
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
        return powers;
    }

    @Override
    public Power createPower(Power power) throws DataIntegrityViolationException {
        try {
            return bridgeDao.addPower(power);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public void deletePower(int powerId) throws DataIntegrityViolationException {
        try {
  
            bridgeDao.deletePower(powerId);
    
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public Power getPowerById(int powerId) throws DataNotFoundException {
        try {
            return bridgeDao.getPowerById(powerId);
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public void editPower(Power power) throws DataIntegrityViolationException {
        try {
            bridgeDao.editPower(power);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public Organization createOrg(Organization org) throws DataIntegrityViolationException {
        try {
            return orgDao.addOrg(org);
        } catch (PersistenceException ex) {
            throw new DataIntegrityViolationException("Unable to insert data.");
        }
    }

    @Override
    public List<Organization> getAllOrgsBySuper(int superId) throws DataNotFoundException {
        try {
            return bridgeDao.getAllOrgsBySuper(superId);
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public List<Super> getSupersByOrgId(int organizationId) throws DataNotFoundException {
        try {
            return bridgeDao.getAllSupersByOrgId(organizationId);
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

    @Override
    public List<Super> getSupersByPowerId(int powerId) throws DataNotFoundException {
        try {
            return bridgeDao.getAllSupersByPowerId(powerId);
        } catch (PersistenceException ex) {
            throw new DataNotFoundException("Unable to fetch data.");
        }
    }

}
