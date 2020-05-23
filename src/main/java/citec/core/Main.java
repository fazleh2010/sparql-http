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
    private static String linkTermbase = "atc/";
    private static Integer limitOfTerms = 50000;

    public static void main(String[] args) throws Exception {
        //ResultSet first_results = getResult(tbx2rdf_atc_endpoint, iate_query);
         //ResultSet sec_results = getResult(dbpedia_endpoint, dbpedia_query);*/
        //ResultSet first_results = getResult(tbx2rdftest, tbx2rdf_iate__query);
       
        //Test connection..
        
          //System.out.println(file.getAbsolutePath());
        
        String myTermTable = "iate", otherTermTable = "atc",matchedTermTable="link";
        MySQLAccess mySQLAccess=new MySQLAccess();
        
        //my terminology
        Termbase myTerminology = getTermBase(myTermTable, path + myTermbase, ".txt");
        addToDataBase(myTermTable, myTerminology,mySQLAccess,limitOfTerms);
        
        //Link terminology
        Termbase otherTerminology = getTermBase(otherTermTable, path + linkTermbase, ".txt");
        addToDataBase(otherTermTable, otherTerminology,mySQLAccess,limitOfTerms);
        
        matchWithDataBase(myTermTable,otherTerminology,mySQLAccess, matchedTermTable);
        
        mySQLAccess.close();
        
       
        
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
            mySQLAccess.deleteTable(myTermTable);
            mySQLAccess.createTermTable(myTermTable);
            mySQLAccess.insertDataTermTable(myTermTable, myTerminology, limitOfTerms);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
     return true;
       
    }
    private static Boolean matchWithDataBase(String myTermTable, Termbase otherTerminology, MySQLAccess mySQLAccess,String matchedTermTable) {
        try {
            mySQLAccess.deleteTable(matchedTermTable);
            mySQLAccess.createLinkingTable(matchedTermTable);
            Integer index=mySQLAccess.insertDataTermTable(myTermTable,otherTerminology,matchedTermTable);
            System.out.println("number of matched found:"+index);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
     return true;
       
    }

}
