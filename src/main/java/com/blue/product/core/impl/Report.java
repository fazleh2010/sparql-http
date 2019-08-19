/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blue.product.core.impl;

import java.net.URL;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class Report {
    private final URL url;
    private final String note;
    private final Boolean flag;
    
    public Report(URL url,Boolean flag,String note){
        this.url=url;
        this.flag=flag;
        this.note=note;
    }

    public URL getUrl() {
        return url;
    }

    public String getNote() {
        return note;
    }

    public Boolean getFlag() {
        return flag;
    }    
}
