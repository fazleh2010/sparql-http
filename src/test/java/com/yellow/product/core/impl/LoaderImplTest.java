/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.core.impl;

import com.yellow.product.core.api.Loader;
import com.yellow.product.exception.LoaderException;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;

/**
 *
 * @author elahi
 */
public class LoaderImplTest extends TestCase {
    private String inputLocation="src/test/resources"; 
    private File inputFile = new File(inputLocation+File.separator+"links.txt");
    
    public LoaderImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getInputs method, of class LoaderImpl.
     */
    public void testGetInputs() throws LoaderException {
        System.out.println("getInputs");
        String url="http://mywebserver.com/images/271947.jpg";
        Loader instance = new LoaderImpl(inputFile);
        Set<String> expResult = new HashSet<String>();
        expResult.add(url);
        Set<String> result = instance.getInputs();
        String line=result.iterator().next();
        assertEquals(line, result.iterator().next());
    }

}
