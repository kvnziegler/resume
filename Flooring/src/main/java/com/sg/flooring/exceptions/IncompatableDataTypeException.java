/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.exceptions;

/**
 *
 * @author kvnzi
 */
public class IncompatableDataTypeException extends Exception {

    public IncompatableDataTypeException(String message) {
        super(message);
    }

    public IncompatableDataTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}

