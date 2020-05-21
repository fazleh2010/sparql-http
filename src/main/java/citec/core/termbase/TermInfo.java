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

    private String termString = "";
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
        termString = info[0].toLowerCase().trim();
        termString = StringMatcherUtil.encripted(termString);
        termUrl = info[1];
        try {
            termUrl = StringMatcherUtil.getUrl(termUrl);
        } catch (Exception ie) {
            System.err.println(ie.getMessage());
        }

    }

    public TermInfo(Object term, Object url) {
        this.termString = term.toString();
        this.termUrl = url.toString();
    }

 
    public TermInfo(Object term, Object url, Object alternativeUrl, SubjectInfo subject) {
        this(term, url);
        this.subject = subject;
        this.alternativeUrl = alternativeUrl.toString();
    }

    public TermInfo(Object term, Object url, Object alternativeUrl, Object reliabilityCode, Object administrativeStatus, SubjectInfo subjectInfo) {
        this(term, url, alternativeUrl, subjectInfo);
        if (reliabilityCode != null) {
            this.reliabilityCode = reliabilityCode.toString();
        }
        if (administrativeStatus != null) {
            this.administrativeStatus = administrativeStatus.toString();
        }

    }

    public String getTermString() {
        return termString;
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
        return "TermInfo{" + "termString=" + termString + ", termUrl=" + termUrl + ", alternativeUrl=" + alternativeUrl + ", subject=" + subject + ", reliabilityCode=" + reliabilityCode + ", administrativeStatus=" + administrativeStatus + '}';
    }

}
