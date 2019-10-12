/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.core.impl;

import com.yellow.product.core.api.Reports;
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
@XmlRootElement(name = "reports")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportGeneration implements Reports {

    private static final String REPORT = "_Report.xml";
    private final String reportFileName;
    @XmlElement(name = "report")
    private List<Report> reports = new ArrayList<Report>();

    public ReportGeneration() {
        this.reportFileName = REPORT;
    }

    public ReportGeneration(String fileName, List<Report> reports) {
        this.reportFileName = fileName + REPORT;
        this.reports = reports;
    }

    @Override
    public List<Report> getreports() {
        return reports;
    }

    @Override
    public String getReportFileName() {
        return reportFileName;
    }

}
