/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blue.product.core.impl;

import com.blue.product.core.api.Downloader;
import com.blue.product.core.api.Loader;
import com.blue.product.exception.DownloadException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TreeMap;
import com.blue.product.exception.ErrorMessage;
import com.blue.product.exception.LoaderException;
import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class ImageDownloader extends Downloader implements ErrorMessage, FileTypeConstant {

    public ImageDownloader(File inputFile) throws DownloadException, LoaderException {
        super(inputFile);
    }
    public ImageDownloader(File inputFile,String outputDir) throws DownloadException, LoaderException {
        super(inputFile,outputDir);
    }

    @Override
    public TreeMap<String, Boolean> download() throws DownloadException {
        TreeMap<String, Boolean> downloadedFileNames = new TreeMap<String, Boolean>();

        for (String line : this.inputs) {
            String name = line.substring(line.lastIndexOf('/') + 1);
            Path target = Paths.get(super.downloadLocation + File.separator + name);
            URL url;
            try {
                url = new URL(line);
                URLConnection connection = url.openConnection();
                connection.setRequestProperty("User-Agent", "Java");
                String mimeType = connection.getContentType();
                if (isImage(mimeType)) {
                    InputStream is = connection.getInputStream();
                    Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
                }

            } catch (MalformedURLException ex) {
                throw new DownloadException(ex.getMessage());
            } catch (IOException ex) {
                throw new DownloadException(ex.getMessage());
            }
        }

        return downloadedFileNames;
    }

    private boolean isImage(String url) {
        if (IMAGE_FILE.contains(url)) {
            return true;
        }
        return false;

    }

}
