/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.core;

import citec.core.termbase.Termbase;
import citec.core.mysql.MySQLAccess;
import citec.core.utils.FileRelatedUtils;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import java.io.File;

/**
 *
 * @author elahi
 */
public class Main implements SparqlEndpoint {
    
    private static String path = "src/main/resources/";
    private static String myTermbase = "iate/";
    private static String myTermFileName = "tbx2rdf_iate_en_A_B";
    private static String linkTermbase = "atc/";
    private static String linkTermFileName = "tbx2rdf_atc_en_A_B";
    private static Integer limitOfTerms = -1;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        ResultSet first_results = getResult(tbx2rdf_atc_endpoint, iate_query);
         //ResultSet sec_results = getResult(dbpedia_endpoint, dbpedia_query);*/
        //ResultSet first_results = getResult(tbx2rdftest, tbx2rdf_iate__query);
       
        /*MySQLAccess mySQLAccess = new MySQLAccess();
        
        //my terminology
        File inputFile=new File(path+myTermbase+myTermFileName+".txt");
        Termbase languageMyTerms=new Termbase("en","A_B",inputFile);
        mySQLAccess.deleteTable(myTermFileName);
        mySQLAccess.createTermTable(myTermFileName);
        mySQLAccess.insertDataTermTable(myTermFileName,languageMyTerms,limitOfTerms);
        
        //Link terminology
        File LinkTermFile=new File(path+myTermbase+myTermFileName+".txt");
        Termbase languageLInkTerms=new Termbase("en","A_B",LinkTermFile);
        mySQLAccess.deleteTable(linkTermFileName);
        mySQLAccess.createTermTable(linkTermFileName);
        mySQLAccess.insertDataTermTable(linkTermFileName,languageLInkTerms,limitOfTerms);*/
        
        
        //mySQLAccess.close();
        //FileRelatedUtils.getHashFromFile(path+myTermbase+myTermFileName);
        //MySQLAccess mySQLAccess = new MySQLAccess();
        /*mySQLAccess.createTermTable("iate_en_A_B");
        mySQLAccess.createTermTable("atc_en_A_B");
        mySQLAccess.createLinkingTable("linking_terms");
        mySQLAccess.close();*/
        //mySQLAccess.deleteTable("en_A_B_term");
        //mySQLAccess.deleteTable("linking_terms");
        //mySQLAccess.insertDataTermTable("en_A_B_term");
        //mySQLAccess.insertDataLinkTable("linking_terms");
    }

    private static ResultSet getResult(String sparql_endpoint, String sparql_query) {
        Query query = QueryFactory.create(sparql_query); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.sparqlService(sparql_endpoint, query);
        ResultSet results = qExe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        return results;
    }

}
