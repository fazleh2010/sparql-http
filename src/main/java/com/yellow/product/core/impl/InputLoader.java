/**
 * <h1>Input processor</h1>
 * The InputLoader takes a input file and extract all lines. The line can be
 * anything.
 */
package com.yellow.product.core.impl;

import com.yellow.product.core.api.Loader;
import com.yellow.product.exception.LoaderException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.nio.charset.StandardCharsets;
import javax.activation.MimetypesFileTypeMap;
import com.yellow.product.core.constant.Message;
import java.io.FileNotFoundException;
import com.yellow.product.core.constant.MimeType;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class InputLoader implements Message, Loader, MimeType {

    // The location of input file
    private final String resourceLocation;
    // The lines of the file
    private Set<String> inputs;

    public InputLoader(File inputfile) throws LoaderException {
        this.resourceLocation = inputfile.getAbsoluteFile().getParent();
        this.inputs = this.getContentFromFile(inputfile);
    }

    /**
     * Read each line of a text file. The line can be anything.
     * <p>
     * @param input file
     * @return all lines of the file.
     * @throws LoaderException if the file is not found or not a text file
     */
    private Set<String> getContentFromFile(File inputfile) throws LoaderException {
        Set<String> inputs = new HashSet<String>();
        if (isTextFile(inputfile, TEXT_FILE)) {
            try {
                InputStream inputStream = new FileInputStream(inputfile);
                inputs = this.getContentFromFile(inputStream);
            } catch (FileNotFoundException ex) {
                throw new LoaderException(ex.getMessage());
            }
        }
        return inputs;
    }

    /**
     * Read each line of an InputStream .
     * <p>
     * @param inputStream of the file
     * @return all lines of the file.
     * @throws LoaderException if the file cannot be read
     */
    private Set<String> getContentFromFile(InputStream inputStream) throws LoaderException {
        BufferedReader reader = null;
        Set<String> inputs = new HashSet<String>();
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8.name()));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    inputs.add(line);
                }
            }

        } catch (Exception ex) {
            throw new LoaderException(ex.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                throw new LoaderException(ex.getMessage());
            }
        }
        return inputs;
    }

    /**
     * Checking whether the file is a text file.
     * <p>
     * @param the given input file
     * @param the file type to check.
     * @return true if it is text file. false otherwise.
     * @throws LoaderException if the file is not text file.
     */
    private boolean isTextFile(File inputFile, String FileType) throws LoaderException {
        MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        if (fileTypeMap.getContentType(inputFile.getName()).equalsIgnoreCase(FileType)) {
            return true;
        } else {
            throw new LoaderException(Message.NOT_TEXT_FILE);
        }

    }

    @Override
    public Set<String> getInputs() {
        return this.inputs;
    }

    @Override
    public String getInputLocation() {
        return resourceLocation;
    }

}
