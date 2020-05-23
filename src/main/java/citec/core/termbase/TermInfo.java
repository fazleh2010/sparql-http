/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.core.termbase;

import citec.core.utils.StringMatcherUtil;

/**
 *
 * @author elahi
 */
public class TermInfo {

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

 
    @Override
    public String toString() {
        return "TermInfo{" + "termString=" + termOrg + ", termUrl=" + termUrl + ", alternativeUrl=" + alternativeUrl + ", subject=" + subject + ", reliabilityCode=" + reliabilityCode + ", administrativeStatus=" + administrativeStatus + '}';
    }

}
