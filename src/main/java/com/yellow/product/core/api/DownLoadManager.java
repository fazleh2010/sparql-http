/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.core.api;

import com.yellow.product.core.impl.LoaderImpl;
import com.yellow.product.core.impl.Report;
import com.yellow.product.exception.DownloadException;
import com.yellow.product.exception.LoaderException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public abstract class DownLoadManager {

    public final Set<String> inputs;
    public final String downloadLocation;
    public List<Report> outputReports = new ArrayList<Report>();

    public DownLoadManager(File inputFile, String OutputLocation) throws DownloadException, LoaderException {
        Loader loader = new LoaderImpl(inputFile);
        this.inputs=loader.getInputs();
        this.downloadLocation = loader.getResourceLocation();
        this.outputReports=download();
    }

    public abstract List<Report> download() throws DownloadException;

    public void display() {
        for (Report report : this.outputReports) {
            System.out.println(report.getUrl() + " " + report.getNote());
            System.out.println();
        }

    }

    public List<Report> getReports() {
        return outputReports;
    }

}
