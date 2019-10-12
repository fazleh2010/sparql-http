/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.utils;

import static com.yellow.product.core.constant.MimeType.IMAGE_FILES;
import java.io.File;
import java.net.URL;
import javafx.util.Pair;

/**
 *
 * @author elahi
 */
public class FileNameUtils {

    public static String getFileName(URL url) {
        return new File(url.getPath()).getName();
    }

    public static String getFileNameWithoutExtension(String fileName) {
        return fileName.replaceFirst("[.][^.]+$", "");
    }

    public static Pair<Boolean, String> getExtensionFromMimeType(String mimetype) throws Exception {
        if (mimetype.contains("/")) {
            mimetype = mimetype.split("/")[1];
            return new Pair<Boolean, String>(Boolean.TRUE, mimetype);

        }
        return new Pair<Boolean, String>(Boolean.FALSE, mimetype);
    }

}
