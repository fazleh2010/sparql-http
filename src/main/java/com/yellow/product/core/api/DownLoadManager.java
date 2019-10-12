/**
 * <h1>Download Manager</h1>
 * The DownloadManager is a abstract class for download application. The input
 * file is processed. The download function has to be implemented to subclass.
 * The result of download is displayed here.
 */
package com.yellow.product.core.api;

import com.yellow.product.core.impl.Report;
import com.yellow.product.core.impl.InputLoader;
import com.yellow.product.core.impl.ImageDownloadReport;
import com.yellow.product.exception.DownloadException;
import com.yellow.product.exception.LoaderException;
import com.yellow.product.utils.FileNameUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public abstract class DownLoadManager {

    public final String inputFileName;
    public final Set<String> inputs;
    public final String downloadLocation;
    public Report report;

    public DownLoadManager(File inputFile) throws DownloadException, LoaderException {
        this.inputFileName = inputFile.getName();
        Loader loader = new InputLoader(inputFile);
        this.inputs = loader.getInputs();
        this.downloadLocation = loader.getInputLocation();
        this.report = this.download();
        this.display();
    }

    /**
     * Returns reports of all the inputs, whether the resources are downloaded
     * or not (true or false) and note attached.
     * <p>
     * The method downloads the resources (i.e. image or file or anything else).
     * This method has to be implemented in subclass for specific applications
     *
     * @return it returns download reports. That is the urls, whether they are
     * downloaded or not, and a note attached.
     */
    public abstract Report download() throws DownloadException;

    /*/**
     * display the results of download application
     *
     */
    public void display() {
        try {
            JAXBContext jContext = JAXBContext.newInstance(Report.class);
            Marshaller marshallObj = jContext.createMarshaller();
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallObj.marshal(report, new FileOutputStream(report.getDownloadReportFileName()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
