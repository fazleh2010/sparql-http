/**
 * <h1>Image download Manager</h1>
 * The ImageDownloadManager is subclass of parent DownloadManager. The urls of
 * input file is extracted at super class. This class downloads and stores
 * images in local machine. The reports of result of download (SUCESS or FAIL)
 * are generated.
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
     * Returns report whether images of the urls are downloaded or not.
     * <p>
     * The method downloads images given urls and reports the results.
     *
     * @return it returns reports of all urls. That is whether images are
     * downloaded or not.
     */
    @Override
    public Report download() throws DownloadException {
        List<ImageDownloadReport> reports = new ArrayList<ImageDownloadReport>();
        for (String line : inputs) {
            URL url = null;
            ImageDownloadReport report = null;
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
                        report = new ImageDownloadReport(url, Boolean.FALSE, FAIL_DOWNLOAD_GENERAL + ex.getMessage());
                    }
                } else {
                    report = new ImageDownloadReport(url, Boolean.FALSE, pair.getValue() + NOT_SUPPORTED_URL);
                }
            } catch (MalformedURLException ex) {
                throw new DownloadException(INVALID_URL + " " + ex.getMessage());
            } catch (IOException ex) {
                throw new DownloadException(ex.getMessage());
            } catch (Exception ex) {
                report = new ImageDownloadReport(url, Boolean.FALSE, ex.getMessage());
            }
            reports.add(report);
        }
        return new Report(this.downloadLocation, inputFileName, reports);
    }

    /**
     * Return Report of download of an url.
     * <p>
     * The method downloads the image given an url.
     *
     * @param url to download
     * @param imageType the types of image
     * @return the output file
     * @throws IOException if the image is failed to download
     */
    private ImageDownloadReport downloadImageFromUrl(URL url, String imageType, File outputFile) throws IOException {
        BufferedImage image = ImageIO.read(url);
        if (image != null) {
            if (outputFile.exists()) {
                outputFile.delete();
            }
            ImageIO.write(image, imageType, outputFile);
            return new ImageDownloadReport(url, Boolean.TRUE, SUCCESSFUL_DOWNLOAD);
        } else {
            return new ImageDownloadReport(url, Boolean.FALSE, FAIL_DOWNLOAD_NO_IMAGE);
        }
    }

    /**
     * Check whether it is a valid image file of not (i.e. jpg,png, etc.)
     * <p>
     * @param the file name
     * @return two results. Whether it is image type or not (true or false) and
     * the mimeType.
     */
    private Pair<Boolean, String> isImageFile(String path) throws Exception {
        String mimeType = null;

        mimeType = URLConnection.guessContentTypeFromName(new File(path).getName());
        Pair<Boolean, String> pair;
        try {
            pair = FileNameUtils.getExtensionFromMimeType(mimeType);
            if (pair.getKey()) {
                mimeType = pair.getValue();
                if (IMAGE_FILES.contains(mimeType)) {
                    return new Pair<Boolean, String>(Boolean.TRUE, mimeType);
                } else {
                    throw new Exception(NOT_IMAGE_FILE);
                }
            }
        } catch (Exception ex) {
            throw new Exception(FAIL_DETECT_IMAGE_FILE);
        }
        return new Pair<Boolean, String>(Boolean.FALSE, mimeType);
    }

}
