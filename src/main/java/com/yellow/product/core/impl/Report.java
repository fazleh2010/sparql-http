/*
 * 
 */
package com.yellow.product.core.impl;

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

    @Override
    public String toString() {
        return "Report{" + "url=" + url + ", note=" + note + ", flag=" + flag + '}';
    }
}
