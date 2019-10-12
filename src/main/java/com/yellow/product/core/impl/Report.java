/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.core.impl;

import com.yellow.product.utils.FileNameUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elahi
 */
@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
public class Report {

    private static final String REPORT = "_Report.xml";
    private String inputFileName;
    private String downloadReportFileName;
    private String downloadLocation;

    @XmlElement(name = "element")
    private List<ImageDownloadReport> reports = new ArrayList<ImageDownloadReport>();

    public Report() {
    }

    public Report(String downloadLocation, String inputFileName, List<ImageDownloadReport> reports) {
        this.inputFileName = downloadLocation + File.separator + inputFileName;
        this.downloadLocation = downloadLocation;
        this.downloadReportFileName = downloadLocation + File.separator + FileNameUtils.getFileNameWithoutExtension(inputFileName) + REPORT;
        this.reports = reports;
    }

    public List<ImageDownloadReport> getreports() {
        return reports;
    }

    public String getDownloadReportFileName() {
        return downloadReportFileName;
    }

    public String getDownloadLocation() {
        return downloadLocation;
    }

    public String getInputFileName() {
        return inputFileName;
    }

}
