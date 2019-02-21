/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.service;

import java.util.List;
import sg.wat4method.superherospringmvc.model.Organization;
import sg.wat4method.superherospringmvc.model.Power;
import sg.wat4method.superherospringmvc.model.Super;
import sg.wat4method.superherospringmvc.model.SuperOrg;
import sg.wat4method.superherospringmvc.model.SuperPower;

/**
 *
 * @author Jun
 */
public interface SuperService {

    //super methods
    List<Super> getAllSupers() throws DataNotFoundException;

    List<Super> getAllHeroes() throws DataNotFoundException;

    List<Super> getAllVillans() throws DataNotFoundException;

    Super createSuper(Super hero) throws DataIntegrityViolationException;

    Super getSuperById(int superId) throws DataNotFoundException;

    void editSuper(Super hero) throws DataIntegrityViolationException;

    void deleteSuper(int superId) throws DataIntegrityViolationException;

    //org methods
    Organization getOrgById(int organizationId) throws DataNotFoundException;

    List<Organization> getAllOrgs() throws DataNotFoundException;

    void editOrg(Organization org) throws DataIntegrityViolationException;

    void deleteOrg(int organizationId) throws DataIntegrityViolationException;

    //bridge table methods
    List<Power> getAllPowersBySuper(int superId) throws DataNotFoundException;

    List<Organization> getAllOrgsBySuper(int superId) throws DataNotFoundException;

    //service layer passthrough for dao CRUD
    Power createPower(Power power) throws DataIntegrityViolationException;

    void editPower(Power power) throws DataIntegrityViolationException;

    List<Power> getAllPowers() throws DataNotFoundException;

    Power getPowerById(int powerId) throws DataNotFoundException;

    void deletePower(int powerId) throws DataIntegrityViolationException;

    public Organization createOrg(Organization org) throws DataIntegrityViolationException;

    public List<Super> getSupersByOrgId(int organizationId) throws DataNotFoundException;

    public List<Super> getSupersByPowerId(int powerId) throws DataNotFoundException;
}
