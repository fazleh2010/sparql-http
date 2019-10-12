/**
 * <h1>Loader</h1>
 * The Loader is a interface to get the result of input file processing.
 *
 */
package com.yellow.product.core.api;

import java.util.Set;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface Loader {

    /**
     * This method gets all lines of a the input file.
     */
    public Set<String> getInputs();

    /**
     * This method gets the location of input file.
     */
    public String getInputLocation();

}
