/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import sg.wat4method.superherospringmvc.model.Power;
import sg.wat4method.superherospringmvc.model.Super;

/**
 *
 * @author Indigo
 */
public interface SuperDao {

    List<Super> getHeroes() throws PersistenceException;

    List<Super> getVillains() throws PersistenceException;

    List<Super> getAll() throws PersistenceException;

    Super getSuperById(int superId) throws PersistenceException;

    Super addSuper(Super hero) throws PersistenceException;

    void editSuper(Super hero) throws PersistenceException;

    void deleteSuper(int superId) throws PersistenceException;

}
