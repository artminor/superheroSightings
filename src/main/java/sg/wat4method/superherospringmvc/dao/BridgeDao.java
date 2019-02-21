/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

import java.util.List;
import sg.wat4method.superherospringmvc.model.Organization;
import sg.wat4method.superherospringmvc.model.Power;
import sg.wat4method.superherospringmvc.model.Super;

/**
 *
 * @author Jun
 */
public interface BridgeDao {

    List<Organization> getAllOrgsBySuper(int superId) throws PersistenceException;

    List<Super> getAllSupersByOrgId(int organizationId) throws PersistenceException;

    List<Super> getAllSupersByPowerId(int powerId) throws PersistenceException;

    //POWER METHODS
    List<Power> getAllPowers() throws PersistenceException;

    List<Power> getAllPowerBySuperId(int superId) throws PersistenceException;

    Power addPower(Power power) throws PersistenceException;

    Power getPowerById(int powerId) throws PersistenceException;

    void editPower(Power power) throws PersistenceException;

    void deletePower(int powerId) throws PersistenceException;

    //bridge table add/deletes
    void addSuperPower(Super hero) throws PersistenceException;

    void addSuperOrg(Super hero) throws PersistenceException;

    void deleteSuperPower(int superId) throws PersistenceException;

    void deleteSuperOrg(int superId) throws PersistenceException;


}
