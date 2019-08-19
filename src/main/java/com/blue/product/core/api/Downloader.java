/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blue.product.core.api;

import com.blue.product.core.impl.Report;
import com.blue.product.exception.DownloadException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public abstract class Downloader {

    public final Set<String> inputs;
    public final String downloadLocation;
    public List<Report> reports = new ArrayList<Report>();

    public Downloader(Set<String> inputs,String downloadLocation) throws DownloadException {
        this.inputs = inputs;
        this.downloadLocation = downloadLocation; 
        this.reports = download();
    }

    public abstract List<Report> download() throws DownloadException;

    public void display() {
        for (Report report : reports) {
            System.out.println(report.getUrl() + " " + report.getUrl() + " " + report.getNote());
            System.out.println();

        }

    }

    public List<Report> getOutputs() {
        return reports;
    }

}
