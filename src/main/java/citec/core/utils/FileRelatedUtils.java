/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.core.utils;

import citec.core.termbase.TermInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author elahi
 */
public class FileRelatedUtils {

    /*public static List<File> writeFile(TreeMap<String, TreeMap<String, List<TermInfo>>> langSortedTerms, String path) throws IOException {
        List<File> files = new ArrayList<File>();
        for (String language : langSortedTerms.keySet()) {
            String str = "";
            TreeMap<String, List<TermInfo>> alphabetPairTerms = langSortedTerms.get(language);
            Integer pairIndex = 0;
            for (String pair : alphabetPairTerms.keySet()) {
                String fileName = path + "_" + language + "_" + pair + ".txt";
                List<TermInfo> terms = alphabetPairTerms.get(pair);
                str = "";
                pairIndex++;
                Integer termIndex = 0;
                for (TermInfo term : terms) {
                    String pairNote = null;

                    if (language.contains("en")) {
                        pairNote = pair;
                    } else {
                        pairNote = pairIndex.toString();
                    }

                    String line = term.getTermString() + " = " + term.getTermUrl();
                    str += line + "\n";
                }
                stringToFile_ApendIf_Exists(str, fileName);
            }

        }
        return files;
    }*/
    public static Map<String, TermInfo> getHashFromFile(File fileName) throws FileNotFoundException, IOException {
        Map<String, TermInfo> hash = new HashMap<String, TermInfo>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {

                // read next line
                line = reader.readLine();
                if (line != null) {
                    TermInfo termInfo=new TermInfo(line);
                    /*String[] info = line.split("=");
                    System.out.println(line);
                    hash.put(info[0], info[1]);*/
                    hash.put(termInfo.getTermUrl(), termInfo);
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hash;
    }

}
