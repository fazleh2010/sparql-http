/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.core;

import citec.core.sparql.SparqlEndpoint;
import citec.core.termbase.Termbase;
import citec.core.mysql.MySQLAccess;
import citec.core.utils.FileRelatedUtils;
import java.io.File;

/**
 *
 * @author elahi
 */
public class Main  {
    
    private static String path = "src/main/resources/";
    private static String myTermbase = "iate/";
    private static String myTermTable = "tbx2rdf_iate_en_A_B";
    private static String linkTermbase = "atc/";
    private static String otherTermTable = "tbx2rdf_atc_en_A_B";
    private static Integer limitOfTerms = 200;
    private static String matchedTermTable = "tbx2rdf_atc_en_A_B";

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        //ResultSet first_results = getResult(tbx2rdf_atc_endpoint, iate_query);
         //ResultSet sec_results = getResult(dbpedia_endpoint, dbpedia_query);*/
        //ResultSet first_results = getResult(tbx2rdftest, tbx2rdf_iate__query);
       
        //Test connection..
        MySQLAccess mySQLAccess = new MySQLAccess();
        
        //my terminology
        File inputFile=new File(path+myTermbase+myTermTable+".txt");
        Termbase languageMyTerms=new Termbase("en","A_B",inputFile);
        //Link terminology
        File LinkTermFile=new File(path+linkTermbase+otherTermTable+".txt");
        Termbase languageLInkTerms=new Termbase("en","A_B",LinkTermFile);
        
        //my terminology
        mySQLAccess.deleteTable(myTermTable);
        mySQLAccess.createTermTable(myTermTable);
        mySQLAccess.insertDataTermTable(myTermTable,languageMyTerms,limitOfTerms);
        
        //Link terminology
        mySQLAccess.deleteTable(otherTermTable);
        mySQLAccess.createTermTable(otherTermTable);
        mySQLAccess.insertDataTermTable(otherTermTable,languageLInkTerms,limitOfTerms);
        
        mySQLAccess.insertDataTermTable(myTermTable,languageLInkTerms,matchedTermTable);
        
    }

    /*private static ResultSet getResult(String sparql_endpoint, String sparql_query) {
        Query query = QueryFactory.create(sparql_query); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.sparqlService(sparql_endpoint, query);
        ResultSet results = qExe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        return results;
    }*/

}
