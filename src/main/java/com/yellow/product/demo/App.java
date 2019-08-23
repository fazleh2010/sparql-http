package com.yellow.product.demo;

import com.yellow.product.core.api.DownLoadManager;
import com.yellow.product.core.impl.ImageDownloadManager;
import com.yellow.product.exception.DownloadException;
import com.yellow.product.exception.LoaderException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mohammad Fazleh Elahi
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
        try {
            if (args.length < 1) {
                System.out.println("parameter for input file is missing!!");
                System.exit(1);
            } else {
                try {
                    File file = new File(args[0]);
                    if (args.length > 1) {
                        System.out.println("Too many parameters! only one paramter is permitted!!");
                        System.exit(1);
                    } else {
                        DownLoadManager imageDownloader = new ImageDownloadManager(file);
                        imageDownloader.display();
                    }
                } catch (DownloadException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LoaderException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
