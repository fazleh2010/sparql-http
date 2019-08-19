/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blue.product.core.api;

import com.blue.product.core.impl.LoaderImpl;
import com.blue.product.core.impl.Report;
import com.blue.product.exception.DownloadException;
import com.blue.product.exception.LoaderException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 *
 * @author Mohammad Fazleh Elahi
 */
public abstract  class Downloader{

    public final Set<String> inputs;
    public final String downloadLocation;
    public List<Report> outputs=new ArrayList<Report>();

    public Downloader(File inputfile) throws DownloadException, LoaderException {
        Loader resourceLoader=new LoaderImpl(inputfile);
        this.inputs=resourceLoader.getInputs();
        this.downloadLocation = inputfile.getAbsoluteFile().getParent();
        this.outputs=download();
    }
    public Downloader(File inputfile,String outputDir) throws DownloadException, LoaderException {
        Loader resourceLoader=new LoaderImpl(inputfile);
        this.inputs=resourceLoader.getInputs();
        this.downloadLocation = outputDir;
        this.outputs=download();
    }
    
    public abstract List<Report> download()throws DownloadException;

    public List<Report> getOutputs() {
        return outputs;
    }


}

