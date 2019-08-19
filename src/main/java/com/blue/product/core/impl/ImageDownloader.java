/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blue.product.core.impl;

import com.blue.product.core.api.Downloader;
import com.blue.product.exception.DownloadException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import com.blue.product.exception.LoaderException;
import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.blue.product.core.constant.Message;
import com.blue.product.core.constant.FileType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class ImageDownloader extends Downloader implements Message, FileType {

    public ImageDownloader(File inputFile) throws DownloadException, LoaderException {
        super(inputFile);
    }

    public ImageDownloader(File inputFile, String outputDir) throws DownloadException, LoaderException {
        super(inputFile, outputDir);
    }

    @Override
    public List<Report> download() throws DownloadException {
        List<Report> reports = new ArrayList<Report>();

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
                    reports.add(new Report(url, Boolean.TRUE, SUCCESSFUL_DOWNLOAD));
                } else {
                    reports.add(new Report(url,Boolean.TRUE, FAIL_DOWNLOAD));
                }

            } catch (MalformedURLException ex) {
                throw new DownloadException(ex.getMessage());
            } catch (IOException ex) {
                throw new DownloadException(ex.getMessage());
            }
        }

        return reports;
    }

    private boolean isImage(String url) {
        if (IMAGE_FILE.contains(url)) {
            return true;
        }
        return false;

    }

}
