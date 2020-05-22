/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;*/

/**
 *
 * @author elahi
 */
public class SparqlHttpTest1 {
     public static void main(String[] args) {
//
//  //CONNESSIONE RDF METEO
//      final String uri = "http://www.dati.lombardia.it/resource/647i-nhxk/";
//      final Model model = ModelFactory.createDefaultModel();
//      model.read(uri);
//      model.write(System.out);


        /*String s2 = "PREFIX  rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX  socrata: <http://www.socrata.com/rdf/terms#>\n" +
                "PREFIX  dcat: <http://www.w3.org/ns/dcat#>\n" +
                "PREFIX  ods: <http://open-data-standards.github.com/2012/01/open-data-standards#>\n" +
                "PREFIX  dcterm: <http://purl.org/dc/terms/>\n" +
                "PREFIX  geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" +
                "PREFIX  skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "PREFIX  foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "PREFIX  dsbase: <http://www.dati.lombardia.it/resource/>\n" +
                "PREFIX  ds: <http://www.dati.lombardia.it/resource/647i-nhxk/>\n" +
                "\n" +
                "SELECT  ?idsensore ?valore \n" +
                "WHERE\n" +
                "  { ?x ds:idsensore ?idsensore .\n" +
                "?x ds:valore ?valore .\n" +
                "  }\n" +
                "LIMIT   5\n" +
                "";
        
        
        
        String s2 = "select distinct ?Concept where {[] a ?Concept} LIMIT 100";

        Query query = QueryFactory.create(s2); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", query );
        ResultSet results = qExe.execSelect();
        ResultSetFormatter.out(System.out, results, query) ;*/


    }

    
}
