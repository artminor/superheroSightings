/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

import java.sql.SQLException;
import java.util.List;
import sg.wat4method.superherospringmvc.model.Organization;

/**
 *
 * @author Jun
 */
public interface OrganizationDao {

    List<Organization> getAll() throws PersistenceException;

    Organization getById(int organizationId) throws PersistenceException;

    Organization addOrg(Organization org) throws PersistenceException;

    void editOrg(Organization org) throws PersistenceException;

    void deleteOrg(int organizationId) throws PersistenceException;
}
