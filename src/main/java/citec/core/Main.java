/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.core;

import citec.core.utils.FileUrlUtils;
import citec.core.sparql.SparqlEndpoint;
import citec.core.termbase.Termbase;
import citec.core.mysql.MySQLAccess;
import citec.core.sparql.CurlSparqlQuery;
import citec.core.sparql.SparqlQuery;
import citec.core.termbase.TermInfo;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elahi
 */
public class Main implements SparqlEndpoint {

    private static String path = "src/main/resources/";
    private static Integer limitOfTerms = -1;

    public static void main(String[] args) throws Exception {

        String myTermTableName = "myTerminology", otherTermTableName = "otherTerminology", matchedTermTable = "link";
        String myTermSparqlEndpoint = null, otherTermSparqlEndpoint = null;

        try {
            if (args[0] != null);
            myTermSparqlEndpoint = args[0];
            if (args[1] != null) {
                otherTermSparqlEndpoint = args[1];
            }

        } catch (Exception e) {
            myTermSparqlEndpoint = SparqlEndpoint.tbx2rdf_atc_endpoint;
            otherTermSparqlEndpoint = SparqlEndpoint.tbx2rdf_intaglio_endpoint;
            System.out.println("myTermSparqlEndpoint");
            System.out.println("otherTermSparqlEndpoint");
        }

        CurlSparqlQuery curlSparql = new CurlSparqlQuery(tbx2rdf_atc_endpoint,writtenFormQuery,myTermSparqlEndpoint);

        /*MySQLAccess mySQLAccess = new MySQLAccess();

        //my terminology
        //Termbase myTerminology = getTermBaseFromTxtFiles(myTermTableName, path + myTermTableName+File.separator, ".txt");
        SparqlQuery myTermsparqlQuery=new SparqlQuery(myTermSparqlEndpoint, myTermTableName);
        Termbase myTerminology = myTermsparqlQuery.getTerminology();
        addToDataBase(myTermTableName, myTermsparqlQuery.getTerminology(), mySQLAccess, limitOfTerms);

        //Link terminology
        //Termbase otherTerminology = getTermBaseFromTxtFiles(otherTermTableName, path + otherTermTableName+File.separator, ".txt");
        SparqlQuery otherSparqlQuery=new SparqlQuery(otherTermSparqlEndpoint, otherTermTableName);
        Termbase otherTerminology = otherSparqlQuery.getTerminology();
        addToDataBase(otherTermTableName, otherSparqlQuery.getTerminology(), mySQLAccess, limitOfTerms);

        matchWithDataBase(myTermTableName,otherTerminology, mySQLAccess, matchedTermTable);

        mySQLAccess.close();*/
    }

    private static Termbase getTermBaseFromTxtFiles(String termBaseName, String path, String extension) throws Exception {
        //System.out.println(termBaseName+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        File[] myTerminologyfiles = FileUrlUtils.getFiles(path, extension);
        Map<String, TermInfo> allkeysValues = new HashMap<String, TermInfo>();
        for (File file : myTerminologyfiles) {
            //System.out.println(file.getAbsolutePath());
            Map<String, TermInfo> terms = new HashMap<String, TermInfo>();
            terms = FileUrlUtils.getHashFromFile(file);
            allkeysValues.putAll(terms);
        }
        Termbase termbase = new Termbase(termBaseName, allkeysValues);
        return termbase;
    }

    private static Boolean addToDataBase(String myTermTableName, Termbase myTerminology, MySQLAccess mySQLAccess, Integer limitOfTerms) {
        try {
            mySQLAccess.deleteTable(myTermTableName);
            mySQLAccess.createTermTable(myTermTableName);
            mySQLAccess.insertDataTermTable(myTermTableName, myTerminology, limitOfTerms);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;

    }

    private static Boolean matchWithDataBase(String myTermTable, Termbase otherTerminology, MySQLAccess mySQLAccess, String matchedTermTable) {
        try {
            mySQLAccess.deleteTable(matchedTermTable);
            mySQLAccess.createLinkingTable(matchedTermTable);
            Integer index = mySQLAccess.insertDataTermTable(myTermTable, otherTerminology, matchedTermTable);
            System.out.println("number of matched found:" + index);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;

    }

    //ResultSet first_results = getResultSparql(tbx2rdf_atc_endpoint, writtenFormQuery);
    //ResultSet sec_results = getResultSparql(dbpedia_endpoint, dbpedia_query);*/
    //ResultSet first_results = getResultSparql(tbx2rdftest, tbx2rdf_iate__query);
    //Test connection..
    //System.out.println(file.getAbsolutePath());
    //String myTermTableName = "iate", otherTermTableName = "atc",matchedTermTable="link";
}
