/*
 * 
 */
package citec.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Mohammad Fazleh Elahi
 *
 * This is a interface contains constants of file type
 *
 */
public interface MimeType {

    //constants for plain text file
    public static final String TEXT_FILE = "text/plain";
    //constants for image file
    public static final String IMAGE_JPEG = "jpeg";
    public static final String IMAGE_JPG = "jpg";
    public static final String IMAGE_PNG = "png";
    public static final String IMAGE_BMP = "bmp";

    //A sets of all allowed image files
    public static final Set<String> IMAGE_FILES = new HashSet<String>(Arrays.asList(IMAGE_JPEG,
            IMAGE_JPG, IMAGE_PNG, IMAGE_BMP));

}
