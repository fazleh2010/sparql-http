/**
 * <h1>Download Exception</h1>
 * This is a class for download exception.
 */
package com.yellow.product.exception;

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
