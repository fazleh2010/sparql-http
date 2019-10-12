/**
 * <h1>Image download Report</h1>
 * The ImageDownloadReport class store the result (SUCESS or FAIL) of an url.
 */
package com.yellow.product.core.impl;

import java.net.URL;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
@XmlRootElement
public class ImageDownloadReport {

    //the url of the image
    private URL url;
    //the mentions the detail (or reason) if fails
    private String note;
    //the image download success or fail
    private Status status;

    public enum Status {
        SUCCESS, FAIL;
    }

    public ImageDownloadReport() {

    }

    public ImageDownloadReport(URL url, Boolean flag, String note) {
        this.url = url;
        if (flag) {
            status = Status.SUCCESS;
        } else {
            status = Status.FAIL;
        }
        this.note = note;
    }

    @XmlAttribute
    public URL getUrl() {
        return url;
    }

    @XmlElement
    public String getNote() {
        return note;
    }

    @XmlElement
    public Status getstatus() {
        return this.status;
    }

}
