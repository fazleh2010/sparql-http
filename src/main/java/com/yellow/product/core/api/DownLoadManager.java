/**
 * <h1>Download Manager</h1>
 * The DownloadManager is a abstract class for download application. The input
 * file is processed. The specific download application has to be implemented to
 * subclass. The result (download status, note) of download application are
 * written in a xml file.
 */
package com.yellow.product.core.api;

import com.yellow.product.core.constant.Message;
import com.yellow.product.core.impl.Report;
import com.yellow.product.core.impl.InputLoader;
import com.yellow.product.exception.DownloadException;
import com.yellow.product.exception.LoaderException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public abstract class DownLoadManager implements Message {

    public final String inputFileName;
    public final Set<String> inputs;
    public final String downloadLocation;
    public Report report;

    public DownLoadManager(File inputFile) throws LoaderException, DownloadException {
        this.inputFileName = inputFile.getName();
        Loader loader = new InputLoader(inputFile);
        this.inputs = loader.getInputs();
        this.downloadLocation = loader.getInputLocation();
        this.report = this.download();
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
     * Write the results of download application in .xml file. 
     *
     */
    public void display() throws DownloadException, LoaderException {
        try {
            JAXBContext jContext = JAXBContext.newInstance(Report.class);
            Marshaller marshallObj = jContext.createMarshaller();
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallObj.marshal(report, new FileOutputStream(report.getDownloadReport()));
        } catch (JAXBException ex) {
            throw new DownloadException(FAILED_TO_WRITE_XML + " " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            throw new LoaderException(CANNOT_WRITE_XML + " " +ex.getMessage());
        }
    }

}
