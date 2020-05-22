/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.core;

import citec.core.sparql.SparqlEndpoint;
import citec.core.termbase.Termbase;
import citec.core.mysql.MySQLAccess;
import citec.core.termbase.TermInfo;
import citec.core.utils.FileRelatedUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elahi
 */
public class Main  {
    
    private static String path = "src/main/resources/";
    private static String myTermbase = "iate/";
    //private static String myTermTable = "tbx2rdf_iate_en_A_B";
    private static String linkTermbase = "atc/";
    //private static String otherTermTable = "tbx2rdf_atc_en_A_B";
    private static Integer limitOfTerms = 10000;
    private static String matchedTermTable = "tbx2rdf_atc_en_A_B";

    public static void main(String[] args) throws Exception {
        //ResultSet first_results = getResult(tbx2rdf_atc_endpoint, iate_query);
         //ResultSet sec_results = getResult(dbpedia_endpoint, dbpedia_query);*/
        //ResultSet first_results = getResult(tbx2rdftest, tbx2rdf_iate__query);
       
        //Test connection..
        
          //System.out.println(file.getAbsolutePath());
        
        String myTermTable = "iate", otherTermTable = "atc";
        MySQLAccess mySQLAccess=new MySQLAccess();
        
        //my terminology
        Termbase myTerminology = getTermBase(myTermTable, path + myTermbase, ".txt");
        addToDataBase(myTermTable, myTerminology,mySQLAccess,limitOfTerms);
        
        //Link terminology
        Termbase otherTerminology = getTermBase(otherTermTable, path + linkTermbase, ".txt");
        addToDataBase(otherTermTable, otherTerminology,mySQLAccess,limitOfTerms);
        
        mySQLAccess.insertDataTermTable(myTermTable,otherTerminology,matchedTermTable);
        
    }

    /*private static ResultSet getResult(String sparql_endpoint, String sparql_query) {
        Query query = QueryFactory.create(sparql_query); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.sparqlService(sparql_endpoint, query);
        ResultSet results = qExe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        return results;
    }*/

    private static Termbase getTermBase(String termBaseName, String path, String extension) throws Exception {
         //System.out.println(termBaseName+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        File[] myTerminologyfiles = FileRelatedUtils.getFiles(path, extension);
        Map<String, TermInfo> allkeysValues = new HashMap<String, TermInfo>();
        for (File file : myTerminologyfiles) {
            //System.out.println(file.getAbsolutePath());
            Map<String, TermInfo> terms = new HashMap<String, TermInfo>();
            terms = FileRelatedUtils.getHashFromFile(file);
            allkeysValues.putAll(terms);
        }
        Termbase termbase = new Termbase(termBaseName, allkeysValues);
        return termbase;
    }

    private static Boolean addToDataBase(String myTermTable, Termbase myTerminology, MySQLAccess mySQLAccess,Integer limitOfTerms) {
        try {
            mySQLAccess = new MySQLAccess();
            mySQLAccess.deleteTable(myTermTable);
            mySQLAccess.createTermTable(myTermTable);
            mySQLAccess.insertDataTermTable(myTermTable, myTerminology, limitOfTerms);
            mySQLAccess.close();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
     return true;
       
    }

}
