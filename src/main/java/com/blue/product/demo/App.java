package com.blue.product.demo;

import com.blue.product.core.api.Downloader;
import com.blue.product.core.impl.ImageDownloader;
import com.blue.product.exception.DownloadException;
import com.blue.product.exception.LoaderException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimeType;
import javax.activation.MimetypesFileTypeMap;

/**
 * Hello world!
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
                    Downloader imageDownloader;
                    File file = new File(args[0]);
                    if(args.length>1){
                       imageDownloader = new ImageDownloader(file,args[1]);
                    }else{
                       imageDownloader = new ImageDownloader(file);
                    }
                        
                    imageDownloader.download();
                    System.out.print(imageDownloader.getOutputs());
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
