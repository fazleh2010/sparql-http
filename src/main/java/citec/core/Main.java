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
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.RDFNode;
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
            System.out.println("Invalid parameter!! default values are used");
        }

        MySQLAccess mySQLAccess = new MySQLAccess();

        //my terminology
        //Termbase myTerminology = getTermBaseFromTxtFiles(myTermTableName, path + myTermTableName+File.separator, ".txt");
        Termbase myTerminology = getTermBaseFromSparqlEndpoint(myTermSparqlEndpoint, myTermTableName);
        addToDataBase(myTermTableName, myTerminology, mySQLAccess, limitOfTerms);

        //Link terminology
        //Termbase otherTerminology = getTermBaseFromTxtFiles(otherTermTableName, path + otherTermTableName+File.separator, ".txt");
        Termbase otherTerminology = getTermBaseFromSparqlEndpoint(otherTermSparqlEndpoint, otherTermTableName);
        addToDataBase(otherTermTableName, otherTerminology, mySQLAccess, limitOfTerms);

        matchWithDataBase(myTermTableName, otherTerminology, mySQLAccess, matchedTermTable);

        mySQLAccess.close();

    }

    private static ResultSet getResultSparql(String sparql_endpoint, String sparql_query) {
        Query query = QueryFactory.create(sparql_query); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.sparqlService(sparql_endpoint, query);
        ResultSet results = qExe.execSelect();
        //ResultSetFormatter.out(System.out, results, query);
        return results;
    }

    private static Termbase getTermBaseFromTxtFiles(String termBaseName, String path, String extension) throws Exception {
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

    private static Termbase getTermBaseFromSparqlEndpoint(String sparqlEndpoint, String termBaseName) throws Exception {
        Map<String, TermInfo> allkeysValues = new HashMap<String, TermInfo>();
        ResultSet results = getResultSparql(sparqlEndpoint, iate_query);
        while (results != null && results.hasNext()) {
            QuerySolution querySolution = results.next();
            RDFNode subject = querySolution.get("?s");
            RDFNode predicate = querySolution.get("?p");
            RDFNode object = querySolution.get("?o");
            //System.out.println("subject:"+subject);
            //System.out.println("predicate:"+predicate);
            //System.out.println("object:"+object);
            TermInfo termInfo = new TermInfo(subject, object);
            //System.out.println(termInfo);
            allkeysValues.put(termInfo.getTermOrg(), termInfo);
        }

        Termbase termbase = new Termbase(termBaseName, allkeysValues);
        //termbase.display();
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

    //ResultSet first_results = getResultSparql(tbx2rdf_atc_endpoint, iate_query);
    //ResultSet sec_results = getResultSparql(dbpedia_endpoint, dbpedia_query);*/
    //ResultSet first_results = getResultSparql(tbx2rdftest, tbx2rdf_iate__query);
    //Test connection..
    //System.out.println(file.getAbsolutePath());
    //String myTermTableName = "iate", otherTermTableName = "atc",matchedTermTable="link";
}
