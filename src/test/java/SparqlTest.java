/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import citec.core.mysql.MySQLAccess;
import citec.core.termbase.Termbase;
/*import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import java.io.File;*/


public class SparqlTest {

   /* public static final String tbx2rdf_iate_endpoint = "https://webtentacle1.techfak.uni-bielefeld.de/tbx2rdf_iate/sparql/";
    public static String tbx2rdf_iate__query = "select distinct ?Concept where {[] a ?Concept} LIMIT 100";
    public static final String tbx2rdf_atc_endpoint = "https://webtentacle1.techfak.uni-bielefeld.de/tbx2rdf_atc/sparql/";
    public static String tbx2rdf_atc_query = "PREFIX cc:    <http://creativecommons.org/ns#> \n"
            + "PREFIX void:  <http://rdfs.org/ns/void#> \n"
            + "PREFIX skos:  <http://www.w3.org/2004/02/skos/core#> \n"
            + "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#> \n"
            + "PREFIX tbx:   <http://tbx2rdf.lider-project.eu/tbx#> \n"
            + "PREFIX decomp: <http://www.w3.org/ns/lemon/decomp#> \n"
            + "PREFIX dct:   <http://purl.org/dc/terms/> \n"
            + "PREFIX rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
            + "PREFIX ontolex: <http://www.w3.org/ns/lemon/ontolex#> \n"
            + "PREFIX ldr:   <http://purl.oclc.org/NET/ldr/ns#> \n"
            + "PREFIX odrl:  <http://www.w3.org/ns/odrl/2/> \n"
            + "PREFIX dcat:  <http://www.w3.org/ns/dcat#> \n"
            + "PREFIX prov:  <http://www.w3.org/ns/prov#> \n"
            + "\n"
            + "SELECT ?s ?p ?o from <http://tbx2rdf.lider-project.eu/> WHERE { \n"
            + "    ?s ?p ?o .\n"
            + "    ?o ontolex:canonicalForm ?canform .\n"
            + "    ?canform ontolex:writtenRep ?rep .\n"
            + "    FILTER regex(str(?rep), \"ace\") .\n"
            + "} LIMIT 5\n"
            + "        ";

    public static void main(String[] args) throws Exception {
        ResultSet first_results = getResult(tbx2rdf_atc_endpoint, tbx2rdf_atc_query);
        //ResultSet sec_results = getResult(dbpedia_endpoint, dbpedia_query);
        //ResultSet first_results = getResult(tbx2rdftest, tbx2rdf_iate__query);

    }

    private static ResultSet getResult(String sparql_endpoint, String sparql_query) {
        Query query = QueryFactory.create(sparql_query); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.sparqlService(sparql_endpoint, query);
        ResultSet results = qExe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        return results;
    }*/

}
