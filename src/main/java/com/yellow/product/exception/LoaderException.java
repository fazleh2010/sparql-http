/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.exception;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LoaderException extends Exception {

  public LoaderException() {
  }

  public LoaderException(String message) {
    super(message);
  }

  public LoaderException(Throwable cause) {
    super(cause);
  }

  public LoaderException(String message, Throwable cause) {
    super(message, cause);
  }
    
}