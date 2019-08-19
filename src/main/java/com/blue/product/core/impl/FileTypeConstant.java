/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blue.product.core.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author elahi
 */
public interface FileTypeConstant {

    public static final String TEXT_FILE = "text/plain";
    public static final String IMAGE_JPG = "image/jpeg";
    public static final String IMAGE_PNG = "image/png";
    public static final String IMAGE_BMP = "image/bmp";
    public static final String IMAGE_GIF = "image/gif";
    public static final Set<String> IMAGE_FILE = new HashSet<String>(Arrays.asList(IMAGE_JPG,
            IMAGE_PNG, IMAGE_BMP, IMAGE_GIF));

}
