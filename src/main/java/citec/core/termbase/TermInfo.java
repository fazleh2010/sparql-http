/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.core.termbase;

import citec.core.utils.StringMatcherUtil;
import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 *
 * @author elahi
 */
public class TermInfo {

    public static String LANGUAGE_SEPERATE_SYMBOLE = "@";
    public static String HASH_SYMBOLE = "#";
    private String termOrg = "";
    private String termDecrpt = "";
    private String termUrl = "";
    private String alternativeUrl = "";
    private SubjectInfo subject = new SubjectInfo();
    private String reliabilityCode = "";
    private String administrativeStatus = "";
    private String POST = "";
    private String Number = "";
    private String Gender = "";
    private String Definition = "";
    private String Hypernym = "";
    private String Hyponym = "";
    private String Variant = "";
    private String Synonym = "";
    private String language = "";

    public TermInfo(RDFNode subject, RDFNode object) {
        this.setTermUrl(subject);
        this.setTermAndLanguage(object);

    }

    public TermInfo(String line) {
        String[] info = line.split("=");
        this.termOrg = info[0].toLowerCase().trim();
        this.termUrl = info[1];

    }

    public TermInfo(String term, String url) {
        this.termOrg = term;
        this.termUrl = url;
    }

    public TermInfo(String term, String url, String alternativeUrl, SubjectInfo subject) {
        this(term, url);
        this.subject = subject;
        this.alternativeUrl = alternativeUrl.toString();
    }

    public TermInfo(String term, String url, String alternativeUrl, String reliabilityCode, String administrativeStatus, SubjectInfo subjectInfo) {
        this(term, url, alternativeUrl, subjectInfo);
        if (reliabilityCode != null) {
            this.reliabilityCode = reliabilityCode;
        }
        if (administrativeStatus != null) {
            this.administrativeStatus = administrativeStatus;
        }

    }

    public String getTermOrg() {
        return termOrg;
    }

    public String getTermDecrpt() {
        return StringMatcherUtil.decripted(termOrg);
    }

    public String getTermUrl() {
        return termUrl;
    }

    public String getSubjectDescription() {
        return this.subject.getSubjectDescription();
    }

    public String getReliabilityCode() {
        return reliabilityCode;
    }

    public String getAdministrativeStatus() {
        return administrativeStatus;
    }

    public String getReferenceID() {
        return this.subject.getReferenceID();
    }

    public String getSubjectId() {
        return this.subject.getSubjectId();
    }

    public String getAlternativeUrl() {
        return alternativeUrl;
    }

    public SubjectInfo getSubject() {
        return subject;
    }

    public String getPOST() {
        return POST;
    }

    public String getNumber() {
        return Number;
    }

    public String getGender() {
        return Gender;
    }

    public String getDefinition() {
        return Definition;
    }

    public String getHypernym() {
        return Hypernym;
    }

    public String getHyponym() {
        return Hyponym;
    }

    public String getVariant() {
        return Variant;
    }

    public String getSynonym() {
        return Synonym;
    }

    private String findTermUrl(RDFNode subject) {
        boolean isSubjectFound = subject.toString().indexOf(HASH_SYMBOLE) != -1 ? true : false;
        if (isSubjectFound) {
            String[] info = subject.toString().split(HASH_SYMBOLE);
            return info[0];
        }
        return null;
    }

    public String getLanguage() {
        return language;
    }

    private void setTermUrl(RDFNode subject) {
        boolean isSubjectFound = subject.toString().indexOf(HASH_SYMBOLE) != -1 ? true : false;
        if (isSubjectFound) {
            String[] info = subject.toString().split(HASH_SYMBOLE);
            this.termUrl = info[0];
        }
    }

    private void setTermAndLanguage(RDFNode object) {
        boolean isObjectFound = object.toString().indexOf(LANGUAGE_SEPERATE_SYMBOLE) != -1 ? true : false;
        if (isObjectFound) {
            String[] info = object.toString().split(LANGUAGE_SEPERATE_SYMBOLE);
            this.termOrg = info[0].toLowerCase().trim();
            this.termDecrpt = StringMatcherUtil.decripted(termOrg);
            this.language = info[1].toLowerCase().trim();
        }
    }

    @Override
    public String toString() {
        return "TermInfo{" + "language=" + language + ",termOrg=" + termOrg + ", termDecrpt=" + termDecrpt + ", termUrl=" + termUrl
                + ", alternativeUrl=" + alternativeUrl + ", subject=" + subject
                + ", reliabilityCode=" + reliabilityCode + ", administrativeStatus=" + administrativeStatus + ", POST=" + POST
                + ", Number=" + Number + ", Gender=" + Gender + ", Definition=" + Definition
                + ", Hypernym=" + Hypernym + ", Hyponym=" + Hyponym + ", Variant=" + Variant
                + ", Synonym=" + Synonym + '}';
    }

}
