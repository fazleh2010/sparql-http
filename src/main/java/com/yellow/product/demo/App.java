package com.yellow.product.demo;

import com.yellow.product.core.api.DownLoadManager;
import com.yellow.product.core.api.Loader;
import com.yellow.product.core.impl.ImageDownloadManager;
import com.yellow.product.core.impl.LoaderImpl;
import com.yellow.product.exception.DownloadException;
import com.yellow.product.exception.LoaderException;
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
                    DownLoadManager imageDownloader;
                    File file = new File(args[0]);
                    if(args.length>1){
                       imageDownloader = new ImageDownloadManager(file,args[1]);
                    }else{
                       imageDownloader = new ImageDownloadManager(file,null);
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
