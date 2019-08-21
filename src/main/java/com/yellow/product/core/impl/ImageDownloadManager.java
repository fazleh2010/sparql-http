/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.core.impl;

import com.yellow.product.core.api.DownLoadManager;
import com.yellow.product.exception.DownloadException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.net.URLConnection;
import com.yellow.product.core.constant.Message;
import com.yellow.product.core.constant.FileType;
import com.yellow.product.exception.LoaderException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.util.Pair;
import javax.imageio.ImageIO;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class ImageDownloadManager extends DownLoadManager implements Message, FileType {

    public ImageDownloadManager(File inputFile, String outputDir) throws DownloadException, LoaderException {
        super(inputFile, outputDir);
    }

    @Override
    public List<Report> download() throws DownloadException {
        List<Report> reports = new ArrayList<Report>();
        for (String line : inputs) {
            URL url;
            Report report = null;
            try {
                url = new URL(line);
                String name = getfileName(url);
                Pair<Boolean, String> pair = isImage(name);
                if (pair.getKey()) {
                    String extension = pair.getValue();
                    File outputFile = new File(super.downloadLocation + File.separator + name);
                    try {
                        report = urlToImageDownload(url, extension, outputFile);
                    } catch (IOException ex) {
                        report = new Report(url, Boolean.FALSE, FAIL_DOWNLOAD);
                    }
                } else {
                    report = new Report(url, Boolean.FALSE, FAIL_DOWNLOAD);
                }
            } catch (MalformedURLException ex) {
                throw new DownloadException(ex.getMessage());
            }
            reports.add(report);
        }

        return reports;
    }

    private Report urlToImageDownload(URL url, String imageType, File outputFile) throws IOException, MalformedURLException {
        BufferedImage image = ImageIO.read(url);
        if (image != null) {
            ImageIO.write(image, imageType, outputFile);
            return new Report(url, Boolean.TRUE, SUCCESSFUL_DOWNLOAD);
        } else {
            return new Report(url, Boolean.FALSE, FAIL_DOWNLOAD);
        }
    }

    private Pair<Boolean, String> isImage(String name) {
        Boolean isImage = false;
        String mimeType = URLConnection.guessContentTypeFromName(new File(name).getName());
        if (IMAGE_FILE.contains(mimeType)) {
            isImage = true;
            if (mimeType.contains("\\/")) {
                mimeType.substring(mimeType.lastIndexOf('/') + 1);
            }
        }
        return new Pair<Boolean, String>(isImage, mimeType);
    }

    private String getfileName(URL url) {
        return new File(url.getPath()).getName();
    }

}
