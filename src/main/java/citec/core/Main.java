/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.core;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 *
 * @author elahi
 */
public class Main {

    public static void main(String[] args) {
        Main main=new Main();
        String first_endpoint="https://webtentacle1.techfak.uni-bielefeld.de/tbx2rdf_iate/sparql/";
        String  first_query = "select distinct ?Concept where {[] a ?Concept} LIMIT 100";
        String sec_endpoint="http://dbpedia.org/sparql";
         String sec_query="select distinct ?Concept where {[] a ?Concept} LIMIT 100";;
        ResultSet  first_results=getResult(first_endpoint,first_query);
        ResultSet  sec_results=getResult(sec_endpoint,sec_query);

    }

    private static ResultSet getResult( String sparql_endpoint,String sparql_query) {
        Query query = QueryFactory.create(sparql_query); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.sparqlService(sparql_endpoint, query);
        ResultSet results = qExe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        return results;
    }

}
