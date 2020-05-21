/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.core.termbase;

import citec.core.utils.FileRelatedUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author elahi
 */
public class Termbase {

    private final String language;
    private final String pair;
    private Map<String, TermInfo> terms = new HashMap<String, TermInfo>();

    public Termbase(String language,String pair, String fileName) throws IOException {
        this.language = language;
        this.pair=pair;
        this.terms = FileRelatedUtils.getHashFromFile(fileName);
    }

    public String getLanguage() {
        return language;
    }

    public Map<String, TermInfo> getTerms() {
        return terms;
    }

    public String getPair() {
        return pair;
    }

}
