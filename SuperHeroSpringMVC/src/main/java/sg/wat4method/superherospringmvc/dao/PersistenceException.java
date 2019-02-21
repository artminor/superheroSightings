/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

/**
 *
 * @author Jun
 */
public class PersistenceException extends Exception {

    public PersistenceException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public PersistenceException(String message) {
        super(message);
    }

}
