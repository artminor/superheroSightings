package sg.wat4method.superherospringmvc.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jun
 */
public class DataIntegrityViolationException extends Exception {

//    public DataIntegrityViolationException(String string, Throwable thrwbl) {
//        super(string, thrwbl);
//    }

    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
