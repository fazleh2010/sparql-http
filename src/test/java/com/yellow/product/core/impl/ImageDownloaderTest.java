/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.core.impl;

import com.yellow.product.core.impl.ImageDownloader;
import com.yellow.product.core.impl.Report;
import com.yellow.product.core.constant.Message;
import com.yellow.product.exception.DownloadException;
import com.yellow.product.exception.LoaderException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author elahi
 */
public class ImageDownloaderTest extends TestCase {

    private String TEST_PATH = "src/test/resources";
    private final String downloadLocation = TEST_PATH + File.separator;
    private Set<String> inputs = new HashSet<String>();

    public ImageDownloaderTest() throws LoaderException {
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
     * When Url is correct. Imgage found.
     * Image downloaded successfully
     */
    public void testDownload_WhenSuccess() throws Exception {
        System.out.println("testDownload_WhenSuccess");
        inputs = new HashSet<String>();
        String  line="https://www.technicalkeeda.com/img/images/article/spring-file-upload-eclipse-setup.png";
        inputs.add(line);
        ImageDownloader instance = new ImageDownloader(inputs, downloadLocation);
        Report report = new Report(null, Boolean.TRUE, null);
        List<Report> expResult = new ArrayList<Report>();
        expResult.add(report);
        Boolean flag = expResult.iterator().next().getFlag();
        List<Report> result = instance.download();
        assertEquals(flag, result.iterator().next().getFlag());
    }
    
    /**
     * Test of download method, of class ImageDownloader.
     * When no image found in url. Url not found
     * Download fail
     */
    public void testDownload_WhenFail() throws Exception {
        System.out.println("testDownload_WhenFail");
        inputs = new HashSet<String>();
        String  line="http://mywebserver.com/images/271947.jpg";
        inputs.add(line);
        ImageDownloader instance = new ImageDownloader(inputs, downloadLocation);
        Report report = new Report(null, Boolean.FALSE, Message.FAIL_DOWNLOAD);
        List<Report> expResult = new ArrayList<Report>();
        expResult.add(report);
        Boolean flag = expResult.iterator().next().getFlag();
        List<Report> result = instance.download();
        assertEquals(flag, result.iterator().next().getFlag());
    }
    
    /**
     * Test of download method, of class ImageDownloader.
     * When no image found in url. Url not found
     * Download fail
     
    @Test(expected = com.blue.product.exception.DownloadException.class)
    public void testDownload_WhenInvalidUrl() throws DownloadException, LoaderException {
        System.out.println("testDownload_WhenFail");
        inputs = new HashSet<String>();
        String  line="test";
        inputs.add(line);
        ImageDownloader instance = new ImageDownloader(inputs, downloadLocation);
        Report report = new Report(null, Boolean.FALSE, Message.FAIL_DOWNLOAD);
        List<Report> expResult = new ArrayList<Report>();
        expResult.add(report);
        Boolean flag = expResult.iterator().next().getFlag();
        List<Report> result = instance.download();
        assertEquals(flag, result.iterator().next().getFlag());
    }*/


}
