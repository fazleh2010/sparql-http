/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.core.api;

import com.yellow.product.core.impl.Report;
import java.util.List;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface Reports {

    /**
     * This method gets report of download application.
     */
    public List<Report> getreports();

    /**
     * This method gets the file Name of report.
     */
    public String getReportFileName();

}
