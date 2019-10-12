/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class Report {

    private URL url;
    private String note;
    private Status status;

    public enum Status {
        SUCCESS, FAIL;
    }

    public Report() {

    }

    public Report(URL url, Boolean flag, String note) {
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
