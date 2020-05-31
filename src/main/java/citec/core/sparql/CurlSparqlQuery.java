/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.core.sparql;

import static citec.core.sparql.SparqlEndpoint.iate_query1;
import citec.core.termbase.TermInfo;
import citec.core.termbase.Termbase;
import citec.core.utils.FileUrlUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import static citec.core.sparql.SparqlEndpoint.endpoint_atc;

/**
 *
 * @author elahi
 * //https://stackoverflow.com/questions/13356534/how-to-read-xml-child-node-values-by-using-a-loop-in-java
 * //https://howtodoinjava.com/xml/read-xml-dom-parser-example/
 */
public class CurlSparqlQuery {

    private Termbase termbase = null;

    public CurlSparqlQuery(String endpoint, String query, String termBaseName) throws Exception {
        String resultSparql = sparqlQuery(endpoint, query);
        System.out.println(resultSparql);
        //this.termbase = new Termbase(termBaseName, parseResult(resultSparql));
    }

    private String sparqlQuery(String tbx2rdf_atc_endpoint, String iate_query1) throws Exception, IOException, UnsupportedEncodingException {
        String result = null;
        String resultUnicode = FileUrlUtils.stringToUrlUnicode(iate_query1);
        String command = "curl " + tbx2rdf_atc_endpoint + "?query=" + resultUnicode;
        Process process = Runtime.getRuntime().exec(command);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            result = builder.toString();
            // System.out.println("result String:");
            //System.out.print(result);
            //convertToXML(result);

        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, TermInfo> parseResult(String xmlStr) throws SAXException, IOException, ParserConfigurationException, Exception {
        Document doc = convertStringToXMLDocument(xmlStr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return this.parseResult(builder, xmlStr);

    }

    private Document convertStringToXMLDocument(String xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, TermInfo> parseResult(DocumentBuilder builder, String xmlStr) throws SAXException, IOException, DOMException, Exception {
        Map<String, TermInfo> allkeysValues = new HashMap<String, TermInfo>();
        Document document = builder.parse(new InputSource(new StringReader(
                xmlStr)));
        NodeList results = document.getElementsByTagName("results");

       
        for (int i = 0; i < results.getLength(); i++) {
            NodeList childList = results.item(i).getChildNodes();

            for (int j = 0; j < childList.getLength(); j++) {
                Node childNode = childList.item(j);
                if ("result".equals(childNode.getNodeName())) {
                    String string = childList.item(j).getTextContent().trim();
                    //st = st.replaceAll("\\s+","");
                    //System.out.println(string);
                    String[] infos = string.split("\n");
                    List<String> wordList = Arrays.asList(infos);
                    String url = null, predicate = null, term = null;
                    Integer index = 0;
                    for (String http : wordList) {
                        if (http.contains("http") && http.contains("CanonicalForm")) {
                            url = http;
                        } else {
                            term = http.trim();
                        }

                    }
                    TermInfo termInfo = new TermInfo(url, null, term, true);
                    System.out.println(termInfo.getTermUrl());
                    allkeysValues.put(termInfo.getTermOrg(), termInfo);
                }
            }
        }
        return allkeysValues;
    }

    public Termbase getTermbase() {
        return termbase;
    }

}
