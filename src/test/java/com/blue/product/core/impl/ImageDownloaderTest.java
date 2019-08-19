/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blue.product.core.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author elahi
 */
public class ImageDownloaderTest extends TestCase {
    
    private String TEST_PATH="src/test/resources"; 
    private File inputFile = new File(TEST_PATH+File.separator+"links.txt");
    
    public ImageDownloaderTest(String testName) {
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
     * Test of download method, of class ImageDownloader.
     */
    public void testDownload() throws Exception {
        System.out.println("download");
        ImageDownloader instance =new ImageDownloader(inputFile);
        Report report= new Report(null,Boolean.TRUE,null);
        List<Report> expResult = new ArrayList<Report>();
        expResult.add(report);
        Boolean flag=expResult.iterator().next().getFlag();
        List<Report> result = instance.download();
        assertEquals(flag,result.iterator().next().getFlag());       
    }
    
}
