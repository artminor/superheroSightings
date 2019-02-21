/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

import java.sql.SQLException;
import java.util.List;
import sg.wat4method.superherospringmvc.model.User;

/**
 *
 * @author Jun
 */
public interface UserDao {

    public User addUser(User newUser) throws PersistenceException;

    public void deleteUser(String username) throws PersistenceException;

    public List<User> getAllUsers() throws PersistenceException;

    public User getUserByName(String username) throws PersistenceException;

}
