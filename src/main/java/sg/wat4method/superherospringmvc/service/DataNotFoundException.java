/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.service;

/**
 *
 * @author Jun
 */
public class DataNotFoundException extends Exception {
//
//    public DataNotFoundException(String string, Throwable thrwbl) {
//        super(string, thrwbl);
//    }
//
//    
    
    public DataNotFoundException(String message) {
        super(message);
    }
}
