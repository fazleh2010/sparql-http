/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.core.api;

import java.util.Set;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface Loader {

    /**
     * This method gets contents of the input file. in this case all lines of a
     * file.
     */
    public Set<String> getInputs();

    /**
     * This method gets the location of input file. in this case the path of
     * input file
     */
    public String getResourceLocation();

}
