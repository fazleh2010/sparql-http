/**
 * <h1>Image download Manager</h1>
 * The ImageDownloadManager is subclass of DownloadManager. This program takes a
 * input file and downloads and save all images. The input file is extracted at
 * super class. The results of download displayed at super class.
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
import com.yellow.product.exception.LoaderException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import com.yellow.product.core.constant.MimeType;
import com.yellow.product.utils.FileNameUtils;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class ImageDownloadManager extends DownLoadManager implements Message, MimeType {

    public ImageDownloadManager(File inputFile) throws DownloadException, LoaderException {
        super(inputFile);
    }

    /**
     * Returns reports of all the image urls, whether these urls are downloaded
     * or not (true or false) and note attached.
     * <p>
     * The method downloads images and reports the results.
     *
     * @return it returns download reports of all urls. That is the urls,
     * whether they are downloaded or not, and note attached.
     */
    @Override
    public List<Report> download() throws DownloadException {
        super.reports = new ArrayList<Report>();
        for (String line : inputs) {
            URL url = null;
            Report report = null;
            try {
                url = new URL(line);
                System.out.println("now processing.." + url);
                String name = FileNameUtils.getFileName(url);
                Pair<Boolean, String> pair = isImageFile(name);
                if (pair.getKey()) {
                    String extension = pair.getValue();
                    File outputFile = new File(super.downloadLocation + File.separator + name);
                    try {
                        report = downloadImageFromUrl(url, extension, outputFile);
                    } catch (IOException ex) {
                        report = new Report(url, Boolean.FALSE, FAIL_DOWNLOAD_GENERAL + ex.getMessage());
                    }
                } else {
                    report = new Report(url, Boolean.FALSE, pair.getValue() + NOT_SUPPORTED_URL);
                }
            } catch (MalformedURLException ex) {
                report = new Report(url, Boolean.FALSE, INVALID_URL);
            } catch (DownloadException ex) {
                report = new Report(url, Boolean.FALSE, ex.getMessage());
            }
            reports.add(report);
        }

        return reports;
    }

    /**
     * Return download report for the url.
     * <p>
     * The method downloads the image and store it in download location.
     *
     * @param url to download
     * @param imageType
     * @return the output file
     * @throws IOException if the image is failed to download
     */
    private Report downloadImageFromUrl(URL url, String imageType, File outputFile) throws IOException {
        BufferedImage image = ImageIO.read(url);
        if (image != null) {
            if (outputFile.exists()) {
                outputFile.delete();
            }
            ImageIO.write(image, imageType, outputFile);
            return new Report(url, Boolean.TRUE, SUCCESSFUL_DOWNLOAD);
        } else {
            return new Report(url, Boolean.FALSE, FAIL_DOWNLOAD);
        }
    }

    /**
     * Check whether it is a valid image file of not (i.e. jpg, jpeg, png, etc.)
     * <p>
     * @param the file name
     * @return two results. Whether it is image type or not (true or false). and
     * the file name.
     */
    private Pair<Boolean, String> isImageFile(String path) throws DownloadException {
        String mimeType = null;
        try {
            mimeType = URLConnection.guessContentTypeFromName(new File(path).getName());
            Pair<Boolean, String> pair = FileNameUtils.getExtensionFromMimeType(mimeType);
            if (pair.getKey()) {
                mimeType = pair.getValue();
                if (IMAGE_FILES.contains(mimeType)) {
                    return new Pair<Boolean, String>(Boolean.TRUE, mimeType);
                }
            }
        } catch (Exception ex) {
            throw new DownloadException(INVALID_MIME_TYPE + " " + ex.getMessage());
        }

        return new Pair<Boolean, String>(Boolean.FALSE, mimeType);
    }

}
