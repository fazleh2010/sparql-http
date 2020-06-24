
import static citec.core.sparql.SparqlEndpoint.ontolex_owl_sameAs;
import static citec.core.sparql.SparqlEndpoint.ontolex_prefix;
import citec.core.sparql.SparqlGenerator;
import citec.core.termbase.TermInfo;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
/**
 *
 * @author elahi (a) Adding some triples to a graph. The snippet describes two
 * RDF triples to be inserted into the default graph of the RDF store.
 *
 * PREFIX dc: <http://purl.org/dc/elements/1.1/>
 * INSERT DATA { <http://example/book3> dc:title "A new book" ; dc:creator
 * "A.N.Other" . }
 */
/**
 *
 * @author elahi
 */
public class SparqlGeneratorTest {
    
    /*
    <http://webtentacle1.techfak.uni-bielefeld.de/tbx2rdf_intaglio/data/intaglio/water+tone-EN#CanonicalForm>
        ontolex:writtenRep  "water tone"@EN .
    */

    @Test
    public void insertSparqlForLinkTest() {
        List<TermInfo> termList=this.getTermInfos();
        SparqlGenerator sparqlGenerator = new SparqlGenerator(termList, ontolex_prefix, ontolex_owl_sameAs);
        System.out.println(sparqlGenerator.getSparqlQuery());
    }

    private List<TermInfo> getTermInfos() {
        String term1 = "notification-en";
        String termUrl1 = "<http://webtentacle1.techfak.uni-bielefeld.de/tbx2rdf_iate/data/X-terminology/notification-en>";
        String otherTeminology1 = "other";
        String OthertermUrl1 = " <http://webtentacle1.techfak.uni-bielefeld.de/data/Y-terminology/notification-en>";
        String term2 = "test-en";
        String termUrl2 = "<http://webtentacle1.techfak.uni-bielefeld.de/tbx2rdf_iate/data/X-terminology/test-en>";
        String otherTeminology2 = "other";
        String OthertermUrl2 = " <http://webtentacle1.techfak.uni-bielefeld.de/data/Y-terminology/test-en>";
        String term3 = "agent-en";
        String termUrl3 = "<http://webtentacle1.techfak.uni-bielefeld.de/tbx2rdf_iate/data/X-terminology/agent-en>";
        String otherTeminology3 = "other";
        String OthertermUrl3 = " <http://webtentacle1.techfak.uni-bielefeld.de/data/Y-terminology/agent-en>";
        TermInfo TermInfo1 = new TermInfo(term1, termUrl1,  OthertermUrl1);
        TermInfo TermInfo2 = new TermInfo(term2, termUrl2,  OthertermUrl2);
        TermInfo TermInfo3 = new TermInfo(term3, termUrl3,  OthertermUrl3);
        List<TermInfo> list = new ArrayList<TermInfo>();
        list.add(TermInfo1);
        list.add(TermInfo2);
        list.add(TermInfo3);
        return list;
    }

}
