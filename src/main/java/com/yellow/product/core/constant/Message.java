/*
 * 
 */
package com.yellow.product.core.constant;

/**
 *
 * @author Mohammad Fazleh Elahi
 *
 * This is a interface contains messages
 */
public interface Message {

    public static final String NOT_TEXT_FILE = "The input file should be text file!! ";
    public static final String EMPTY_FILE = "The file can not be empty!! ";
    public static final String SUCCESSFUL_DOWNLOAD = "The image is successfully stored!! ";
    public static final String FAIL_DOWNLOAD_NO_IMAGE = "No image found to download!!";
    public static final String FAIL_DETECT_IMAGE_FILE = "Failed to detect the image file!!";
    public static final String FAIL_DOWNLOAD_GENERAL = "The download is failed!! ";
    public static final String INVALID_URL = "Invalid url!! ";
    public static final String NOT_IMAGE_FILE = "This is not a image file!! ";
    public static final String NOT_SUPPORTED_URL = "  format is not supported yet !!";
    public static final String INTERNET_CONNECTION_PROBLEM = "Internet is not connected!!!";
    public static final String MISSING_INPUT_FILE = "parameter for input file is missing!!";
    public static final String TOO_MANY_PARAMETERS =  "Too many parameters! only one paramter is permitted!!";
    public static final String FAILED_TO_WRITE_XML = "failed to write report in xml file!!!!";
    public static final String CANNOT_WRITE_XML="Can not read or write XML file!!!";

}
