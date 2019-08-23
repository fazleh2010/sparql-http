/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.core.impl;

import com.yellow.product.core.constant.Message;
import com.yellow.product.exception.LoaderException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class ImageDownloaderTest extends TestCase {

    private String downloadLocation = "src/test/resources";
    private File inputFile = new File(downloadLocation + File.separator + "linksSuccess.txt.txt");

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
     * Test of download method, of class ImageDownloader. When Url is correct.
     * Imgage found. Image downloaded successfully
     */
    public void testDownload_WhenSuccess() throws Exception {
        System.out.println("testDownload_WhenSuccess");
        inputFile = new File(downloadLocation + File.separator + "linksSuccess.txt");
        ImageDownloadManager instance = new ImageDownloadManager(inputFile, downloadLocation);
        Report report = new Report(null, Boolean.TRUE, null);
        List<Report> expResult = new ArrayList<Report>();
        expResult.add(report);
        Boolean flag = expResult.iterator().next().getFlag();
        List<Report> result = instance.download();
        assertEquals(flag, result.iterator().next().getFlag());
    }

    /**
     * Test of download method, of class ImageDownloader. When no image found in
     * url. Url not found Download fail
     */
    public void testDownload_WhenFail() throws Exception {
        System.out.println("testDownload_WhenFail");
        inputFile = new File(downloadLocation + File.separator + "linksFail.txt");
        ImageDownloadManager instance = new ImageDownloadManager(inputFile, downloadLocation);
        Report report = new Report(null, Boolean.FALSE, Message.FAIL_DOWNLOAD);
        List<Report> expResult = new ArrayList<Report>();
        expResult.add(report);
        Boolean flag = expResult.iterator().next().getFlag();
        List<Report> result = instance.download();
        assertEquals(flag, result.iterator().next().getFlag());
    }

    /**
     * Test of download method, of class ImageDownloader. When no image found in
     * url. Url not found Download fail
     *
     * @Test(expected = com.blue.product.exception.DownloadException.class)
     * public void testDownload_WhenInvalidUrl() throws DownloadException,
     * LoaderException { System.out.println("testDownload_WhenFail"); inputs =
     * new HashSet<String>(); String line="test"; inputs.add(line);
     * ImageDownloader instance = new ImageDownloader(inputs, downloadLocation);
     * Report report = new Report(null, Boolean.FALSE, Message.FAIL_DOWNLOAD);
     * List<Report> expResult = new ArrayList<Report>(); expResult.add(report);
     * Boolean flag = expResult.iterator().next().getFlag(); List<Report> result
     * = instance.download(); assertEquals(flag,
     * result.iterator().next().getFlag()); }
     */
}
