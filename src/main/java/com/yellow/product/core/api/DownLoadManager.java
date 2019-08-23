/**
 * <h1>Download Manager</h1>
 * The DownloadManager is a abstract class for download application.
 * The input file is processed. 
 * The download function has to be implemented to subclass. 
 * The result of download is displayed here.
 */
package com.yellow.product.core.api;

import com.yellow.product.core.impl.InputLoader;
import com.yellow.product.core.impl.Report;
import com.yellow.product.exception.DownloadException;
import com.yellow.product.exception.LoaderException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public abstract class DownLoadManager{

    public final Set<String> inputs;
    public final String downloadLocation;
    private List<Report> outputReports = new ArrayList<Report>();

    public DownLoadManager(File inputFile) throws DownloadException, LoaderException {
        Loader loader = new InputLoader(inputFile);
        this.inputs = loader.getInputs();
        this.downloadLocation = loader.getInputLocation();
        this.outputReports=download();
    }

    /**
     * Returns reports of all the inputs, whether the resources are downloaded
     * or not (true or false) and note attached.
     * <p>
     * The method downloads the resources (i.e. image or file or anything else).
     * This method has to be implemented in subclass for specific applications
     *
     * @return it returns download reports. That is the urls, whether they are
     * downloaded or not, and note attached.
     */
    public abstract List<Report> download() throws DownloadException;

    /**
     * display download reports of all the urls.
     *
     */
    public void display() {
        System.out.println("\nThe report of download application!!"+ "\n");
        String line="";
        for (Report report : this.outputReports) {
            if (report.getFlag()) {
                line = "url=" + report.getUrl()  + "   " + report.getNote() ;
            } else {
                line = "url=" + report.getUrl() + "   " + report.getNote();
            }
            System.out.println(line);
        }

    }
}
