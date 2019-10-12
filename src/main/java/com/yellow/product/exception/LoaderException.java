/**
 * <h1>Loader Exception</h1>
 * This exception class for input file processing.
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
