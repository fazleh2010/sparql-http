/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blue.product.exception;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class DownloadException extends Exception {

  public DownloadException() {
  }

  public DownloadException(String message) {
    super(message);
  }

  public DownloadException(Throwable cause) {
    super(cause);
  }

  public DownloadException(String message, Throwable cause) {
    super(message, cause);
  }
    
}
